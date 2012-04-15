package regionals2012;

import java.io.FileNotFoundException;

import utils.tests.Accelerometer;
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Accelerometer test = new Accelerometer();
			test.main();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
