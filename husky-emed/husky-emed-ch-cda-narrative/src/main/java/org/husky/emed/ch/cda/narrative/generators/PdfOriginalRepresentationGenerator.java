/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.emed.ch.cda.narrative.generators;

import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.emed.ch.cda.narrative.NarrativeTreatmentAuthor;
import org.husky.emed.ch.cda.narrative.NarrativeTreatmentDocument;
import org.husky.emed.ch.cda.narrative.NarrativeTreatmentItem;
import org.husky.emed.ch.cda.narrative.enums.NarrativeLanguage;
import org.husky.emed.ch.cda.narrative.pdf.HtmlToPdfAConverter;
import org.husky.emed.ch.cda.narrative.pdf.OpenHtmlToPdfAConverter;
import org.husky.emed.ch.cda.narrative.utils.NarrativeUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

/**
 * This class provides a way to generate a CDA-CH-EMED PRE or PMLC document original representation as a PDF 1/A. The
 * generated PDF contains the information of the CDA document rendered for human reading. The languages supported are
 * English, German, French and Italian.
 *
 * <p>To include the PDF in the document, see {@link NarrativeUtils#setPdfOriginalRepresentation}
 *
 * <p>HTML template file:
 *
 * @author Quentin Ligier
 * @implNote It first creates an HTML document with all the data and then converts it to a PDF.
 */
public class PdfOriginalRepresentationGenerator extends AbstractNarrativeGenerator {

    /**
     * The HTML-to-PDF/A converter.
     */
    private final HtmlToPdfAConverter pdfConverter;

    /**
     * The PDF author.
     */
    private String author = "Husky PDF generator";

    /**
     * Simplified constructor. It will use the default HTML-to-PDF/A generator.
     */
    public PdfOriginalRepresentationGenerator() throws IOException {
        super();
        final var converter = new OpenHtmlToPdfAConverter();
        converter.setProducerName("The Husky library");
        converter.addFont("arimo", 400, BaseRendererBuilder.FontStyle.NORMAL,
                () -> this.getResourceAsStream("/narrative/default/font/Arimo-Regular.ttf"));
        converter.addFont("arimo", 700, BaseRendererBuilder.FontStyle.NORMAL,
                () -> this.getResourceAsStream("/narrative/default/font/Arimo-Bold.ttf"));
        this.pdfConverter = converter;
    }

    /**
     * Full constructor.
     *
     * @param htmlToPdfAConverter The HTML-to-PDF/A converter.
     */
    public PdfOriginalRepresentationGenerator(final HtmlToPdfAConverter htmlToPdfAConverter) throws IOException {
        super();
        this.pdfConverter = Objects.requireNonNull(htmlToPdfAConverter);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = Objects.requireNonNull(author);
    }

    /**
     * It will use the default template.
     *
     * @param document
     * @return
     */
    public byte[] generate(final NarrativeTreatmentDocument document,
                           final NarrativeLanguage lang) throws Exception {
        return this.generate(document, lang, null, null);
    }

