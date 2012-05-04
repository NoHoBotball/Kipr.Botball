package utils.vision;

import cbc.sensors.vision.Camera;

public class Blob extends cbccore.sensors.vision.Blob{

	public Blob(int ch, int index) {
		super(new Camera(), ch, index);
	}
	
	public int score() {
		return getSize() * getConfindence();
	}

}
