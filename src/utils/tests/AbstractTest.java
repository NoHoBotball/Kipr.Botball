package utils.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class AbstractTest {
	
	private String testName;
	PrintWriter dataFile;
	String[] labels = {"Target", "Result"};
	Double[][] data;
	int n, i=0;
	
	public AbstractTest(int n) throws FileNotFoundException {
		this.n = n;
		setOut();
	}
	
	public AbstractTest(String testName, String[] labels, int n) throws FileNotFoundException {
		this.testName = testName;
		this.n = n;
		setOut();
	}
	
	public void setOut() throws FileNotFoundException {
		data = new Double[labels.length][n];

		File dataFile;
		for(int i = 0;; i++){
			dataFile = new File(testName + "_" + i + ".test");
			if(dataFile.exists()){
				break;
			}
		}
		this.dataFile = new PrintWriter(dataFile);
	}
	
	public void main() {
		begin();
		for(i = 0; i<n && !endTest(); i++) {
			doTest();
		}
		end();
		printData();
		dataFile.flush();
		dataFile.close();
	}
	
	void begin() {
		
	}

	void doTest() {
		
	}
	
	boolean endTest(){
		return false;
	}
	
	void end() {
		
	}
	
	void printData() {
		for(int labelNum = 0; labelNum < labels.length; labelNum++) {
			dataFile.print(labels[labelNum] + "= {");
			for(int testNum = 0; testNum < n; testNum++) {
				dataFile.print(" " + data[labelNum][testNum] + " ");
				if(testNum == n-1) break;
				dataFile.print(',');
			}
			dataFile.println('}');
		}
	}
	
}