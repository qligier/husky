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

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for IdReferenceType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="IdReferenceType"&gt;
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
 *       &lt;attribute name="Version" type="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}VersionMatchType" /&gt;
 *       &lt;attribute name="EarliestVersion" type="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}VersionMatchType" /&gt;
 *       &lt;attribute name="LatestVersion" type="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}VersionMatchType" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent>
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdReferenceType", propOrder = {
        "value"
})
public class IdReferenceType {

    @XmlValue
    @XmlSchemaType(name = "anyURI")
    protected String value;
    @XmlAttribute(name = "Version")
    protected String version;
    @XmlAttribute(name = "EarliestVersion")
    protected String earliestVersion;
    @XmlAttribute(name = "LatestVersion")
    protected String latestVersion;

    public IdReferenceType() {
    }

    public IdReferenceType(final String value) {
        this.value = value;
    }

    /**
     * Gets the value of the value property.
     *
     * @return possible object is {@link String }
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is {@link String }
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the version property.
     *
     * @return possible object is {@link String }
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     *
     * @param value allowed object is {@link String }
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the earliestVersion property.
     *
     * @return possible object is {@link String }
     */
    public String getEarliestVersion() {
        return earliestVersion;
    }

    /**
     * Sets the value of the earliestVersion property.
     *
     * @param value allowed object is {@link String }
     */
    public void setEarliestVersion(String value) {
        this.earliestVersion = value;
    }

    /**
     * Gets the value of the latestVersion property.
     *
     * @return possible object is {@link String }
     */
    public String getLatestVersion() {
        return latestVersion;
    }

    /**
     * Sets the value of the latestVersion property.
     *
     * @param value allowed object is {@link String }
     */
    public void setLatestVersion(String value) {
        this.latestVersion = value;
    }

}
