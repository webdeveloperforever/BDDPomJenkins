package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	XSSFWorkbook workbook=null;
	FileInputStream fis=null;
	XSSFSheet sheet=null;
	XSSFRow headerRow=null;
	XSSFRow row=null;
	XSSFCell cell=null;
	int colNum=0;
	List<String> columnData=new ArrayList<>();
	public ExcelUtils(String filePath) {
		try {
			fis=new FileInputStream(filePath);
			workbook=new XSSFWorkbook(fis);
			fis.close();
		}
	catch(FileNotFoundException e) {
		e.printStackTrace();
	}catch(IOException e) {
		e.printStackTrace();
	}
	}
	public List<String> readExcelData(String SheetName,String columnName){
		List<String> columnData=new ArrayList<>();
			sheet=workbook.getSheet(SheetName);
			sheet=workbook.getSheet(SheetName);
			headerRow=sheet.getRow(0);
			int rowCount=sheet.getLastRowNum();
			for(int i=1;i<=rowCount;i++) {
				Row row=sheet.getRow(i);
				Cell cell=row.getCell(getColumnIndex(headerRow,columnName));
				if(cell!=null) {
					DataFormatter dataFormatter=new DataFormatter();
					String value=dataFormatter.formatCellValue(cell);
					columnData.add(value.trim());
				}
			}
		return columnData;
	}
	private int getColumnIndex(Row headerRow, String columnName) {
		// TODO Auto-generated method stub
		for(int j=0;j<headerRow.getLastCellNum();j++) {
			Cell cell=headerRow.getCell(j);
			if(cell!=null && cell.getStringCellValue().trim().equals(columnName.trim())) {
				return j;
			}
		}
		return -1;
	}
}
