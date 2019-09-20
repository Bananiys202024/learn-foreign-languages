package com.web.Fremdsprache.util;

import java.nio.charset.Charset;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class Security {

	public static String generateRandomSevenElementCode() {
		return RandomStringUtils.randomAlphabetic(7);
	}

}
