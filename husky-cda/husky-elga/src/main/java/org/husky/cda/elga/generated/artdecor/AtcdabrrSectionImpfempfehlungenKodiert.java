/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.cda.elga.generated.artdecor;

import java.util.List;

import javax.annotation.processing.Generated;

import org.husky.common.hl7cdar2.ObjectFactory;
import org.husky.common.hl7cdar2.POCDMT000040Section;

/**
 * atcdabrr_section_ImpfempfehlungenKodiert
 * Identifier: 1.2.40.0.34.6.0.11.2.2<br>
 * Effective date: 2021-08-26 15:09:41<br>
 * Version: 1.0.1+20220103<br>
 * Status: active
 */
@Generated(value = "org.husky.codegenerator.cda.ArtDecor2JavaGenerator", date = "2022-02-18")
public class AtcdabrrSectionImpfempfehlungenKodiert extends POCDMT000040Section {

    public AtcdabrrSectionImpfempfehlungenKodiert() {
        super.getClassCode().add("DOCSECT");
        super.getMoodCode().add("EVN");
        super.getTemplateId().add(createHl7TemplateIdFixedValue("1.2.40.0.34.6.0.11.2.2"));
        super.getTemplateId().add(createHl7TemplateIdFixedValue("2.16.840.1.113883.10.20.1.6"));
        super.getTemplateId().add(createHl7TemplateIdFixedValue("1.3.6.1.4.1.19376.1.5.3.1.3.23"));
        super.setCode(createHl7CodeFixedValue("18776-5",
                                              "2.16.840.1.113883.6.1",
                                              null,
                                              "TREATMENT PLAN"));
        super.getEntry().add(createHl7EntryFixedValue("DRIV",
                                                      "true"));
    }

    /**
     * Adds a hl7Component
     */
    public void addHl7Component(org.husky.common.hl7cdar2.POCDMT000040Component5 value) {
        getComponent().add(value);
    }

    /**
     * Adds a hl7Entry
     */
    public void addHl7Entry(org.husky.common.hl7cdar2.POCDMT000040Entry value) {
        getEntry().add(value);
    }

    /**
     * Adds a hl7Component
     */
    public void clearHl7Component() {
        getComponent().clear();
    }

    /**
     * Adds a hl7Entry
     */
    public void clearHl7Entry() {
        getEntry().clear();
    }

    /**
     * Creates fixed contents for CDA Element hl7Code
     *
     * @param code the desired fixed value for this argument.
     */
    private static org.husky.common.hl7cdar2.CE createHl7CodeFixedValue(String code, String codeSystem, String codeSystemName, String displayName) {
        ObjectFactory factory = new ObjectFactory();
        org.husky.common.hl7cdar2.CE retVal = factory.createCE();
        retVal.setCode(code);
        retVal.setCodeSystem(codeSystem);
        retVal.setCodeSystemName(codeSystemName);
        retVal.setDisplayName(displayName);
        return retVal;
    }

    /**
     * Creates fixed contents for CDA Element hl7Component
     *
     * @param typeCode the desired fixed value for this argument.
     * @param contextConductionInd the desired fixed value for this argument.
     */
    private static org.husky.common.hl7cdar2.POCDMT000040Component5 createHl7ComponentFixedValue(String typeCode, String contextConductionInd) {
        ObjectFactory factory = new ObjectFactory();
        org.husky.common.hl7cdar2.POCDMT000040Component5 retVal = factory.createPOCDMT000040Component5();
        retVal.setTypeCode(org.husky.common.hl7cdar2.ActRelationshipHasComponent.fromValue(typeCode));
        if (contextConductionInd != null) {
            retVal.setContextConductionInd(Boolean.parseBoolean(contextConductionInd));
        }
        return retVal;
    }

    /**
     * Creates fixed contents for CDA Element hl7Entry
     *
     * @param typeCode the desired fixed value for this argument.
     * @param contextConductionInd the desired fixed value for this argument.
     */
    private static org.husky.common.hl7cdar2.POCDMT000040Entry createHl7EntryFixedValue(String typeCode, String contextConductionInd) {
        ObjectFactory factory = new ObjectFactory();
        org.husky.common.hl7cdar2.POCDMT000040Entry retVal = factory.createPOCDMT000040Entry();
        retVal.setTypeCode(org.husky.common.hl7cdar2.XActRelationshipEntry.fromValue(typeCode));
        if (contextConductionInd != null) {
            retVal.setContextConductionInd(Boolean.parseBoolean(contextConductionInd));
        }
        return retVal;
    }

    /**
     * Creates fixed contents for CDA Element hl7TemplateId
     *
     * @param root the desired fixed value for this argument.
     */
    private static org.husky.common.hl7cdar2.II createHl7TemplateIdFixedValue(String root) {
        ObjectFactory factory = new ObjectFactory();
        org.husky.common.hl7cdar2.II retVal = factory.createII();
        retVal.setRoot(root);
        return retVal;
    }

    /**
     * Gets the hl7Code
     */
    public org.husky.common.hl7cdar2.CE getHl7Code() {
        return code;
    }

    /**
     * Gets the hl7Id
     */
    public org.husky.common.hl7cdar2.II getHl7Id() {
        return id;
    }

    /**
     * Gets the hl7TemplateId
     */
    public List<org.husky.common.hl7cdar2.II> getHl7TemplateId() {
        return templateId;
    }

    /**
     * Gets the hl7Text
     */
    public org.husky.common.hl7cdar2.StrucDocText getHl7Text() {
        return text;
    }

    /**
     * Gets the hl7Title
     */
    public org.husky.common.hl7cdar2.ST getHl7Title() {
        return title;
    }

    /**
     * Adds a predefined org.husky.common.hl7cdar2.POCDMT000040Component5, filled by: "COMP", "true"
     * @return the predefined element.
     */
    public static org.husky.common.hl7cdar2.POCDMT000040Component5 getPredefinedComponentCompTrue() {
        return createHl7ComponentFixedValue("COMP",
                                            "true");
    }

    /**
     * Sets the hl7Code
     */
    public void setHl7Code(org.husky.common.hl7cdar2.CE value) {
        this.code = value;
    }

    /**
     * Sets the hl7Id
     */
    public void setHl7Id(org.husky.common.hl7cdar2.II value) {
        this.id = value;
    }

    /**
     * Sets the hl7TemplateId
     */
    public void setHl7TemplateId(org.husky.common.hl7cdar2.II value) {
        getTemplateId().clear();
        getTemplateId().add(value);
    }

    /**
     * Sets the hl7Text
     */
    public void setHl7Text(org.husky.common.hl7cdar2.StrucDocText value) {
        this.text = value;
    }

    /**
     * Sets the hl7Title
     */
    public void setHl7Title(org.husky.common.hl7cdar2.ST value) {
        this.title = value;
    }
}