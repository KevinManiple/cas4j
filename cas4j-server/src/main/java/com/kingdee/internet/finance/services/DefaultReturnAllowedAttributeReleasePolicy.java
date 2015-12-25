package com.kingdee.internet.finance.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jasig.cas.services.AbstractAttributeReleasePolicy;

public class DefaultReturnAllowedAttributeReleasePolicy extends AbstractAttributeReleasePolicy {

	private static final long serialVersionUID = 5178621101856801298L;

	private List<String> allowedAttributes;

	/**
	 * Instantiates a new Return allowed attribute release policy.
	 */
	public DefaultReturnAllowedAttributeReleasePolicy() {
		//this(new ArrayList<String>());
		allowedAttributes = new ArrayList<String>();
		allowedAttributes.add("username");
		allowedAttributes.add("userid");
		allowedAttributes.add("mobile");
		allowedAttributes.add("email");
	}

	/**
	 * Instantiates a new Return allowed attribute release policy.
	 *
	 * @param allowedAttributes the allowed attributes
	 */
	public DefaultReturnAllowedAttributeReleasePolicy(final List<String> allowedAttributes) {
		this.allowedAttributes = allowedAttributes;
	}

	/**
	 * Sets the allowed attributes.
	 *
	 * @param allowed the allowed attributes.
	 */
	public void setAllowedAttributes(final List<String> allowed) {
		this.allowedAttributes = allowed;
	}

	/**
	 * Gets the allowed attributes.
	 *
	 * @return the allowed attributes
	 */
	public List<String> getAllowedAttributes() {
		return Collections.unmodifiableList(this.allowedAttributes);
	}

	@Override
	protected Map<String, Object> getAttributesInternal(final Map<String, Object> resolvedAttributes) {
		final Map<String, Object> attributesToRelease = new HashMap<>(resolvedAttributes.size());

		for (final String attribute : this.allowedAttributes) {
			final Object value = resolvedAttributes.get(attribute);

			if (value != null) {
				logger.debug("Found attribute [{}] in the list of allowed attributes", attribute);
				attributesToRelease.put(attribute, value);
			}
		}
		return attributesToRelease;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		final DefaultReturnAllowedAttributeReleasePolicy rhs = (DefaultReturnAllowedAttributeReleasePolicy) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(this.allowedAttributes, rhs.allowedAttributes)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(13, 133).appendSuper(super.hashCode()).append(allowedAttributes).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString()).append("allowedAttributes", allowedAttributes)
				.toString();
	}

}
