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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Objects;

/**
 * This class provides a marshaller implementation to convert APPC documents to their XML representation.
 *
 * @author Quentin Ligier
 **/
public class AppcMarshaller {

    /**
     * This class is not instantiable.
     */
    private AppcMarshaller() {
    }

    /**
     * Marshalles an APPC document to a {@link String}.
     *
     * @param appcDocument The APPC document to marshall.
     * @return the XML representation of the {@code appcDocument}.
     * @throws JAXBException if an error was encountered while creating the marshaller.
     */
    public static String marshall(final PolicySetType appcDocument) throws JAXBException {
        Objects.requireNonNull(appcDocument);
        final var jaxbContext = JAXBContext.newInstance(PolicySetType.class);
        final var marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // Remove the marshaller XML declaration

        final var writer = new StringWriter();
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        marshaller.marshal(appcDocument, writer);
        return writer.toString();
    }
}
