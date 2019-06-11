/*
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
package org.ehealth_connector.valueset.model;

import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.ehealth_connector.common.basetypes.OrganizationBaseType;

/**
 * The Class Version contains all information describing a specific version of a
 * value set.
 */
public class Version {

	/**
	 * Builder to build {@link Version}.
	 */
	@Generated("SparkTools")
	public static final class Builder {

		/** The label. */
		private String label;

		/** The publishing authority. */
		private OrganizationBaseType publishingAuthority;

		/** The valid from. */
		private LocalDateTime validFrom;

		/** The valid to. */
		private LocalDateTime validTo;

		/**
		 * Instantiates a new builder.
		 */
		private Builder() {
		}

		/**
		 * Builds the.
		 *
		 * @return the version
		 */
		public Version build() {
			return new Version(this);
		}

		/**
		 * With label.
		 *
		 * @param label
		 *            the label
		 * @return the builder
		 */
		public Builder withLabel(String label) {
			this.label = label;
			return this;
		}

		/**
		 * With publishing authority.
		 *
		 * @param publishingAuthority
		 *            the publishing authority
		 * @return the builder
		 */
		public Builder withPublishingAuthority(OrganizationBaseType publishingAuthority) {
			this.publishingAuthority = publishingAuthority;
			return this;
		}

		/**
		 * With valid from.
		 *
		 * @param validFrom
		 *            the valid from
		 * @return the builder
		 */
		public Builder withValidFrom(LocalDateTime validFrom) {
			this.validFrom = validFrom;
			return this;
		}

		/**
		 * With valid to.
		 *
		 * @param validTo
		 *            the valid to
		 * @return the builder
		 */
		public Builder withValidTo(LocalDateTime validTo) {
			this.validTo = validTo;
			return this;
		}
	}

	/**
	 * Creates builder to build {@link Version}.
	 *
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/** The label. */
	private String label;

	/** The publishing authority. */
	private OrganizationBaseType publishingAuthority;

	/** The valid from. */
	private LocalDateTime validFrom;

	/** The valid to. */
	private LocalDateTime validTo;

	/**
	 * Instantiates a new version.
	 *
	 * @param builder
	 *            the builder
	 */
	@Generated("SparkTools")
	private Version(Builder builder) {
		this.label = builder.label;
		this.publishingAuthority = builder.publishingAuthority;
		this.validFrom = builder.validFrom;
		this.validTo = builder.validTo;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */

	public String getLabel() {
		return label;
	}

	/**
	 * Gets the publishing authority.
	 *
	 * @return the publishing authority
	 */

	public OrganizationBaseType getPublishingAuthority() {
		return publishingAuthority;
	}

	/**
	 * Gets the valid from.
	 *
	 * @return the valid from
	 */

	public LocalDateTime getValidFrom() {
		return validFrom;
	}

	/**
	 * Gets the valid to.
	 *
	 * @return the valid to
	 */

	public LocalDateTime getValidTo() {
		return validTo;
	}

	/**
	 * Sets the label.
	 *
	 * @param label
	 *            the new label
	 */

	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Sets the publishing authority.
	 *
	 * @param publishingAuthority
	 *            the new publishing authority
	 */

	public void setPublishingAuthority(OrganizationBaseType publishingAuthority) {
		this.publishingAuthority = publishingAuthority;
	}

	/**
	 * Sets the valid from.
	 *
	 * @param validFrom
	 *            the new valid from
	 */

	public void setValidFrom(LocalDateTime validFrom) {
		this.validFrom = validFrom;
	}

	/**
	 * Sets the valid to.
	 *
	 * @param validTo
	 *            the new valid to
	 */

	public void setValidTo(LocalDateTime validTo) {
		this.validTo = validTo;
	}

}