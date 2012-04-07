package calibrate;


import java.io.File;
import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * 
 * This class represents a file that contains information arranged into rows
 * and/or columns.
 * 
 * @author Joseph McGee
 *
 */
public class DataSheetReader extends FileReader{
	
	DataSheetReader(File file) throws IOException {
		super(file);
	}
	DataSheetReader(String fileName) throws IOException {
		super(fileName);
	}
	DataSheetReader(FileDescriptor fileDescriptor) {
		super(fileDescriptor);
	}
	
	Double[][] dataByCol;
	Double[][] dataByRow;
	
	/**
	 * @return Data read from the file, organized by column and row into arrays
	 * 			of the format: <code>array[col][row]</code>
	 * @throws IOException
	 */
	public Double[][] getData() throws IOException{
		return getDataByCol();
	}
	/**
	 * @return Data read from the file, organized by column and row into arrays
	 * 			of the format: <code>array[col][row]</code>
	 * @throws IOException
	 */
	public Double[][] getDataByCol() throws IOException{
		if(dataByCol == null) _readDataSheet();
		return dataByCol;
	}
	/**
	 * @return Data read from the file, organized by column and row into arrays
	 * 			of the format: <code>array[row][col]</code>
	 * @throws IOException
	 */
	public Double[][] getDataByRow() throws IOException{
		if(dataByRow == null) _readDataSheet();
		return dataByRow;
	}
	
	/**
	 * @param col Number of column of data to return. 0 is the leftmost column.
	 * @return a Double[] containing values in the specified column listed top
	 * 			to bottom. First value is assumed to be a label and discarded.
	 * @throws IOException
	 */
	public Double[] getDataCol(int col) throws IOException{
		if(dataByCol == null) _readDataSheet();
		return dataByCol[col];
	}
	
	/**
	 * @param row number of the row of data to get. 0 is the leftmost row.
	 * @return a Double[] containing values in the specified row listed left
	 * 			to right. First value is assumed to be a label and discarded.
	 * @throws IOException
	 */
	public Double[] getDataRow(int row) throws IOException{
		if(dataByRow == null) _readDataSheet();
		return dataByRow[row];
	}	
	
	
	private void _readDataSheet() throws IOException {
		
		Scanner source = new Scanner(this);

		//Keep track of current and maximum row/column
		int row, max_row = 0;
		int col, max_col = 0;
		
		/*
		 * extra contains ArrayLists which contain doubles.
		 * The Nth member (with index N-1) of extra represents the Nth column of
		 * ...the data sheet, counting starting with the leftmost column.
		 * The Xth member (with index X-1) of a member extra represents the Xth
		 * ...row of the Nth column of the data sheet, "file".
		 */
		ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();

		//The first line of the file should contain labels for the data listed
		//in the column below each label.
		if(source.hasNextLine()){
			source.nextLine();
		}
		//Throw exception if there are no lines in file.
		else throw new IOException(this + " is empty.");
		
		//Build custom pattern for Double objects if not already built
		if(floatObjPattern == null) buildFloatAndDecimalPattern();
		

		
		//Parse every row and add elements to members of extra.
		for(row = 0; source.hasNextLine(); row++) {
			//Read next line of data.
			Scanner line = new Scanner(source.nextLine());
			data.add(new ArrayList<Double>());
			
			//Add all values of type Double in the line to an ArrayList in extra
			for(col = 0; line.hasNext(floatObjPattern); col++){
				
				//Translate the next token into a double.
				//Don't think nextDouble() recognizes null or NaN values.
				Double d;
				String s = line.next().toLowerCase();
				if     (s == "null") d = null;
				else if(s == "nan" ) d = Double.NaN;
				else				 d = new Scanner(s).nextDouble();
				
				
				//Add element to ArrayList for the current row
				data.get(row).add(d);
				if(col > max_col) max_col = col;
			}
			if(row > max_row) max_row = row;
		}
		
		for(row = 0; row < max_row; row++) {
			while(data.get(row).size()-1 < max_col) {
				data.get(row).add(null);
			}
		}
		
		//copy value from extra into data
		dataByCol = new Double[max_col][max_row];
		for(col = 0; col < max_col; col++) {
			for(row = 0; row < max_row; row++) {
				dataByCol[col][row] = data.get(row).get(col);
			}
		}
		
		dataByRow = new Double[max_row][max_col];
		for(row = 0; row < max_row; row++){
			data.get(row).toArray(dataByRow[row]);
		}
	}
	
	
	


    static private Pattern floatPattern;
    //Altered floatPattern to accept "null" for Float or Double objects
    static private Pattern floatObjPattern;
    static private Pattern decimalPattern;
    static private void buildFloatAndDecimalPattern() {
    	
    	//this part is mostly copied from Scanner.class source attachment
        String groupSeparator = "\\,";
        String decimalSeparator = "\\.";
        String nanString = "[Nn][Aa][Nn]";//I made this not case sensitive.
        String nullString = "[Nn][Uu][Ll][Ll]";//I made this.
        String infinityString = "Infinity";
        String positivePrefix = "";
        String negativePrefix = "\\-";
        String non0Digit = "[\\p{javaDigit}&&[^0]]";
    	
        String digit = "([0-9]|(\\p{javaDigit}))";
        String exponent = "([eE][+-]?"+digit+"+)?";
        String groupedNumeral = "("+non0Digit+digit+"?"+digit+"?("+
                                groupSeparator+digit+digit+digit+")+)";
        // Once again digit++ is used for performance, as above
        String numeral = "(("+digit+"++)|"+groupedNumeral+")";
        String decimalNumeral = "("+numeral+"|"+numeral + 
            decimalSeparator + digit + "*+|"+ decimalSeparator + 
            digit + "++)";
        String nonNumber = "(NaN|"+nanString+"|Infinity|"+
                               infinityString+")";
        String positiveFloat = "(" + positivePrefix + decimalNumeral + 
                            exponent + ")";
        String negativeFloat = "(" + negativePrefix + decimalNumeral + 
                            exponent + ")";
        String decimal = "(([-+]?" + decimalNumeral + exponent + ")|"+ 
            positiveFloat + "|" + negativeFloat + ")";
        String hexFloat = 
            "[-+]?0[xX][0-9a-fA-F]*\\.[0-9a-fA-F]+([pP][-+]?[0-9]+)?";
        String positiveNonNumber = "(" + positivePrefix + nonNumber + ")";
        String negativeNonNumber = "(" + negativePrefix + nonNumber + ")";
        String signedNonNumber = "(([-+]?"+nonNumber+")|" +
                                 positiveNonNumber + "|" + 
                                 negativeNonNumber + ")";
        floatPattern = Pattern.compile(decimal + "|" + hexFloat + "|" +
                                       signedNonNumber);
        //Altered floatPattern to accept "null" for Float or Double objects
        floatObjPattern = Pattern.compile(decimal + "|" + hexFloat + "|" +
                signedNonNumber + "|" + nullString);
        decimalPattern = Pattern.compile(decimal);
    }
	

}
