package org.ehealth_connector.emed.cda.generated.artdecor;

import java.util.List;
import javax.annotation.processing.Generated;
import org.ehealth_connector.emed.cda.generated.hl7cdar2.ObjectFactory;
import org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040Participant1;

/**
 * cdach_header_PatientContact
 * <p>
 * Template description: Information on a patient contact. CDA-CH V2 derivatives, i.e. Swiss exchange formats MAY use this template by either reference or specialisation.<br>
 * Element description: Information on a patient contact.<br>
 * <p>
 * Identifier: 2.16.756.5.30.1.1.10.2.43<br>
 * Effective date: 2019-11-19 11:59:25<br>
 * Version: 2020<br>
 * Status: active
 */
@Generated(value = "org.ehealth_connector.codegenerator.cda.ArtDecor2JavaGenerator", date = "2021-09-08")
public class CdachHeaderPatientContact extends POCDMT000040Participant1 {

    public CdachHeaderPatientContact() {
        super.getTypeCode().add("IND");
        super.getTemplateId().add(createHl7TemplateIdFixedValue("2.16.756.5.30.1.1.10.2.43"));
        super.getTemplateId().add(createHl7TemplateIdFixedValue("1.3.6.1.4.1.19376.1.5.3.1.2.4"));
    }

    /**
     * Creates fixed contents for CDA Element hl7TemplateId
     *
     * @param root the desired fixed value for this argument.
     */
    private static org.ehealth_connector.emed.cda.generated.hl7cdar2.II createHl7TemplateIdFixedValue(String root) {
        ObjectFactory factory = new ObjectFactory();
        org.ehealth_connector.emed.cda.generated.hl7cdar2.II retVal = factory.createII();
        retVal.setRoot(root);
        return retVal;
    }

    /**
     * Gets the hl7AssociatedEntity
     * Either the contact person or the contact's organization SHALL be present.
     */
    public org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040AssociatedEntity getHl7AssociatedEntity() {
        return associatedEntity;
    }

    /**
     * Gets the hl7TemplateId
     */
    public List<org.ehealth_connector.emed.cda.generated.hl7cdar2.II> getHl7TemplateId() {
        return templateId;
    }

    /**
     * Gets the hl7Time
     * Validity period of the participation.
     */
    public org.ehealth_connector.emed.cda.generated.hl7cdar2.IVLTS getHl7Time() {
        return time;
    }

    /**
     * Sets the hl7AssociatedEntity
     * Either the contact person or the contact's organization SHALL be present.
     */
    public void setHl7AssociatedEntity(org.ehealth_connector.emed.cda.generated.hl7cdar2.POCDMT000040AssociatedEntity value) {
        this.associatedEntity = value;
    }

    /**
     * Sets the hl7TemplateId
     */
    public void setHl7TemplateId(org.ehealth_connector.emed.cda.generated.hl7cdar2.II value) {
        getTemplateId().clear();
        getTemplateId().add(value);
    }

    /**
     * Sets the hl7Time
     * Validity period of the participation.
     */
    public void setHl7Time(org.ehealth_connector.emed.cda.generated.hl7cdar2.IVLTS value) {
        this.time = value;
    }
}
