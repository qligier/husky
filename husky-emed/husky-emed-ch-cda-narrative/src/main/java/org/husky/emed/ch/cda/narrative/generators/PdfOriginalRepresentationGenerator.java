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
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.StringSubstitutor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.emed.ch.cda.narrative.NarrativeTreatmentAuthor;
import org.husky.emed.ch.cda.narrative.NarrativeTreatmentDocument;
import org.husky.emed.ch.cda.narrative.NarrativeTreatmentItem;
import org.husky.emed.ch.cda.narrative.enums.NarrativeLanguage;
import org.husky.emed.ch.cda.narrative.pdf.HtmlToPdfAConverter;
import org.husky.emed.ch.cda.narrative.pdf.OpenHtmlToPdfAConverter;
import org.husky.emed.ch.cda.narrative.utils.NarrativeUtils;
import org.husky.emed.ch.enums.TimingEventAmbu;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
        return this.generate(document, lang, null, null, null);
    }

    /**
     * @param document
     * @param lang           The language to use to generate the narrative text.
     * @param variables
     * @param templateHeader The HTML template header (before the main content).
     * @param templateFooter The HTML template footer (after the main content).
     * @return
     */
    public byte[] generate(final NarrativeTreatmentDocument document,
                           final NarrativeLanguage lang,
                           @Nullable Map<String, String> variables,
                           @Nullable String templateHeader,
                           @Nullable String templateFooter) throws Exception {
        Objects.requireNonNull(document, "digest shall not be null in generate()");
        Objects.requireNonNull(lang, "lang shall not be null in generate()");

        if (variables == null) {
            variables = new HashMap<>(64);
        }
        variables.putIfAbsent("title", "Carte de médication");
        variables.putIfAbsent("subject", "");
        variables.putIfAbsent("author", this.author);
        variables.putIfAbsent("description", "");
        variables.putIfAbsent("lang", lang.getLanguageCode().getCodeValue());
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

        // Document title
        root.appendChild(narDom.title1(variables.getOrDefault("title", "Carte de médication"), "title"));

        // Medication table
        final var medicationTableRows = new ArrayList<Element>();
        int i = 1;
        for (final var treatment : document.getActiveTreatments()) {
            final var cells = new ArrayList<Element>();

            // Link to medication details
            final var tdLink = narDom.td(null, "col n");
            tdLink.appendChild(narDom.link("#entry-" + i, "#" + i, "Voir les détails", null));
            cells.add(tdLink);

            // Medication name
            cells.add(narDom.td(this.formatMedicationName(narDom, treatment, lang), "col name"));

            // Dosage instructions (5 columns)
            cells.addAll(this.formatDosageCells(narDom, treatment, lang));

            // Route and approach site
            final Node routeSite = Optional.ofNullable(treatment.getRouteOfAdministration())
                    .map(narDom::text)
                    .orElseGet(() -> narDom.span("N/A", "na"));
            cells.add(narDom.td(routeSite, "col route-site"));

            medicationTableRows.add(narDom.tr(cells));
            ++i;
        }
        if (medicationTableRows.isEmpty()) {
            final var td = narDom.td(narDom.em("Il n'y a pas de traitement actif"), "no-treatment");
            td.setAttribute("colspan", "8");
            medicationTableRows.add(narDom.tr(td));
        }
        root.appendChild(this.createMedicationTable(narDom, medicationTableRows, lang));

        // Medication details
        i = 1;
        for (final var treatment : document.getActiveTreatments()) {
            final var hr = narDom.getDocument().createElement("hr");
            hr.setAttribute("id", "entry-" + i);
            root.appendChild(hr);

            root.appendChild(narDom.title2(List.of(
                    narDom.span(i, "n"),
                    this.formatMedicationName(narDom, treatment, lang)
            ), "med_" + i));

            // Last author
            root.appendChild(narDom.div(List.of(
                    narDom.title3("Dernier intervenant", null),
                    narDom.p(this.formatAuthorName(narDom, treatment.getSectionAuthor(), lang))
            ), "narrative last_author"));

            if (treatment.getTreatmentReason() != null) {
                root.appendChild(narDom.div(List.of(
                        narDom.title3("Raison du traitement", null),
                        narDom.p(StringEscapeUtils.escapeXml11(treatment.getTreatmentReason()))
                ), "narrative treatment_reason"));
            }
            if (treatment.getAnnotationComment() != null) {
                root.appendChild(narDom.div(List.of(
                        narDom.title3("Commentaire", null),
                        narDom.p(StringEscapeUtils.escapeXml11(treatment.getAnnotationComment()))
                ), "narrative annotation_comment"));
            }
            if (treatment.getPatientMedicationInstructions() != null) {
                root.appendChild(narDom.div(List.of(
                        narDom.title3("Instructions de médication", null),
                        narDom.p(StringEscapeUtils.escapeXml11(treatment.getPatientMedicationInstructions()))
                ), "narrative medication_instructions"));
            }
            if (treatment.getFulfilmentInstructions() != null) {
                root.appendChild(narDom.div(List.of(
                        narDom.title3("Instructions de fulfilment", null),
                        narDom.p(StringEscapeUtils.escapeXml11(treatment.getFulfilmentInstructions()))
                ), "narrative fulfilment_instructions"));
            }

            ++i;
        }

        root.appendChild(narDom.getDocument().createElement("hr"));
        root.appendChild(narDom.div(List.of(
                narDom.title3("Auteur du document", "document_author"),
                narDom.p(this.formatAuthorName(narDom, document.getAuthor1(), lang))
        ), "narrative document_author"));


        final var stringSubstitutor = new StringSubstitutor(variables);
        final String body = stringSubstitutor.replace(templateHeader)
                + narDom.render()
                + stringSubstitutor.replace(templateFooter);

        return this.pdfConverter.convert(body);
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
    List<Node> formatAuthorName(final NarrativeDomFactory narDom,
                                @Nullable NarrativeTreatmentAuthor author,
                                final NarrativeLanguage lang) {

        if (author == null) {
            return List.of(narDom.em("Inconnu"));
        }

        final var nodes = new ArrayList<Node>();
        if (author.getName() != null) {
            nodes.add(narDom.text(author.getName()));
        }

        if (nodes.isEmpty()) {
            nodes.add(narDom.em("Inconnu"));
        }
        nodes.add(narDom.br());

        if (!author.getAddress().isEmpty()) {
            final var address = author.getAddress();

            if (address != null) {
                nodes.add(narDom.text(address));
                nodes.add(narDom.br());
            }
        }

        return nodes;
    }

    List<Element> formatDosageCells(final NarrativeDomFactory narDom,
                                    final NarrativeTreatmentItem item,
                                    final NarrativeLanguage lang) {
        if (item.getNarrativeDosageInstructions() != null) {
            final var td = narDom.td(item.getNarrativeDosageInstructions(), "col dosage");
            td.setAttribute("colspan", "5");
            return List.of(td);
        }
        final var cells = new ArrayList<Element>(5);

        cells.add(displayQuantity(narDom, item.getDosageIntakeMorning(), item.getDosageUnit(), TimingEventAmbu.MORNING));
        cells.add(displayQuantity(narDom, item.getDosageIntakeNoon(), item.getDosageUnit(), TimingEventAmbu.NOON));
        cells.add(displayQuantity(narDom, item.getDosageIntakeEvening(), item.getDosageUnit(), TimingEventAmbu.EVENING));
        cells.add(displayQuantity(narDom, item.getDosageIntakeNight(), item.getDosageUnit(), TimingEventAmbu.NIGHT));

        cells.add(narDom.td(null, null));
        return cells;
    }

    private Element displayQuantity(NarrativeDomFactory narDom, String quantity, String unit, TimingEventAmbu timing) {
        if (quantity == null || quantity.equals("")) {
            return narDom.td(null, null);
        } else {
            return narDom.td(List.of(narDom.text(quantity + " "), narDom.span(unit, "unit")), null);
        }
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
