/*******************************************************************************
 *
 * The authorship of this code and the accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. http://medshare.net
 *
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 *
 * This code is are made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * Year of publication: 2015
 *
 *******************************************************************************/

package org.ehealth_connector.common;

import org.ehealth_connector.cda.enums.AddressUse;
import org.ehealth_connector.cda.enums.AdministrativeGender;
import org.ehealth_connector.communication.DocDescriptor;
import org.ehealth_connector.communication.ch.enums.CodedMetadataEnumInterface;
import org.openhealthtools.ihe.common.hl7v2.CX;
import org.openhealthtools.ihe.common.hl7v2.Hl7v2Factory;
import org.openhealthtools.ihe.common.hl7v2.SourcePatientInfoType;
import org.openhealthtools.ihe.common.hl7v2.XAD;
import org.openhealthtools.ihe.common.hl7v2.XCN;
import org.openhealthtools.ihe.common.hl7v2.XON;
import org.openhealthtools.ihe.common.hl7v2.XPN;
import org.openhealthtools.ihe.common.hl7v2.XTN;
import org.openhealthtools.ihe.xds.document.DocumentDescriptor;
import org.openhealthtools.ihe.xds.document.XDSDocument;
import org.openhealthtools.ihe.xds.metadata.AuthorType;
import org.openhealthtools.ihe.xds.metadata.CodedMetadataType;
import org.openhealthtools.ihe.xds.metadata.InternationalStringType;
import org.openhealthtools.ihe.xds.metadata.LocalizedStringType;
import org.openhealthtools.ihe.xds.metadata.MetadataFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.CE;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;

/**
 * <div class="de">Class XdsUtil.</div> <div class="fr"></div> <div
 * class="it"></div>
 */
public class XdsUtil {

	/**
	 * <div class="en">Convert code.</div> <div class="de"></div> <div
	 * class="fr"></div> <div class="it"></div>
	 * 
	 * @param code
	 * <br>
	 *            <div class="de"> code</div> <div class="fr"></div> <div
	 *            class="it"></div>
	 * @return the coded metadata type
	 */
	public static CodedMetadataType convertCode(Code code) {
		return createCodedMetadata(code.getCodeSystem(), code.getCode(), code.getDisplayName(),
				null);
	}

	public static CodedMetadataType convertCode(Code code, String language) {
		return createCodedMetadata(code.getCodeSystem(), code.getCode(), code.getDisplayName(),
				null, language);
	}

	public static CodedMetadataType[] convertEhcCodeToCodedMetadataType(Code[] codeList) {
		if (codeList == null)
			return null;
		else {
			CodedMetadataType[] cmtArray = new CodedMetadataType[codeList.length];

			int i = 0;
			for (Code cme : codeList) {
				cmtArray[i] = XdsUtil.convertCode(cme);
				i++;
			}

			return cmtArray;
		}
	}

	public static org.openhealthtools.ihe.xds.consumer.query.DateTimeRange[] convertEhcDateTimeRange(
			org.ehealth_connector.communication.xd.storedquery.DateTimeRange[] dtr) {
		if (dtr == null)
			return null;
		else {
			org.openhealthtools.ihe.xds.consumer.query.DateTimeRange[] dtrArray = new org.openhealthtools.ihe.xds.consumer.query.DateTimeRange[dtr.length];

			int i = 0;
			for (org.ehealth_connector.communication.xd.storedquery.DateTimeRange dt : dtr) {
				dtrArray[i] = dt.getOhtDateTimeRange();
				i++;
			}

			return dtrArray;
		}
	}

	public static CodedMetadataType[] convertEhcEnumToCodedMetadataType(
			CodedMetadataEnumInterface[] codedMetadataEnum) {
		if (codedMetadataEnum == null)
			return null;
		else {
			CodedMetadataType[] cmtArray = new CodedMetadataType[codedMetadataEnum.length];

			int i = 0;
			for (CodedMetadataEnumInterface cme : codedMetadataEnum) {
				cmtArray[i] = cme.getCodedMetadataType();
				i++;
			}

			return cmtArray;
		}
	}