    /**
     * @param document
     * @param lang           The language to use to generate the narrative text.
     * @param templateHeader The HTML template header (before the main content).
     * @param templateFooter The HTML template footer (after the main content).
     * @return
     */
    public byte[] generate(final NarrativeTreatmentDocument document,
                           final NarrativeLanguage lang,
                           @Nullable String templateHeader,
                           @Nullable String templateFooter) throws Exception {
        Objects.requireNonNull(document, "digest shall not be null in generate()");
        Objects.requireNonNull(lang, "lang shall not be null in generate()");

        if (templateHeader == null) {
            try (final var is = this.getResourceAsStream("/narrative/default/template.header.html")) {
                templateHeader = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
        }
        if (templateFooter == null) {
            templateFooter = "</body></html>";
        }

        final var narDom = new NarrativeDomFactory(true);
        final var root = narDom.getDocument().getDocumentElement();

        // Document title & Patient's personal data
        root.appendChild(this.formatHeader(narDom, document, lang));

        // Last version
        root.appendChild(narDom.p(StringUtils.capitalize(this.getMessage("LAST_VERSION", lang)) + " " + document.getDocumentationTime()));

        // Medication table
        root.appendChild(narDom.title2(StringUtils.capitalize(this.getMessage("ACTIVE_TREATMENT", lang)), null));
        List<NarrativeTreatmentItem> activeTreatments = document.getActiveTreatments().stream()
                .filter(t -> !t.isInReserve())
                .toList();
        List<Element> medicationActiveTableRows = createMedicationTableRows(activeTreatments, lang, narDom);
        root.appendChild(this.createMedicationTable(narDom, medicationActiveTableRows, lang));

        // Medication in reserve
        root.appendChild(narDom.title2(StringUtils.capitalize(this.getMessage("IN_RESERVE_TREATMENT", lang)), null));
        List<NarrativeTreatmentItem> inReserveTreatment = document.getActiveTreatments().stream()
                .filter(NarrativeTreatmentItem::isInReserve)
                .toList();
        List<Element> medicationInReserveTableRows = createMedicationTableRows(inReserveTreatment, lang, narDom);
        root.appendChild(this.createMedicationTable(narDom, medicationInReserveTableRows, lang));

        Map<String, String> variables = new HashMap<>(64);
        variables.putIfAbsent("title", "Carte de médication");
        variables.putIfAbsent("subject", "");
        variables.putIfAbsent("author", this.author);
        variables.putIfAbsent("description", "");
        variables.putIfAbsent("lang", lang.getLanguageCode().getCodeValue());
        variables.putIfAbsent("generationDate", formatTemporal(Instant.now(), "dd.MM.yyyy hh:mm:ss", lang));

        final var stringSubstitutor = new StringSubstitutor(variables);
        final String body = stringSubstitutor.replace(templateHeader)
                + narDom.render()
                + stringSubstitutor.replace(templateFooter);

        return this.pdfConverter.convert(body);
    }

    List<Element> createMedicationTableRows(final List<NarrativeTreatmentItem> treatments,
                                            final NarrativeLanguage lang,
                                            final NarrativeDomFactory narDom) throws IOException {
        final var medicationTableRows = new ArrayList<Element>();
        for (final var treatment : treatments) {
            final var cells = new ArrayList<Element>();

            // Medication icon
            var rowspanForComment = treatment.getAnnotationComment() != null ? "2" : null;
            cells.add(narDom.td(narDom.img(treatment.getProductIcon(), this.getMessage("MEDICATION_ICON", lang)), "border-bottom border-top", rowspanForComment, null));

            // Medication name
            cells.add(narDom.td(this.formatMedicationName(narDom, treatment, lang), "col name"));

            // Medication image
            cells.add(narDom.td(narDom.img(treatment.getProductImage(), this.getMessage("MEDICATION_IMAGE", lang)), null));

            // Dosage instructions (5 columns)
            cells.addAll(this.formatDosageCells(narDom, treatment, lang));

            // Date from to
            var dateFromTo = narDom.p(null);
            dateFromTo.appendChild(narDom.text(treatment.getTreatmentStart()));
            dateFromTo.appendChild(narDom.br());
            dateFromTo.appendChild(narDom.text(treatment.getTreatmentStop()));
            cells.add(narDom.td(dateFromTo, null));

            // Patient's instructions
            cells.add(narDom.td(treatment.getPatientMedicationInstructions(), null));

            // Reason
            cells.add(narDom.td(treatment.getTreatmentReason(), null));

            // Prescribed by
            cells.add(narDom.td(treatment.getSectionAuthor().getName(), null));

            // Comments
            if (treatment.getAnnotationComment() != null) {
                var td = narDom.td(null, "comments", null, "11");
                byte[] infoCircle = this.getResourceAsStream("/narrative/default/icons/info-circle.png").readAllBytes();
                td.appendChild(narDom.img("data:image/png;base64," + Base64.getEncoder().encodeToString(infoCircle), this.getMessage("COMMENT", lang)));
                td.appendChild(narDom.text(treatment.getAnnotationComment()));

                medicationTableRows.add(narDom.tr(cells, "border-top"));
                medicationTableRows.add(narDom.tr(td, null));
            } else {
                medicationTableRows.add(narDom.tr(cells, "border-top border-bottom"));
            }
        }

        if (medicationTableRows.isEmpty()) {
            final var td = narDom.td(narDom.em("Il n'y a pas de traitement actif"), "no-treatment");
            td.setAttribute("colspan", "12");
            medicationTableRows.add(narDom.tr(td, null));
        }

        return  medicationTableRows;
    }

    /**
     * Gets the {@link InputStream} of a resource.
     *
     * @param resource The resource path.
     * @return the non-null {@link InputStream} of the resource file.
     * @throws FileNotFoundException if the resource doesn't exist.
     */
    InputStream getResourceAsStream(final String resource) throws FileNotFoundException {
        final var is = PdfOriginalRepresentationGenerator.class.getResourceAsStream(resource);
        if (is == null) {
            throw new FileNotFoundException("The resource '" + resource + "' is not found");
        }
        return is;
    }

    /**
     * Gets the name of an author. It may be a person or a device.
     *
     * @param author
     * @return The author name.
     */
    Node formatAuthorName(final NarrativeDomFactory narDom,
                                final NarrativeTreatmentAuthor author,
                                final NarrativeLanguage lang) {

        final var p = narDom.p(null);
        p.appendChild(narDom.text(author.getName()));
        p.appendChild(narDom.br());

        if (author.getOrganization() != null) {
            p.appendChild(narDom.text(author.getOrganization()));
            p.appendChild(narDom.br());
        }

        if (author.getAddress() != null && !author.getAddress().isEmpty()) {
            p.appendChild(narDom.text(author.getAddress()));
            p.appendChild(narDom.br());
        }

        return p;
    }

    /**
     * Gets information on the dosage of the medicine
     *
     * @param narDom
     * @param item The medicine
     * @param lang
     * @return
     */
    List<Element> formatDosageCells(final NarrativeDomFactory narDom,
                                    final NarrativeTreatmentItem item,
                                    final NarrativeLanguage lang) {
        if (item.getNarrativeDosageInstructions() != null) {
            final var td = narDom.td(item.getNarrativeDosageInstructions(), "col dosage");
            td.setAttribute("colspan", "5");
            return List.of(td);
        }
        final var cells = new ArrayList<Element>(5);

        cells.add(displayQuantity(narDom, item.getDosageIntakeMorning()));
        cells.add(displayQuantity(narDom, item.getDosageIntakeNoon()));
        cells.add(displayQuantity(narDom, item.getDosageIntakeEvening()));
        cells.add(displayQuantity(narDom, item.getDosageIntakeNight()));

        cells.add(narDom.td(narDom.text(item.getDosageUnit()), "unit"));
        return cells;
    }

    /**
     * Gets dosage of the medicine
     *
     * @param narDom
     * @param quantity
     * @return
     */
    Element displayQuantity(NarrativeDomFactory narDom, String quantity) {
        if (quantity == null || quantity.equals("")) {
            return narDom.td("0", null);
        } else {
            return narDom.td(narDom.text(quantity), null);
        }
    }
    
    Element formatPatientPersonalData(NarrativeDomFactory narDom, NarrativeTreatmentDocument document) {
        var patientPersonalData = narDom.div(null, "patient-data");
        patientPersonalData.appendChild(narDom.text(document.getPatientName()));
        patientPersonalData.appendChild(narDom.br());
        patientPersonalData.appendChild(narDom.text(String.format("%s, %s", document.getPatientBirthDate(), document.getPatientGender())));
        patientPersonalData.appendChild(narDom.br());
        patientPersonalData.appendChild(narDom.text(String.format("%s / %s", document.getPatientAddress(), document.getPatientContact())));
        return patientPersonalData;
    }

    Element formatHeader(NarrativeDomFactory narDom, NarrativeTreatmentDocument document, NarrativeLanguage lang) {
        var tr = narDom.tr(null, null);
        tr.appendChild(narDom.td(narDom.title1(StringUtils.capitalize(this.getMessage("TITLE", lang)), "title"), null));
        tr.appendChild(narDom.td(this.formatPatientPersonalData(narDom, document), null));
        var tdAuthor = narDom.td(null, null);
        tdAuthor.appendChild(narDom.title3(StringUtils.capitalize(this.getMessage("AUTHOR_DOCUMENT", lang)), null));
        tdAuthor.appendChild(this.formatAuthorName(narDom, document.getAuthor1(), lang));

        tr.appendChild(tdAuthor);

        final var table = narDom.table();
        table.appendChild(tr);
        return table;
    }
}
/*
 * Qu'est-ce qu'on inclut dans une medication card?
 *
 * Tableau colonnes:
 * ID: #1
 * Nom: nom + gtin, ou nom (préparation magistrale), ou préparation magistrale
 * Statut: suspendu, actif, ...
 * Dosage: | | | | si structuré et possible, sinon narratif.
 * Voie et route: texte
 *
 * Pour chaque médicament:
 * Tous les narratifs (Fulfillment Instructions utile?)
 * Auteur de section (et auteur de document?)
 */
