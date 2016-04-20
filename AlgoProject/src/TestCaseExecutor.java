import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


import org.apache.poi.xssf.usermodel.*;
/**
 * This class execute all the test cases received in file 
 * for mentioned algorithm. After execution store the results
 * in the a excel File
 * @author deepak Sharma
 *
 */
public class TestCaseExecutor {
	XSSFWorkbook ResultBook = new XSSFWorkbook();
	XSSFSheet ResultSheet;
	File excelFile;
	String[] executableAlgos;
	//Algo executableAlgo;
	
public TestCaseExecutor( String algoName, String fileName) {
	//
	excelFile = new File(fileName);
	executableAlgos=algoName.split(",");
}	
/**
 * Read test Cases one by one from file and send it as inputs to
 * the implementation of algorithm.
 * Object of implementation is created using static method of
 * algoFactory class at run time. 
 * @param testFile This file contains all the test Cases
 * @throws IOException
 */
public void TestExecutor(File testFile) throws IOException
{

	String sCurrentLine;
	
	String[] input;
	int algoCounter=0;
	int testCaseLength=1;
	int rowCounter=1;
	BufferedReader fileReader = new BufferedReader(new FileReader(testFile));
	while(algoCounter<executableAlgos.length){
	ResultSheet = ResultBook.createSheet(executableAlgos[algoCounter]);
	rowCounter=1;
	Algo AlgoImplementation= AlgoFactory.createAlgo(executableAlgos[algoCounter++]);
	
	Result algoResult;
	while ((sCurrentLine = fileReader.readLine()) != null) {
		input=sCurrentLine.split("\t");
	 	algoResult=AlgoImplementation.execute(input[0],input[1]); 
	    recordResults(algoResult,rowCounter++);}
	}
	testCaseLength++;
}
/**
 * This method store the output of the execution in Excel workbook.
 * record of each algorithm will be store in different Excel Sheet
 * @param algoResult Object of result class
 * @param testCaseLength 
 * @throws FileNotFoundException
 */
private void recordResults(Result algoResult, int rowCounter ) throws FileNotFoundException
{
	int cellCount=0;
    XSSFRow executionRecordRow = ResultSheet.createRow(rowCounter);
    XSSFCell lengthCell = executionRecordRow.createCell(cellCount++);
    lengthCell.setCellValue(rowCounter);
    XSSFCell stringSLengthCell = executionRecordRow.createCell(cellCount++);
    stringSLengthCell.setCellValue(algoResult.getSLength());
    XSSFCell stringRLengthCell = executionRecordRow.createCell(cellCount++);
    stringRLengthCell.setCellValue(algoResult.getRlength());
    XSSFCell timeCell = executionRecordRow.createCell(cellCount++);
    timeCell.setCellValue(algoResult.getTimeTaken());
    XSSFCell ComSubSeqCell = executionRecordRow.createCell(cellCount++);
    ComSubSeqCell.setCellValue(algoResult.getComSubSeq());
    XSSFCell LCSlengthCell = executionRecordRow.createCell(cellCount++);
    LCSlengthCell.setCellValue(algoResult.getLCSlength());
     FileOutputStream excelWriter = new FileOutputStream (this.excelFile,false);
     try {
		ResultBook.write(excelWriter);
	} catch (IOException e) {
	e.printStackTrace();
	}
     
}

}