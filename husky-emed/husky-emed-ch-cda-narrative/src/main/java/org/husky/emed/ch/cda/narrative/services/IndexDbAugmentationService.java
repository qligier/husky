package org.husky.emed.ch.cda.narrative.services;

import org.apache.commons.io.IOUtils;
import org.apache.xerces.impl.dv.util.Base64;
import org.husky.emed.ch.cda.narrative.enums.NarrativeLanguage;
import org.husky.emed.ch.cda.narrative.enums.ProductCodeType;
import org.husky.emed.ch.cda.narrative.treatment.NarrativeTreatmentIngredient;
import org.husky.emed.ch.cda.narrative.treatment.NarrativeTreatmentItem;
import org.husky.emed.ch.enums.RouteOfAdministrationEdqm;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Adds information to a {@link NarrativeTreatmentItem} using the INDEX database
 *
 * @author Ronaldo Loureiro
 */
public class IndexDbAugmentationService {

    private String url;
    private String username;
    private String password;

    /**
     * Constructor
     * @param url the url of the database
     * @param username the username for the database connection
     * @param password the user's password
     */
    public IndexDbAugmentationService(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * Adds information to the item using the INDEX database
     * @param item the narrative item to be improved
     */
    public void augment(NarrativeTreatmentItem item, NarrativeLanguage lang) {
        if (item.getCodeType() == ProductCodeType.GTIN) {
            final String gtin = item.getProductCode();

            if (item.getRouteOfAdministration() == null) {
                Optional<RouteOfAdministrationEdqm> roa = this.getRouteOfAdministration(gtin);
                if (roa.isPresent()) {
                    try {
                        ValueSetEnumNarrativeForPatientService valueSetEnumNarrative = new ValueSetEnumNarrativeForPatientService();
                        item.setRouteOfAdministration(valueSetEnumNarrative.getMessage(roa.get(), lang));
                    } catch (IOException e) {
                        item.setRouteOfAdministration(roa.get().getDisplayName(lang.getLanguageCode()));
                    }
                }
            }

            if (item.getProductIngredients().size() == 0) {
                List<NarrativeTreatmentIngredient> activeIngredients = this.getActiveIngredients(gtin);
                item.setProductIngredients(activeIngredients);
            }

            this.getProductName(gtin).ifPresent(item::setProductName);

            if (this.hasProductImage(gtin)) {
                this.getImage(String.format("https://index.hcisolutions.ch/files/pics/%s_PIF_F.jpg?key=gtin", gtin))
                        .ifPresent(item::setProductImageFront);
                this.getImage(String.format("https://index.hcisolutions.ch/files/pics/%s_PIB_F.jpg?key=gtin", gtin))
                        .ifPresent(item::setProductImageBack);
            }
        }
    }

    /**
     * Gets the database connection
     *
     * @return
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.username, this.password);
    }

    /**
     * Gets the route of administration of a medicine using its GTIN
     * @param gtin The medecine's GTIN
     *
     * @return The route of administration if available
     */
    private Optional<RouteOfAdministrationEdqm> getRouteOfAdministration(String gtin) {
        String sqlSelectRouteOfAdministration = "SELECT PRODUCT.EDQMROA AS ROA FROM ARTICLE INNER JOIN PRODUCT ON ARTICLE.PRDNO = PRODUCT.PRDNO WHERE ARTICLE.GTIN = ?";

        try(Connection conn = this.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sqlSelectRouteOfAdministration);
            ps.setString(1, gtin);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String code = rs.getString("ROA");
                if (RouteOfAdministrationEdqm.isInValueSet(code)) {
                    return Optional.ofNullable(RouteOfAdministrationEdqm.getEnum(code));
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    /**
     * Gets the active ingredients of a medicine
     * @param gtin The medecine's GTIN
     *
     * @return The list with the active ingredient
     */
    private List<NarrativeTreatmentIngredient> getActiveIngredients(String gtin) {
        String sqlSelectActiveIngredient = "SELECT SUBSTANCE.NAMF AS NAME, PRODUCT.QTY as QTY, PRODUCT.QTYU as QTYU FROM (ARTICLE INNER JOIN PRODUCT ON ARTICLE.PRDNO = PRODUCT.PRDNO) INNER JOIN SUBSTANCE ON PRODUCT.SUBNO = SUBSTANCE.SUBNO WHERE ARTICLE.GTIN = ? AND PRODUCT.WHK = 'W'";

        try(Connection conn = this.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sqlSelectActiveIngredient);
            ps.setString(1, gtin);

            ResultSet rs = ps.executeQuery();
            var activeIngredients = new ArrayList<NarrativeTreatmentIngredient>();
            while (rs.next()) {
                String substanceName = rs.getString("NAME");
                String substanceQty = rs.getString("QTY");
                String substanceUnit = rs.getString("QTYU");
                activeIngredients.add(new NarrativeTreatmentIngredient(substanceName, substanceQty, substanceUnit));
            }
            return activeIngredients;
        } catch (SQLException e) {
            return List.of();
        }
    }

    /**
     * Checks if images of the medicine are available
     * @param gtin the medicine's GTIN
     *
     * @return true if images are available otherwise false
     */
    private boolean hasProductImage(String gtin) {
        String sqlSelectImg2 = "SELECT PRODUCT.IMG2 as IMG FROM ARTICLE INNER JOIN PRODUCT ON ARTICLE.PRDNO = PRODUCT.PRDNO WHERE ARTICLE.GTIN = ?";

        try(Connection conn = this.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sqlSelectImg2);
            ps.setString(1, gtin);

            ResultSet rs = ps.executeQuery();
            boolean hasProductImage = false;
            while (rs.next()) {
                hasProductImage = rs.getBoolean("IMG");
            }
            return hasProductImage;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Gets the name of medicine
     * @param gtin the medicine's gtin
     * @return the medicine's name
     */
    private Optional<String> getProductName(String gtin) {
        String sqlSelectName = "SELECT PRODUCT.BNAMF AS BRAND, PRODUCT.ADNAMF AS ADNAME FROM ARTICLE INNER JOIN PRODUCT ON ARTICLE.PRDNO = PRODUCT.PRDNO WHERE ARTICLE.GTIN = ? LIMIT 1";

        try(Connection conn = this.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sqlSelectName);
            ps.setString(1, gtin);

            ResultSet rs = ps.executeQuery();
            rs.next();

            String brand = rs.getString("BRAND");
            String restName = rs.getString("ADNAME");
            return Optional.of(String.format("%s %s", brand, restName));

        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    private Optional<String> getImage(String url) {
        try {
            InputStream inputStream = new URL(url).openStream();
            String b64Img = Base64.encode(IOUtils.toByteArray(inputStream));
            inputStream.close();
            return Optional.of("data:image/png;base64," + b64Img);
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
