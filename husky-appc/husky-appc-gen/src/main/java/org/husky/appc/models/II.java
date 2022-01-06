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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for II complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="II"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="root" use="required" type="{urn:hl7-org:v3}uid" /&gt;
 *       &lt;attribute name="extension" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "II", namespace = "urn:hl7-org:v3")
public class II {

    @XmlAttribute(name = "root", required = true)
    protected String root;
    @XmlAttribute(name = "extension")
    protected String extension;

    /**
     * Constructor.
     */
    public II() {
    }

    /**
     * Constructor.
     *
     * @param root The identifier root.
     */
    public II(final String root) {
        this.root = root;
    }

    /**
     * Constructor.
     *
     * @param root      The identifier root.
     * @param extension The identifier extension.
     */
    public II(final String root,
              final String extension) {
        this.root = root;
        this.extension = extension;
    }

    /**
     * Gets the value of the root property.
     *
     * @return possible object is {@link String }
     */
    public String getRoot() {
        return root;
    }

    /**
     * Sets the value of the root property.
     *
     * @param value allowed object is {@link String }
     */
    public void setRoot(String value) {
        this.root = value;
    }

    /**
     * Gets the value of the extension property.
     *
     * @return possible object is {@link String }
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     *
     * @param value allowed object is {@link String }
     */
    public void setExtension(String value) {
        this.extension = value;
    }

}
