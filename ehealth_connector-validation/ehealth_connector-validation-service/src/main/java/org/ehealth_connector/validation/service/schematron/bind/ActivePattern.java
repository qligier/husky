/*
 * 
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 * 
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 * For exact developer information, please refer to the commit history of the forge.
 * 
 * This code is made available under the terms of the Eclipse Public License v1.0.
 * 
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 * 
 * This line is intended for UTF-8 encoding checks, do not modify/delete: äöüéè
 * 
 */
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2016.04.08 at 10:09:10 AM CEST
//

package org.ehealth_connector.validation.service.schematron.bind;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="role" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="document" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "active-pattern")
public class ActivePattern {

	@XmlAttribute(name = "id")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlID
	@XmlSchemaType(name = "ID")
	protected String id;
	@XmlAttribute(name = "name")
	@XmlSchemaType(name = "anySimpleType")
	protected String name;
	@XmlAttribute(name = "role")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NMTOKEN")
	protected String role;
	@XmlAttribute(name = "document")
	protected String document;

	/**
	 * Gets the value of the document property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getDocument() {
		return document;
	}

	/**
	 * Gets the value of the id property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the value of the name property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the value of the role property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the value of the document property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setDocument(String value) {
		this.document = value;
	}

	/**
	 * Sets the value of the id property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Sets the value of the name property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Sets the value of the role property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setRole(String value) {
		this.role = value;
	}

}
