/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 *
 */

package org.husky.appc.xml;

import org.husky.appc.models.PolicySetType;
import org.husky.common.utils.xml.XmlUnmarshaller;
import org.xml.sax.InputSource;

import javax.xml.bind.DataBindingException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.util.Objects;

/**
 * This class provides an unmarshaller implementation to read APPC documents from their XML representation.
 *
 * @author Quentin Ligier
 **/
public class AppcUnmarshaller {

    /**
     * This class is not instantiable.
     */
    private AppcUnmarshaller() {
    }

    /**
     * Unmarshalles an APPC document as a {@link PolicySetType} object.
     *
     * @param appcContent The APPC content as a string.
     * @return the unmarshalled APPC document.
     * @throws DataBindingException         if the unmarshalling fails because of the content.
     * @throws ParserConfigurationException if the unmarshalling fails because of the parser.
     */
    public static PolicySetType unmarshall(final String appcContent) throws DataBindingException, ParserConfigurationException {
        return XmlUnmarshaller.unmarshallStringAsType(Objects.requireNonNull(appcContent), PolicySetType.class);
    }

    /**
     * Unmarshalles an APPC document as a {@link PolicySetType} object.
     *
     * @param appcInputStream The APPC content as an {@link InputStream}.
     * @return the unmarshalled APPC document.
     * @throws DataBindingException         if the unmarshalling fails because of the content.
     * @throws ParserConfigurationException if the unmarshalling fails because of the parser.
     */
    public static PolicySetType unmarshall(final InputStream appcInputStream) throws DataBindingException, ParserConfigurationException {
        return XmlUnmarshaller.unmarshallAsType(new InputSource(Objects.requireNonNull(appcInputStream)),
                PolicySetType.class);
    }

    /**
     * Unmarshalles an APPC document as a {@link PolicySetType} object.
     *
     * @param inputSource The APPC content as an {@link InputSource}.
     * @return the unmarshalled APPC document.
     * @throws DataBindingException         if the unmarshalling fails because of the content.
     * @throws ParserConfigurationException if the unmarshalling fails because of the parser.
     */
    public static PolicySetType unmarshall(final InputSource inputSource) throws DataBindingException, ParserConfigurationException {
        return XmlUnmarshaller.unmarshallAsType(Objects.requireNonNull(inputSource), PolicySetType.class);
    }
}
