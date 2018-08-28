package com.eot.core;

public enum TransactionTypes {

	DEPOSITE("DEPOSITE", 0), WITHDRAWAL("WITHDRAWAL", 1), TRANSFER("TRANSFER", 2), BILLPAYMENT("BILLPAYMENT",
			3), TOPUP("TOPUP", 4), BALANCEENQUIRY("BALANCEENQUIRY", 5), MINISTATEMENT("MINISTATEMENT", 6);

	private final String key;
	private final Long value;

	TransactionTypes(String key, int value) {
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