	/**
	 * <div class="en">Convert identificator.</div> <div class="de"></div> <div
	 * class="fr"></div> <div class="it"></div>
	 * 
	 * @param id
	 * <br>
	 *            <div class="de"> id</div> <div class="fr"></div> <div
	 *            class="it"></div>
	 * @return the cx
	 */
	public static CX convertEhcIdentificator(Identificator id) {
		if (id == null)
			return null;
		return createCx(id.getRoot(), id.getExtension());
	}

	public static XON convertEhcOrganization(Organization o) {
		XON xon = Hl7v2Factory.eINSTANCE.createXON();
		xon.setIdNumber(o.getId());
		xon.setOrganizationName(o.getName());
		return xon;
	}

	/**
	 * <div class="en">Convert ii.</div> <div class="de"></div> <div
	 * class="fr"></div> <div class="it"></div>
	 * 
	 * @param ii
	 * <br>
	 *            <div class="de"> ii</div> <div class="fr"></div> <div
	 *            class="it"></div>
	 * @return the cx
	 */
	public static CX convertII(II ii) {
		return createCx(ii.getRoot(), ii.getExtension());
	}

	public static String convertInternationalStringType(InternationalStringType ist) {
		if (ist != null) {
			if (ist.getLocalizedString() != null && ist.getLocalizedString().size() > 0) {
				String s = "";
				for (int i = 0; i < ist.getLocalizedString().size(); i++) {
					LocalizedStringType lst = (LocalizedStringType) ist.getLocalizedString().get(i);
					s = s + lst.getValue();
					if (i > 0)
						s = s + "\n";
				}
				return s;
			}
		}
		return null;
	}

	public static Author convertOhtAuthorType(AuthorType at) {
		Author a = new Author();

		// Author Person
		XCN ap = null;
		if (at != null) {
			if (at.getAuthorPerson() != null) {
				ap = at.getAuthorPerson();
				// Id
				a.addId(convertOhtXcnIdToEhc(ap.getAssigningAuthorityUniversalId(),
						ap.getIdNumber()));
				// Name
				Name name = new Name(ap.getGivenName(), ap.getFamilyName(), ap.getPrefix(),
						ap.getSuffix());
				a.addName(name);
			}
		}
		// Institution
		XON xon = null;
		if (Util.atLeastOne(at.getAuthorInstitution())) {
			for (int i = 0; i < at.getAuthorInstitution().size(); i++) {
				xon = (XON) at.getAuthorInstitution().get(i);
				Organization org = new Organization(xon.getOrganizationName());
				org.addId(convertOhtXcnIdToEhc(xon.getAssigningAuthorityUniversalId(),
						xon.getIdNumber()));
			}
		}
		// Role
		String role = null;
		if (Util.atLeastOne(at.getAuthorRole())) {
			role = (String) at.getAuthorRole().get(0);
			a.setRoleFunction(new Code("", role));
		}
		// Speciality
		CE speciality = null;
		if (Util.atLeastOne(at.getAuthorSpeciality())) {
			speciality = (CE) at.getAuthorSpeciality().get(0);
			a.setSpeciality(new Code(speciality));
		}
		// Telecoms
		XTN xtn = null;
		Telecoms t = new Telecoms();
		if (Util.atLeastOne(at.getAuthorTelecommunication())) {
			for (int i = 0; i < at.getAuthorTelecommunication().size(); i++) {
				xtn = (XTN) at.getAuthorTelecommunication().get(i);
				t.add(xtn.getTelecommunicationType(), xtn.getTelecommunicationAddress(),
						AddressUse.PRIVATE);
			}
		}

		return a;
	}

	public static Code convertOhtCodedMetadataType(CodedMetadataType cmt) {
		return new Code(cmt.getSchemeName(), cmt.getCode(),
				convertInternationalStringType(cmt.getDisplayName()));
	}

	public static Identificator convertOhtCx(CX cx) {
		return new Identificator(cx.getAssigningAuthorityUniversalId(), cx.getIdNumber());
	}

