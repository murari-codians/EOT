package com.eot.core;

public enum LoginTypes {

	SUPERADMIN("SUPERADMIN", 10), MGURUSH("MGURUSH", 20);

	private final String key;
	private final Long value;

	LoginTypes(String key, int value) {
		this.key = key;
		this.value = (long) value;

	}

	public String getKey() {
		return key;
	}

	public Long getValue() {
		return value;
	}

}
