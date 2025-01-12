package com.qp.grocerybooking.utilities;

import java.util.Random;

public class CommonUtils {
	public static String generateRandomNumberString(int digit) {
		Random random = new Random();
		Double lowestPossibleNum = Math.pow(10, digit-1);
		Double randomNumber = lowestPossibleNum + random.nextInt(9 * lowestPossibleNum.intValue());
		return String.valueOf(randomNumber.intValue());
	}
}
