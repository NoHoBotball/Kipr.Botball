package utils;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class DataSheetWriter extends FileWriter {
	DataSheetWriter(File file) throws IOException {
		super(file);
	}
	DataSheetWriter(String fileName) throws IOException {
		super(fileName);
	}
	DataSheetWriter(FileDescriptor fileDescriptor) {
		super(fileDescriptor);
	}
	
	Double[][] dataByCol;
	Double[][] dataByRow;
	private int row, max_row = 0;
	private int col, max_col = 0;
	
	public void writeDataSheet(String[] label, Double[][] data) throws IOException {
		writeDataSheetByCol(label, data);
	}
	public void writeDataSheetByCol(String[] label, Double[][] data)throws IOException {
		PrintWriter dest = new PrintWriter(this);
		
		//determine max col and row index.
		max_col = data.length;
		for(col = 0; col < data.length; col++) {
			if(data[col].length > max_row) max_row = data[col].length;
		}
		
		//Print labels to file
		for(col = 0; col < max_col; col++) {
			dest.print(label[col]);
		}
		dest.println();
		
		
		//Print data to file by row and col
		for(row = 0; row < max_row; row++) {
			for(col = 0; col < max_col; col++) {
				dest.print("" + data[col][row] + "\t");
			}
			dest.println();
		}
	}
	public void writeDataSheetByRow(String[] label, Double[][] data)throws IOException {
		PrintWriter dest = new PrintWriter(this);
		
		//determine max row and col index.
		max_row = data.length;
		for(row = 0; row < data.length; row++) {
			if(data[row].length > max_col) max_col = data[row].length;
		}
		
		//Print labels to file
		for(col = 0; col < max_col; col++) {
			dest.print(label[col]);
		}
		dest.println();
		
		
		//Print data to file by col and row
		for(row = 0; row < max_row; row++) {
			for(col = 0; col < max_col; col++) {
				dest.print("" + data[row][col] + "\t");
			}
			dest.println();
		}
		
		
	}	

}