	public static Patient convertOhtSourcePatientInfoType(SourcePatientInfoType spit) {
		Patient p = new Patient();

		// Name
		XPN xpn = null;
		if (Util.atLeastOne(spit.getPatientName())) {
			for (int i = 0; i < spit.getPatientName().size(); i++) {
				xpn = (XPN) spit.getPatientName().get(i);
				p.addName(XdsUtil.convertOhtXpn(xpn));
			}
		}
		// Date of birth
		if (spit.getPatientDateOfBirth() != null) {
			p.setBirthday(DateUtil.parseDateyyyyMMdd(spit.getPatientDateOfBirth()));
		}
		// Gender
		if (spit.getPatientSex() != null) {
			p.setAdministrativeGender(AdministrativeGender.getEnum(spit.getPatientSex()));
		}
		// Address
		if (spit.getPatientAddress() != null) {
			p.addAddress(XdsUtil.convertOhtXad(spit.getPatientAddress()));
		}
		// ID
		CX cx = null;
		if (Util.atLeastOne(spit.getPatientIdentifier())) {
			for (int i = 0; i < spit.getPatientIdentifier().size(); i++) {
				cx = (CX) spit.getPatientIdentifier().get(i);
				p.addId(XdsUtil.convertOhtCx(cx));
			}
		}
		// Phone Business
		Telecoms t = new Telecoms();
		if (spit.getPatientPhoneBusiness() != null) {
			t.add(spit.getPatientPhoneBusiness().getTelecommunicationType(), spit
					.getPatientPhoneBusiness().getTelecommunicationAddress(), AddressUse.BUSINESS);
		}
		// Phone Home
		if (spit.getPatientPhoneHome() != null) {
			t.add(spit.getPatientPhoneHome().getTelecommunicationType(), spit.getPatientPhoneHome()
					.getTelecommunicationAddress(), AddressUse.PRIVATE);
		}
		p.setTelecoms(t);

		return p;
	}

	public static Address convertOhtXad(XAD xad) {
		if (xad == null)
			return null;
		return new Address(xad.getStreetAddress(), xad.getZipOrPostalCode(), xad.getCity(),
				AddressUse.PRIVATE);
	}

	public static Identificator convertOhtXcnIdToEhc(String assigningAuthorityUniversalId, String id) {
		return new Identificator(assigningAuthorityUniversalId, id);
	}

	public static Name convertOhtXpn(XPN xpn) {
		return new Name(xpn.getGivenName(), xpn.getFamilyName(), xpn.getPrefix(), xpn.getSuffix());
	}

	/**
	 * <div class="en">Creates the coded metadata.</div> <div class="de"></div>
	 * <div class="fr"></div> <div class="it"></div>
	 * 
	 * @param schemeName
	 * <br>
	 *            <div class="de"> scheme name</div> <div class="fr"></div> <div
	 *            class="it"> scheme name</div>
	 * @param code
	 * <br>
	 *            <div class="de"> code</div> <div class="fr"></div> <div
	 *            class="it"></div>
	 * @param displayName
	 * <br>
	 *            <div class="de"> display name</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 * @param schemeUuid
	 * <br>
	 *            <div class="de"> scheme uuid</div> <div class="fr"></div> <div
	 *            class="it"></div>
	 * @return the coded metadata type
	 */
	public static CodedMetadataType createCodedMetadata(String schemeName, String code,
			String displayName, String schemeUuid) {
		CodedMetadataType cmt = MetadataFactory.eINSTANCE.createCodedMetadataType();

		cmt.setCode(code);
		if (displayName != null) {
			cmt.setDisplayName(createInternationalString(displayName));
		}
		if (schemeName != null) {
			cmt.setSchemeName(schemeName);
		}
		if (schemeUuid != null) {
			cmt.setSchemeUUID(schemeUuid);
		}

		return cmt;
	}

	/**
	 * <div class="en">Creates the coded metadata.</div> <div class="de"></div>
	 * <div class="fr"></div> <div class="it"></div>
	 * 
	 * @param schemeName
	 * <br>
	 *            <div class="de"> scheme name</div> <div class="fr"></div> <div
	 *            class="it"> scheme name</div>
	 * @param code
	 * <br>
	 *            <div class="de"> code</div> <div class="fr"></div> <div
	 *            class="it"></div>
	 * @param displayName
	 * <br>
	 *            <div class="de"> display name</div> <div class="fr"></div>
	 *            <div class="it"></div>
	 * @param schemeUuid
	 * <br>
	 *            <div class="de"> scheme uuid</div> <div class="fr"></div> <div
	 *            class="it"></div>
	 * @param language
	 * <br>
	 *            language
	 * @return the coded metadata type
	 */
	public static CodedMetadataType createCodedMetadata(String schemeName, String code,
			String displayName, String schemeUuid, String language) {
		CodedMetadataType cmt = MetadataFactory.eINSTANCE.createCodedMetadataType();

		cmt.setCode(code);
		if (displayName != null) {
			cmt.setDisplayName(createInternationalString(displayName, language));
		}
		if (schemeName != null) {
			cmt.setSchemeName(schemeName);
		}
		if (schemeUuid != null) {
			cmt.setSchemeUUID(schemeUuid);
		}

		return cmt;
	}

