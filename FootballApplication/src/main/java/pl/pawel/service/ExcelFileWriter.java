package pl.pawel.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pl.pawel.model.Match;

public class ExcelFileWriter implements IFileSaver{

	private String path = "C:\\Users\\Pawel\\Desktop\\";
	private String fileName = "CreatedFile.xlsx";
	private String sheetName = "Resuts of Games";
	private Workbook workbook;
	
	@Override
	public void saveMatch(Match game) {
		String headers [] = {"Home Team","Away Team","Result"};
		
		workbook = new XSSFWorkbook();
		
		Sheet sheet = workbook.createSheet(sheetName);
		Row headerRow = sheet.createRow(0);
		for(int i=0;i<headers.length;i++) {
			headerRow.createCell(i).setCellValue(headers[i]);
		}
		Row rowsWithData = sheet.createRow(1);
		rowsWithData.createCell(0).setCellValue(game.getHomeTeam().getClubName());
		rowsWithData.createCell(1).setCellValue(game.getAwayTeam().getClubName());
		rowsWithData.createCell(2).setCellValue(game.getHomeTeamScore()+":"+game.getAwayTeamScore());
		
		try {
			FileOutputStream fileOut = new FileOutputStream(path+fileName);
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private CellStyle createStyle() {
		CellStyle cs = workbook.createCellStyle();
		
		return cs;
	}
	
}
