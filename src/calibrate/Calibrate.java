package calibrate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.Scanner;

public abstract class Calibrate extends Thread{
	
	static String className = "Calibrate";
	File dataStore;
	PrintWriter dataOut;
	int counter;
	int n;//override this
	int target;//override this
	public void main(String[] args) {
		
		PrintWriter counterW = new PrintWriter(System.out);
		try {
			counterW = new PrintWriter(className + "_counter");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner counterR = new Scanner(className + "_counter");
		counter = counterR.nextInt();
		counterR.close();
		counterW.println("" + (counter+1));
		counterW.flush();
		counterW.close();
		
		dataStore = new File(className + "_" + counter + ".test");
		
		try {
			dataOut = new PrintWriter(dataStore);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.start();
	}
	
	public String calibrate() {
		
		return null;
	}
	
	public void run(){
		for(int i = 0; i<n; i++){
			dataOut.println("target" + '\t' + "result");
			dataOut.print(target + '\t' + calibrate());
			dataOut.println();
		}
	}
}
