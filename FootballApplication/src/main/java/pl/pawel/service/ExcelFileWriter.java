package pl.pawel.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pl.pawel.model.Match;

public class ExcelFileWriter implements IFileSaver {

	private String path = "C:\\Users\\Pawel\\Desktop\\";
	private String fileName = "CreatedFile.xlsx";
	private String sheetName = "Resuts of Games";
	private Workbook workbook;
	private CreationHelper creationHelper;

	@Override
	public void saveMatch(Match game) {
		String headers[] = { "Home Team", "Away Team", "Result" };

		workbook = new XSSFWorkbook();
		creationHelper = workbook.getCreationHelper();

		CellStyle dataCell = workbook.createCellStyle();
		dataCell.setDataFormat(creationHelper.createDataFormat().getFormat("DD-MM-YYYY"));

		Sheet sheet = workbook.createSheet(sheetName);

		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(this.createStyle());
		}
		Row rowsWithData = sheet.createRow(1);
		rowsWithData.createCell(0).setCellValue(game.getHomeTeam().getClubName());
		rowsWithData.createCell(1).setCellValue(game.getAwayTeam().getClubName());
		rowsWithData.createCell(2).setCellValue(game.getHomeTeamScore() + ":" + game.getAwayTeamScore());
		Cell randomData = rowsWithData.createCell(3);
		randomData.setCellValue(Date.valueOf("2018-05-12"));
		randomData.setCellStyle(dataCell);
		rowsWithData.createCell(4).setCellValue("13.05.2018");
		sheet.autoSizeColumn(1);

		try {
			FileOutputStream fileOut = new FileOutputStream(path + fileName);
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

		// Font
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.AQUA.getIndex());

		cs.setFont(headerFont);
		return cs;
	}

	public void saveListOfMatches(List<Match> listOfGames) {
		String headers[] = { "Home Team", "Away Team", "Result" };

		workbook = new XSSFWorkbook();
		creationHelper = workbook.getCreationHelper();

		CellStyle dataCell = workbook.createCellStyle();
		dataCell.setDataFormat(creationHelper.createDataFormat().getFormat("DD-MM-YYYY"));

		Sheet sheet = workbook.createSheet(sheetName);

		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(this.createStyle());
		}
		int numOfRow = 0;
		for (Match game : listOfGames) {
			Row rowsWithData = sheet.createRow(++numOfRow);
			rowsWithData.createCell(0).setCellValue(game.getHomeTeam().getClubName());
			rowsWithData.createCell(1).setCellValue(game.getAwayTeam().getClubName());
			rowsWithData.createCell(2).setCellValue(game.getHomeTeamScore() + ":" + game.getAwayTeamScore());
		}

		try {
			FileOutputStream fileOut = new FileOutputStream(path + fileName);
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

}
