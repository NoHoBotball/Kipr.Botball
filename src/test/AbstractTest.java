package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class AbstractTest {
	
	private String testName;
	PrintWriter dataFile;
	int n;
	
	public AbstractTest(int n) throws FileNotFoundException {
		this.n = n;
		setOut();
	}
	
	public AbstractTest(String testName, int n) throws FileNotFoundException {
		this.testName = testName;
		this.n = n;
		setOut();
	}
	
	public void setOut() throws FileNotFoundException {
		File dataFile;
		for(int i = 0;; i++){
			dataFile = new File(testName + "_" + i + ".test");
			if(dataFile.exists()){
				break;
			}
		}
		this.dataFile = new PrintWriter(dataFile);
	}
	
	protected Double target() {
		System.err.println("Idiot.  I told you to override target().  --Joseph McGee");
		return null;
	}
	protected Double result() {
		System.err.println("Idiot.  I told you to override result().  --Joseph McGee");
		return null;
	}
	
	public void main() {
		dataFile.println("target" + "\t" + "result");
		begin();
		for(int i = 0; i<n && !endTest(); i++) {
			doTest();
		}
		end();
		dataFile.flush();
		dataFile.close();
	}
	
	void begin() {
		
	}

	void doTest() {
		dataFile.printf("%5.3g\t", target());
		dataFile.printf("%5.3g\t", result());
		dataFile.println();
	}
	
	boolean endTest(){
		return false;
	}
	
	void end() {
		
	}
	
}