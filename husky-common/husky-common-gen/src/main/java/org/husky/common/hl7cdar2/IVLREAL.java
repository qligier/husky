/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://gitlab.com/ehealth-connector/api/wikis/Team/
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
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren.
// Generiert: 2020.07.09 um 01:07:39 PM CEST
//

package org.husky.common.hl7cdar2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java-Klasse für IVL_REAL complex type.
 *
 * <p>
 * Das folgende Schemafragment gibt den erwarteten Content an, der in dieser
 * Klasse enthalten ist.
 *
 * <pre>
 * &lt;complexType name="IVL_REAL">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:v3}SXCM_REAL">
 *       &lt;choice minOccurs="0">
 *         &lt;sequence>
 *           &lt;element name="low" type="{urn:hl7-org:v3}IVXB_REAL"/>
 *           &lt;choice minOccurs="0">
 *             &lt;element name="width" type="{urn:hl7-org:v3}REAL" minOccurs="0"/>
 *             &lt;element name="high" type="{urn:hl7-org:v3}IVXB_REAL" minOccurs="0"/>
 *           &lt;/choice>
 *         &lt;/sequence>
 *         &lt;element name="high" type="{urn:hl7-org:v3}IVXB_REAL"/>
 *         &lt;sequence>
 *           &lt;element name="width" type="{urn:hl7-org:v3}REAL"/>
 *           &lt;element name="high" type="{urn:hl7-org:v3}IVXB_REAL" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element name="center" type="{urn:hl7-org:v3}REAL"/>
 *           &lt;element name="width" type="{urn:hl7-org:v3}REAL" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IVL_REAL", propOrder = { "rest" })
public class IVLREAL extends SXCMREAL {

	@XmlElementRefs({
			@XmlElementRef(name = "low", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false),
			@XmlElementRef(name = "width", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false),
			@XmlElementRef(name = "high", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false),
			@XmlElementRef(name = "center", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false) })
	protected List<JAXBElement<? extends REAL>> rest;

	/**
	 * Ruft das restliche Contentmodell ab.
	 *
	 * <p>
	 * Sie rufen diese "catch-all"-Eigenschaft aus folgendem Grund ab: Der
	 * Feldname "High" wird von zwei verschiedenen Teilen eines Schemas
	 * verwendet. Siehe: Zeile 916 von
	 * file:/C:/src/git/ehc-trunk/common/husky-common-gen/src/main/resources/schemas/hl7cdar2/coreschemas/datatypes.xsd
	 * Zeile 907 von
	 * file:/C:/src/git/ehc-trunk/common/husky-common-gen/src/main/resources/schemas/hl7cdar2/coreschemas/datatypes.xsd
	 * <p>
	 * Um diese Eigenschaft zu entfernen, wenden Sie eine Eigenschaftenanpassung
	 * für eine der beiden folgenden Deklarationen an, um deren Namen zu ändern:
	 * Gets the value of the rest property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the rest property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows: <pre>
	 *    getRest().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link JAXBElement }{@code <}{@link IVXBREAL }{@code >}
	 * {@link JAXBElement }{@code <}{@link REAL }{@code >} {@link JAXBElement
	 * }{@code <}{@link IVXBREAL }{@code >} {@link JAXBElement
	 * }{@code <}{@link REAL }{@code >}
	 *
	 *
	 */
	public List<JAXBElement<? extends REAL>> getRest() {
		if (rest == null) {
			rest = new ArrayList<JAXBElement<? extends REAL>>();
		}
		return this.rest;
	}

}