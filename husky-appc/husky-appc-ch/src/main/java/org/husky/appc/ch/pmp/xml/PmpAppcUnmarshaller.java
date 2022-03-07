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
package org.husky.appc.ch.pmp.xml;

import org.husky.appc.ch.ChAppcUrns;
import org.husky.appc.ch.errors.InvalidSwissAppcContentException;
import org.husky.appc.ch.models.ChPolicySetInterface;
import org.husky.appc.ch.pmp.models.PmpPolicySetEmergency;
import org.husky.appc.ch.pmp.models.PmpPolicySetGroup;
import org.husky.appc.ch.pmp.models.PmpPolicySetHealthcareProfessional;
import org.husky.appc.ch.pmp.policysets.PolicySetPmpEmedicationAccess;
import org.husky.appc.ch.pmp.policysets.PolicySetPmpFullAccess;
import org.husky.appc.ch.policysets.PolicySetExclusionList;
import org.husky.appc.ch.xml.ChAppcUnmarshaller;
import org.husky.appc.models.PolicySetType;
import org.husky.communication.ch.enums.PurposeOfUse;
import org.husky.communication.ch.enums.Role;

import java.util.Collections;
import java.util.List;

/**
 * This class provides an unmarshaller implementation to read PMP APPC documents from their XML representation.
 *
 * <p>This unmarshaller assumes the document is valid regarding the specifications. If it isn't, the unmarshalled
 * object may not reflect the original document perfectly. Use the validator before.
 *
 * @author Quentin Ligier
 **/
public class PmpAppcUnmarshaller extends ChAppcUnmarshaller {

    /**
     * List of allowed referenced policy set Ids for normal access (HCP, EMER).
     */
    private static final List<String> ALLOWED_NORMAL_REFS = List.of(
            PolicySetPmpEmedicationAccess.INSTANCE.getId(),
            PolicySetExclusionList.INSTANCE.getId()
    );

    /**
     * Default constructor.
     */
    public PmpAppcUnmarshaller() {
        super(
                Collections.emptyList(),
                List.of(PolicySetPmpEmedicationAccess.INSTANCE,
                        PolicySetExclusionList.INSTANCE,
                        PolicySetPmpFullAccess.INSTANCE)
        );
    }

    @Override
    protected ChPolicySetInterface extractChildPolicySet(final PolicySetType policySet) {
        final var generic = super.extractChildPolicySet(policySet);

        if (generic.getRole() == Role.HEALTHCARE_PROFESSIONAL
                && generic.getPurposeOfUse() == PurposeOfUse.EMERGENCY_ACCESS
                && generic.getConfidentialityCodes() == null
                && generic.getSubjectId() == null
                && generic.getSubjectIdQualifier() == null
                && generic.getSubjectOrganizationId() == null
                && generic.getReferencedPolicies().isEmpty()
                && generic.getReferencedPolicySets().size() == 1
                && ALLOWED_NORMAL_REFS.contains(generic.getReferencedPolicySets().get(0).getId())) {
            return new PmpPolicySetEmergency(
                    generic.getId(),
                    generic.getDescription(),
                    generic.getReferencedPolicySets(),
                    generic.getValidityStartDate(),
                    generic.getValidityEndDate()
            );
        } else if (generic.getRole() == Role.HEALTHCARE_PROFESSIONAL
                && generic.getPurposeOfUse() == PurposeOfUse.NORMAL_ACCESS
                && generic.getConfidentialityCodes() == null
                && generic.getSubjectId() != null
                && ChAppcUrns.GLN.equals(generic.getSubjectIdQualifier())
                && generic.getSubjectOrganizationId() == null
                && generic.getReferencedPolicies().isEmpty()
                && generic.getReferencedPolicySets().size() == 1
                && ALLOWED_NORMAL_REFS.contains(generic.getReferencedPolicySets().get(0).getId())) {
            return new PmpPolicySetHealthcareProfessional(
                    generic.getId(),
                    generic.getDescription(),
                    generic.getReferencedPolicySets(),
                    generic.getValidityStartDate(),
                    generic.getValidityEndDate(),
                    generic.getSubjectId()
            );
        } else if (generic.getRole() == Role.HEALTHCARE_PROFESSIONAL
                && generic.getPurposeOfUse() == PurposeOfUse.NORMAL_ACCESS
                && generic.getConfidentialityCodes() == null
                && generic.getValidityEndDate() != null
                && generic.getSubjectId() == null
                && generic.getSubjectIdQualifier() == null
                && generic.getSubjectOrganizationId() != null
                && generic.getReferencedPolicies().isEmpty()
                && generic.getReferencedPolicySets().size() == 1
                && PolicySetPmpEmedicationAccess.INSTANCE.getId().equals(generic.getReferencedPolicySets().get(0).getId())) {
            return new PmpPolicySetGroup(
                    generic.getId(),
                    generic.getDescription(),
                    List.of(PolicySetPmpEmedicationAccess.INSTANCE),
                    generic.getValidityStartDate(),
                    generic.getValidityEndDate(),
                    generic.getSubjectOrganizationId()
            );
        } else {
            throw new InvalidSwissAppcContentException("The PMP policy set is invalid");
        }
    }
}
