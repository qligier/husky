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

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EffectType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EffectType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Permit"/&gt;
 *     &lt;enumeration value="Deny"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "EffectType")
@XmlEnum
public enum EffectType {

    @XmlEnumValue("Permit")
    PERMIT("Permit"),
    @XmlEnumValue("Deny")
    DENY("Deny");
    private final String value;

    EffectType(String v) {
        value = v;
    }

    @NonNull
    public static EffectType fromValue(String v) {
        for (EffectType c : EffectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String value() {
        return value;
    }

}
