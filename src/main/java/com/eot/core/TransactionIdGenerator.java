package com.eot.core;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class TransactionIdGenerator {

	public String transactionIdGenerator() {

		final String intChar = "0123456789";
		final Random r = new Random();
		String pass = "";

		while (pass.length() != 8) {
			int rPick = r.nextInt(3);
			if (rPick == 2) {
			int spot = r.nextInt(9);
			pass += intChar.charAt(spot);
			}
		}

		return pass;

	}

}
