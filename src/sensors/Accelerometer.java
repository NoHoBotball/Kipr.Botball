package sensors;

import cbccore.low.Sensor;

public class Accelerometer implements Runnable{

	Sensor cbc = new Sensor();
	
	
	//a value of 1365 is approximately 1 G (9.8    m/sec^2 - accel of gravity)	
	//a value of 1365 is approximately 1 G (9.8e3 mm/sec^2 - accel of gravity)	
	
	final static double ACCEL_UNIT = 9.8E3 / 1365; // mm/sec^2
	
	private long time;
	private long lag;
	private long endTime;
	
	private double[] pos  = new double[3];
	private double[] dPos = new double[3];
	private double[] v  = new double[3];
	private double[] dV = new double[3];
	private double[] a  = new double[3];
	/**
	 * @param pos - original position vector. components defined in mm.
 	 * @param v - original velocity vector. components defined in mm/sec.
	 * @param lag - time in milliseconds between updates
	 * @param timeout - time in milliseconds until position tracking instance times out
	 */
	Accelerometer(double[] pos, double[] v, long lag, long timeout){
		this.pos = pos;
		this.v = v;
		for(int i = 0; i<3; i++)
			a[i] = getAccel(i);
		
		this.lag = lag;
		endTime = System.currentTimeMillis() + timeout;
		time    = System.currentTimeMillis();

		run();
	}
	
	/**
	 * 
	 * @param axis - which component of the acceleration vector?
	 * @return <b>axis</b> component of the acceleration vector in mm/sec^2
	 */
	public double getAccel(int axis) {
		double accel;
		
		switch(axis){
		case 0:
			accel = cbc.accel_x();
			break;
		case 1:
			accel = cbc.accel_y();
			break;
		case 2:
			accel = cbc.accel_z();
			break;
		default:
			System.err.println("There are only three dimensions." +
					"Choose one, dumbass.");
			accel = 0;
		}
		
		//convert accel into mm/sec^2
		accel = accel/ACCEL_UNIT;
		return accel;
	}
	public double[] getAccel() {
		double[] accel = new double[3];
		
		accel[0] = cbc.accel_x();
		accel[1] = cbc.accel_y();
		accel[2] = cbc.accel_z();
		
		for(int i = 0; i<3; i++)
			
		//convert accel into mm/sec^2
		for(i = 0; i<3; i++){
			accel[i] = accel[i]/ACCEL_UNIT;
		}
		return accel;
	}
	
	public double[] getV() {
		return v;
	}
	public double[] getdV() {
		double[] dV = this.dV;
		for(int i = 0; i<3; i++)
			this.dV[i] += 0;
		return dV;
	}
	
	public double[] getPos() {
		return pos;
	}
	public double[] getdPos() {
		double[] dPos = this.dPos;
		for(int i = 0; i<3; i++)
			this.dPos[i] += 0;
		return dPos;
	}
	
	/**@return change in position since last call*/
	private void trackPos() {
		double dTime = System.currentTimeMillis() - time;
		
		//calculate delta position vector components
		for(int i = 0; i<3; i++) {
			dPos[i] = dPos[i] + v[i]*dTime + .5*a[i]*dTime*dTime;
			pos[i] += dPos[i];
		}
		//calculate delta velocity vector components
		for(int i = 0; i<3; i++) {
			dV[i] = dV[i] + a[i]*dTime;
			v[i] += dV[i];
		}
		//calculate delta acceleration vector components
		for(int i = 0; i<3; i++) {
			a[i]  = getAccel(i);
		}
		
		time = System.currentTimeMillis();
	}

	
	public void run(){
		while(System.currentTimeMillis() < endTime) {
			while(System.currentTimeMillis() - time < lag)
				Thread.yield();
			trackPos();
			}
		trackPos();
	}	
	
}
