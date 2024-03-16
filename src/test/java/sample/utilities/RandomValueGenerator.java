package sample.utilities;

import java.util.Random;

/**
 * Class having common utility methods which can be used across test artifacts.
 */
public class RandomValueGenerator {

	/**
	 * Method to generate random double value upto 2 decimal points.
	 * 
	 * @return randomeDoubleValue
	 */
	public static double randomDoubleValue() {
		Random random = new Random();

		double randomValue = random.nextDouble() * 100;

		randomValue = Math.round(randomValue * 100.0) / 100.0;
		return randomValue;
	}

}
