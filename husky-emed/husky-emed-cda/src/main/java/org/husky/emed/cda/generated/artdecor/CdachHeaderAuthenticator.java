package org.husky.emed.cda.generated.artdecor;

import java.util.List;
import javax.annotation.processing.Generated;

import org.husky.emed.cda.generated.hl7cdar2.ObjectFactory;
import org.husky.emed.cda.generated.hl7cdar2.POCDMT000040Authenticator;

/**
 * cdach_header_Authenticator
 * <p>
 * Template description: Information about an authenticator of a CDA document. An authenticator MUST be a person. All CDA-CH V2 derivatives, i.e. Swiss exchange formats MUST use this template by either reference or specialisation.<br>
 * Element description: Information about an authenticator of a CDA document. An authenticator MUST be a person.<br>
 * <p>
 * Identifier: 2.16.756.5.30.1.1.10.2.6<br>
 * Effective date: 2019-01-01 20:20:44<br>
 * Version: 2020<br>
 * Status: active
 */
@Generated(value = "org.ehealth_connector.codegenerator.cda.ArtDecor2JavaGenerator", date = "2021-09-08")
public class CdachHeaderAuthenticator extends POCDMT000040Authenticator {

    public CdachHeaderAuthenticator() {
        super.getTemplateId().add(createHl7TemplateIdFixedValue("2.16.756.5.30.1.1.10.2.6"));
        super.setSignatureCode(createHl7SignatureCodeFixedValue("S"));
    }

    /**
     * Creates fixed contents for CDA Element hl7SignatureCode
     *
     * @param code the desired fixed value for this argument.
     */
    private static org.husky.emed.cda.generated.hl7cdar2.CS createHl7SignatureCodeFixedValue(String code) {
        ObjectFactory factory = new ObjectFactory();
        org.husky.emed.cda.generated.hl7cdar2.CS retVal = factory.createCS();
        retVal.setCode(code);
        return retVal;
    }

    /**
     * Creates fixed contents for CDA Element hl7TemplateId
     *
     * @param root the desired fixed value for this argument.
     */
    private static org.husky.emed.cda.generated.hl7cdar2.II createHl7TemplateIdFixedValue(String root) {
        ObjectFactory factory = new ObjectFactory();
        org.husky.emed.cda.generated.hl7cdar2.II retVal = factory.createII();
        retVal.setRoot(root);
        return retVal;
    }

    /**
     * Gets the hl7AssignedEntity
     */
    public org.husky.emed.cda.generated.hl7cdar2.POCDMT000040AssignedEntity getHl7AssignedEntity() {
        return assignedEntity;
    }

    /**
     * Gets the hl7SignatureCode
     */
    public org.husky.emed.cda.generated.hl7cdar2.CS getHl7SignatureCode() {
        return signatureCode;
    }

    /**
     * Gets the hl7TemplateId
     */
    public List<org.husky.emed.cda.generated.hl7cdar2.II> getHl7TemplateId() {
        return templateId;
    }

    /**
     * Gets the hl7Time
     * Timestamp of the signature.
     */
    public org.husky.emed.cda.generated.hl7cdar2.TS getHl7Time() {
        return time;
    }

    /**
     * Sets the hl7AssignedEntity
     */
    public void setHl7AssignedEntity(org.husky.emed.cda.generated.hl7cdar2.POCDMT000040AssignedEntity value) {
        this.assignedEntity = value;
    }

    /**
     * Sets the hl7SignatureCode
     */
    public void setHl7SignatureCode(org.husky.emed.cda.generated.hl7cdar2.CS value) {
        this.signatureCode = value;
    }

    /**
     * Sets the hl7TemplateId
     */
    public void setHl7TemplateId(org.husky.emed.cda.generated.hl7cdar2.II value) {
        getTemplateId().clear();
        getTemplateId().add(value);
    }

    /**
     * Sets the hl7Time
     * Timestamp of the signature.
     */
    public void setHl7Time(org.husky.emed.cda.generated.hl7cdar2.TS value) {
        this.time = value;
    }
}