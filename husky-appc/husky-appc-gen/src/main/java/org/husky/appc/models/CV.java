/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.appc.models;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.husky.common.enums.ValueSetEnumInterface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;


/**
 * <p>Java class for CV complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CV">
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="originalText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="code" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="codeSystem" use="required" type="{urn:hl7-org:v3}oid" /&gt;
 *       &lt;attribute name="codeSystemName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="codeSystemVersion" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="displayName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CV", namespace = "urn:hl7-org:v3", propOrder = {
        "originalText"
})
public class CV {

    protected String originalText;
    @XmlAttribute(name = "code", required = true)
    protected String code;
    @XmlAttribute(name = "codeSystem", required = true)
    protected String codeSystem;
    @XmlAttribute(name = "codeSystemName")
    protected String codeSystemName;
    @XmlAttribute(name = "codeSystemVersion")
    protected String codeSystemVersion;
    @XmlAttribute(name = "displayName")
    protected String displayName;

    public CV() {
    }

    public CV(final String code,
              final String codeSystem) {
        this.code = code;
        this.codeSystem = codeSystem;
    }

    public CV(final String code,
              final String codeSystem,
              final String displayName) {
        this.code = code;
        this.codeSystem = codeSystem;
        this.displayName = displayName;
    }

    public CV(@NonNull final ValueSetEnumInterface valueSetEnum) {
        this.code = Objects.requireNonNull(valueSetEnum).getCodeValue();
        this.codeSystem = valueSetEnum.getCodeSystemId();
        this.displayName = valueSetEnum.getDisplayName();
    }

    /**
     * Gets the value of the originalText property.
     *
     * @return possible object is {@link String }
     */
    public String getOriginalText() {
        return originalText;
    }

    /**
     * Sets the value of the originalText property.
     *
     * @param value allowed object is {@link String }
     */
    public void setOriginalText(String value) {
        this.originalText = value;
    }

    /**
     * Gets the value of the code property.
     *
     * @return possible object is {@link String }
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the codeSystem property.
     *
     * @return possible object is {@link String }
     */
    public String getCodeSystem() {
        return codeSystem;
    }

    /**
     * Sets the value of the codeSystem property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCodeSystem(String value) {
        this.codeSystem = value;
    }

    /**
     * Gets the value of the codeSystemName property.
     *
     * @return possible object is {@link String }
     */
    public String getCodeSystemName() {
        return codeSystemName;
    }

    /**
     * Sets the value of the codeSystemName property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCodeSystemName(String value) {
        this.codeSystemName = value;
    }

    /**
     * Gets the value of the codeSystemVersion property.
     *
     * @return possible object is {@link String }
     */
    public String getCodeSystemVersion() {
        return codeSystemVersion;
    }

    /**
     * Sets the value of the codeSystemVersion property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCodeSystemVersion(String value) {
        this.codeSystemVersion = value;
    }

    /**
     * Gets the value of the displayName property.
     *
     * @return possible object is {@link String }
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     *
     * @param value allowed object is {@link String }
     */
    public void setDisplayName(String value) {
        this.displayName = value;
    }

}
