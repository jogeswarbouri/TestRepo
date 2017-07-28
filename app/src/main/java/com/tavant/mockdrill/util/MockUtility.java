package com.tavant.mockdrill.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.tavant.mockdrill.model.LoanData;

public class MockUtility {
	
	public Set<LoanData> excelToJson(MultipartFile myFile){
		Set<LoanData> ingestData = new HashSet<LoanData>();
		try{
			HSSFWorkbook workbook=new HSSFWorkbook(myFile.getInputStream());
			HSSFSheet Sheet=workbook.getSheetAt(0);
			int i=0;
			
			LoanData pojoClass =  new LoanData();
			while(i<=Sheet.getLastRowNum()){
				
				HSSFRow row = Sheet.getRow(i++);
				int columnCount = row.getLastCellNum();
				JSONObject json = new JSONObject();
				for(int n=0;n<columnCount;n++){
					json.put("F"+n, row.getCell(n).getStringCellValue());
				}
				
				pojoClass.setLoanId(Integer.parseInt(row.getCell(0).getStringCellValue())+"");
				//pojoClass.setLoanData(json);
				ingestData.add(pojoClass);
			}
			workbook.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return ingestData;
	}
	public Set<LoanData> excelToJson2(MultipartFile myFile){
		Set<LoanData> ingestData = new HashSet<LoanData>();
		try {
			int i = 1;
			boolean header =true;
			List<String> headers = new ArrayList<String>();
			// Creates a workbook object from the uploaded excelfile
			XSSFWorkbook workbook = new XSSFWorkbook(myFile.getInputStream());
			// Creates a worksheet object representing the first sheet
			XSSFSheet worksheet = workbook.getSheetAt(0);
			//Iterator<Row> iterator = worksheet.iterator();
			//iterator.hasNext()
			
			// Reads the data in excel file until last row is encountered
			while (i <= worksheet.getLastRowNum()) {
				LoanData pojoClass =  new LoanData();
				// Creates an object for the UserInfo Model
				// Creates an object representing a single row in excel
				XSSFRow row = worksheet.getRow(i++);
				int columnCount = row.getLastCellNum();
				JSONObject json = new JSONObject();
				for(int n=1;n<columnCount;n++){
					if(null !=row.getCell(n) && null != row.getCell(n).getRawValue()){
						if(header){
							if (row.getCell(n).getCellType() == XSSFCell.CELL_TYPE_STRING){
								headers.add(row.getCell(n).getStringCellValue());
							}else if (row.getCell(n).getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
								headers.add(row.getCell(n).getRawValue());
							}else if (row.getCell(n).getCellType() == XSSFCell.CELL_TYPE_BLANK){
								headers.add(row.getCell(n).getRawValue());
							}else if (row.getCell(n).getCellType() == XSSFCell.CELL_TYPE_BOOLEAN){
								headers.add(row.getCell(n).getRawValue());
							}
							header = false;
						}else{
							json.put(headers.get(n), row.getCell(n).getRawValue());
						}
						System.out.println(row.getCell(n).getCellType());
					}
				}
				if(null != row.getCell(0).getRawValue()){
			//	pojoClass.setLoanId(row.getCell(0).getRawValue());
				}
				pojoClass.setLoanData(json.toString());
				ingestData.add(pojoClass);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return ingestData;
	}
	
	public List<LoanData> excelToJson1(MultipartFile myFile){
		List<LoanData> ingestData = new ArrayList<LoanData>();
		try {
			boolean header =true;
			List<String> headers = new ArrayList<String>();
			XSSFWorkbook wb = new XSSFWorkbook(myFile.getInputStream());
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			XSSFCell cell;
			Iterator rows = sheet.rowIterator();
			int count=0;
			Map<String, String> data = new HashMap<String, String>();
	
			while (rows.hasNext()){
				LoanData pojoClass =  new LoanData();
				row=(XSSFRow) rows.next();
				Iterator cells = row.cellIterator();
				JSONObject json = new JSONObject();
				int cellposition =0;
				while (cells.hasNext() && cellposition<10){
					cell=(XSSFCell) cells.next();
					if(header){
						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
							headers.add(cell.getStringCellValue());
							//System.out.print(cell.getStringCellValue()+",");
						}else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
							headers.add(cell.getNumericCellValue()+"");
							//System.out.print(cell.getNumericCellValue()+",");
						}else{
							headers.add("");
						}
						
					}else{
						if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
							json.put(headers.get(cellposition), cell.getStringCellValue());
						}else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
							Double d = new Double(cell.getNumericCellValue());
							Integer ivalue = d.intValue();
							
							json.put(headers.get(cellposition), ivalue+"");
						}else{
							json.put(headers.get(cellposition), " ");
						}
						
					}
					cellposition++;
					}
					if(!header){
					if(null != row.getCell(0).getRawValue()){
						Double d = new Double(row.getCell(0).getNumericCellValue());
						Integer iLoanvalue = d.intValue();
						pojoClass.setLoanId(iLoanvalue+"");
					}
					pojoClass.setLoanData(json.toString());
					ingestData.add(pojoClass);
					}
					header = false;
					}
					wb.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
		return ingestData;
	}

}
