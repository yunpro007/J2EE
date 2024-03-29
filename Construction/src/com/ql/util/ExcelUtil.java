package com.ql.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelUtil {
	/**
	 * 拿到初始化的工作簿
	 * @param excelName
	 * @param headers
	 * @return
	 */
	public static final HSSFWorkbook getWorkBook(String excelName,
			  String[] headers){
				if(null == excelName || "".equals(excelName)){
					excelName = "导出文件";
				}
				if(headers == null){
					return null;
				}
				//工作簿
				HSSFWorkbook workbook = new HSSFWorkbook();
				//表格
				HSSFSheet sheet = workbook.createSheet(excelName);
				//设置表格默认列宽度为15个字符  
		        sheet.setDefaultColumnWidth(15); 
		      //生成一个样式，用来设置标题样式  
		        HSSFCellStyle style = workbook.createCellStyle();  
		        //设置这些样式  
		        style.setFillForegroundColor(HSSFColor.BLACK.index);  
		        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
		        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
		        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
		        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
		        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
		        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		        //生成一个字体  
		        HSSFFont font = workbook.createFont();  
		        font.setColor(HSSFColor.VIOLET.index);  
		        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		        //把字体应用到当前的样式  
		        style.setFont(font);  
		        // 生成并设置另一个样式,用于设置内容样式  
		        HSSFCellStyle style2 = workbook.createCellStyle();  
		        style2.setFillForegroundColor(HSSFColor.BLACK.index);  
		        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
		        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
		        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
		        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
		        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
		        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
		        // 生成另一个字体  
		        HSSFFont font2 = workbook.createFont();  
		        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
		        // 把字体应用到当前的样式  
		        style2.setFont(font2);
		        //产生表格标题行  
		        HSSFRow row = sheet.createRow(0); 
		        for(int i=0; i<headers.length; i++){
		        	HSSFCell cell =row.createCell(i);
		        	cell.setCellStyle(style);
		        	HSSFRichTextString text = new HSSFRichTextString(headers[i]);
		        	cell.setCellValue(text);
		        }
		        return workbook;
	}
	
	
	
	/**
	 * 导出到流
	 */
	public final static void exportExcel(HSSFWorkbook workbook, OutputStream out){
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
