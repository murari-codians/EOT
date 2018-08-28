package com.eot.core;

public enum LoginTypes {

	SUPERADMIN("SUPERADMIN", 10), MGURUSH("MGURUSH", 20), DISTRIBUTER("DISTRIBUTOR", 30), WHOLSELLER("WHOLESELLER",
			40), AGENT("AGENT", 40), SOLEMERCHANT("SOLEMERCHANT", 60), AGENTSOLEMERCHANT("AGENTSOLEMERCHANT", 70);

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
