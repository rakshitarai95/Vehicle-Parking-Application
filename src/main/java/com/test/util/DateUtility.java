package com.test.util;

import java.util.Random;

public class DateUtility {

	public static String getTicketNumber() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder tempPwd = new StringBuilder();
		Random random = new Random();
		while (tempPwd.length() < 6) {
			int index = (int) (random.nextFloat() * SALTCHARS.length());
			tempPwd.append(SALTCHARS.charAt(index));
		}
		return tempPwd.toString();

	}

}