	/**
	 * <div class="en">Creates the cx.</div> <div class="de"></div> <div
	 * class="fr"></div> <div class="it"></div>
	 * 
	 * @param authorityId
	 * <br>
	 *            <div class="de"> authority id</div> <div class="fr"> authority
	 *            id</div> <div class="it"> authority id</div>
	 * @param id
	 * <br>
	 *            <div class="de"> id</div> <div class="fr"> id</div> <div
	 *            class="it"> id</div>
	 * @return the cx
	 */
	public static CX createCx(String authorityId, String id) {
		CX cx = Hl7v2Factory.eINSTANCE.createCX();
		cx.setAssigningAuthorityUniversalId(authorityId);
		cx.setIdNumber(id);
		cx.setAssigningAuthorityUniversalIdType("ISO");
		return cx;
	}

	/**
	 * <div class="en">Creates the international string.</div> <div
	 * class="de"></div> <div class="fr"></div> <div class="it"></div>
	 * 
	 * @param text
	 * <br>
	 *            <div class="de"> text</div> <div class="fr"> text</div> <div
	 *            class="it"> text</div>
	 * @return the org.openhealthtools.ihe.xds.metadata. international string
	 *         type
	 */
	@SuppressWarnings("unchecked")
	public static org.openhealthtools.ihe.xds.metadata.InternationalStringType createInternationalString(
			String text) {
		org.openhealthtools.ihe.xds.metadata.InternationalStringType ist = MetadataFactory.eINSTANCE
				.createInternationalStringType();
		LocalizedStringType lst = MetadataFactory.eINSTANCE.createLocalizedStringType();
		lst.setValue(text);
		ist.getLocalizedString().add(lst);
		return ist;
	}

	/**
	 * <div class="en">Creates the international string.</div> <div
	 * class="de"></div> <div class="fr"></div> <div class="it"></div>
	 * 
	 * @param text
	 * <br>
	 *            <div class="de"> text</div> <div class="fr"> text</div> <div
	 *            class="it"> text</div>
	 * @param language
	 * <br>
	 *            the language
	 * @return the org.openhealthtools.ihe.xds.metadata. international string
	 *         type
	 */
	@SuppressWarnings("unchecked")
	public static org.openhealthtools.ihe.xds.metadata.InternationalStringType createInternationalString(
			String text, String language) {
		org.openhealthtools.ihe.xds.metadata.InternationalStringType ist = MetadataFactory.eINSTANCE
				.createInternationalStringType();
		LocalizedStringType lst = MetadataFactory.eINSTANCE.createLocalizedStringType();
		lst.setValue(text);
		lst.setLang(language);
		ist.getLocalizedString().add(lst);
		return ist;
	}

	public static String createXdmDocName(XDSDocument xdsDoc, int docNr) {
		// compile the path and filename for the zip file
		String fileName = "DOC";

		// Fix DocumentDescriptor problem...
		DocumentDescriptor dd = xdsDoc.getDescriptor();
		if (dd.toString().startsWith("UNKNOWN!")) {
			String mimeType = dd.toString().replace("UNKNOWN!", "");
			mimeType = mimeType.substring(mimeType.indexOf("!") + 1, mimeType.length());
			dd = DocumentDescriptor.getDocumentDescriptorForMimeType(mimeType);
		}
		// if ("UNKNOWN!CDA-R2!text/xml".equals(dd.toString()))
		// dd = DocumentDescriptor.CDA_R2;

		String fileNameExtension = DocDescriptor.getFileExtension(dd);
		fileName = fileName.concat(String.format("%5s", docNr).replace(' ', '0'));
		fileName = fileName.concat("." + fileNameExtension.toUpperCase());
		return fileName;
	}

	public static String createXdmDocPathAndName(XDSDocument xdsDoc, int docNr) {
		String filePath = "IHE_XDM/SUBSET01/" + createXdmDocName(xdsDoc, docNr);
		return filePath;
	}
}
