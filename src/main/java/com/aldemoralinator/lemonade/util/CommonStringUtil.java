package com.aldemoralinator.lemonade.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class CommonStringUtil {
	
	public static void throwErrIfValidEmailAddress(String email) throws AddressException {
		InternetAddress emailAddr = new InternetAddress(email);
		emailAddr.validate();
	}
	
	public static String extractUsername(String email) {
		String[] substrings = email.split("@");
		return substrings[0];
	}
}
