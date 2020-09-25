package com.jamasoftware.services.restclient.jamadomain.fields;

import com.jamasoftware.services.restclient.jamadomain.values.UserFieldValue;

/**
 * Jama Connect is a dynamic Cloud service, new Field types are potentially introduced. To copy with unknown and thus
 * unsupported field types, we introduce this {@link JamaField}.
 *
 * @author Jan mauersberger
 */
public class UnsupportedField extends JamaField {

	@Override
	public UserFieldValue getValue() {
		throw new RuntimeException("Unsupported field type " + getName()); //$NON-NLS-1$
	}

	public UnsupportedField(String type) {
		super(type);
	}

	public UnsupportedField() {
		super();
	}

}
