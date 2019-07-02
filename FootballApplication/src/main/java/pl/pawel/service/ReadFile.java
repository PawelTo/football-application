package pl.pawel.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	private static String path = "C:\\Users\\Pawel\\Desktop\\DirToLearn\\Files_To_Read\\";
	private static String fileName = "Results.XML";
	private BufferedReader bfReader;
	private FileReader fileReader;
	private String readLineInFile;
	private List<String[]> dataFromFile;

	private static Logger logger = LoggerFactory.getLogger(ReadFile.class);

	/** Method check the file format and start proper method to get data
	 * @return data from file
	 */
	public List<String[]> getDataFromFile() {
		dataFromFile = new ArrayList<>();
		switch (this.checkFileFormat()) {
		case "txt":
			dataFromFile = this.dataFromTxtFile();
			break;
		case "csv":
			dataFromFile = this.dataFromCSVFile();
			break;
		case "XML":
			dataFromFile = this.dataFromXMLFile();
			break;
		case "xlsx":
		case "xlsm":
			try {
				dataFromExcel(this.workbookXlsxFactory());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "xls":
			try {
				dataFromExcel(this.workbookXlsFactory());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			logger.info("Wrong string format: "+this.checkFileFormat());
			break;
		}
		return dataFromFile;
	}

	/**
	 * @return format of file to which has data to read
	 */
	private String checkFileFormat() {
		return fileName.substring(fileName.indexOf(".")+1);
	}
		
	private List<String[]> dataFromXMLFile() {
		File file = new File(path+fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse(file);
			doc.getDocumentElement().normalize();
			
			NodeList nMatchList = doc.getElementsByTagName("match");
			
			for(int i=0;i<nMatchList.getLength();i++) {
				Node matchNode = nMatchList.item(i);
				Element matchElement = (Element) matchNode;
				String[] match = new String [7];
				match[0] = matchElement.getElementsByTagName("Date").item(0).getTextContent();
				match[1] = matchElement.getElementsByTagName("Home_Team").item(0).getTextContent();
				match[2] = matchElement.getElementsByTagName("Away_Team").item(0).getTextContent();
				match[3] = matchElement.getElementsByTagName("Home_Team_Score").item(0).getTextContent();
				match[4] = matchElement.getElementsByTagName("Away_Team_Score").item(0).getTextContent();
				match[5] = matchElement.getElementsByTagName("Competition").item(0).getTextContent();
				match[6] = matchElement.getElementsByTagName("Attendance").item(0).getTextContent();
				dataFromFile.add(match);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataFromFile;
	}
	
	public List<String[]> dataFromCSVFile() {
		try {
			fileReader = new FileReader(path + fileName);
			bfReader = new BufferedReader(fileReader);
			while ((readLineInFile = bfReader.readLine()) != null)
				dataFromFile.add(readLineInFile.split(";"));
			bfReader.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.info("CSV file not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataFromFile;
	}
	
	public List<String[]> dataFromTxtFile(){
		try {
			fileReader = new FileReader(path + fileName);
			bfReader = new BufferedReader(fileReader);
			while ((readLineInFile= bfReader.readLine()) != null) {
				dataFromFile.add(readLineInFile.split("	"));
			}
			bfReader.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.info("Txt file not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataFromFile;
	}
	
	/**Method to read data from excel file
	 * @param wb - type of excel file do retrieve data
	 * @return List<String[]> - Data from excel file
	 */
	public List<String[]> dataFromExcel(Workbook wb){
		//CELL_SEPARATOR is the string to separate data from each cell during combining it to one String
		final String CELL_SEPARATOR = ";";
		Sheet sheet= wb.getSheetAt(0);
		DataFormatter df = new DataFormatter();
		Iterator<Row> rowIterator = sheet.rowIterator();
		logger.info("Start processing excel: "+fileName);
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			readLineInFile="";
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				readLineInFile = readLineInFile+df.formatCellValue(cell)+CELL_SEPARATOR;
			}
			logger.info("Data from excel: "+readLineInFile);
			dataFromFile.add(readLineInFile.split(CELL_SEPARATOR));
		}
		return dataFromFile;
	}
	
	/** Create XSSFWorkbook - for operation with Excel 2007 and newer
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private Workbook workbookXlsxFactory() throws FileNotFoundException, IOException{
		return new XSSFWorkbook(new FileInputStream(path+fileName));
	}
	
	/** Create HSSFWorkbook - for operation with Excel 2003 and older
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private Workbook workbookXlsFactory() throws FileNotFoundException, IOException {
		return new HSSFWorkbook(new FileInputStream(path+fileName));
	}
}