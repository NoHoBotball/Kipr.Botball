package regionals2012;

import java.io.FileNotFoundException;

import test.Accelerometer;
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Accelerometer test = new Accelerometer("Accelerometer", 2, 9.8e3, 'z');
			test.main();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
