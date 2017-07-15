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
	 * �õ���ʼ���Ĺ�����
	 * @param excelName
	 * @param headers
	 * @return
	 */
	public static final HSSFWorkbook getWorkBook(String excelName,
			  String[] headers){
				if(null == excelName || "".equals(excelName)){
					excelName = "�����ļ�";
				}
				if(headers == null){
					return null;
				}
				//������
				HSSFWorkbook workbook = new HSSFWorkbook();
				//���
				HSSFSheet sheet = workbook.createSheet(excelName);
				//���ñ��Ĭ���п��Ϊ15���ַ�  
		        sheet.setDefaultColumnWidth(15); 
		      //����һ����ʽ���������ñ�����ʽ  
		        HSSFCellStyle style = workbook.createCellStyle();  
		        //������Щ��ʽ  
		        style.setFillForegroundColor(HSSFColor.BLACK.index);  
		        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
		        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
		        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
		        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
		        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
		        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		        //����һ������  
		        HSSFFont font = workbook.createFont();  
		        font.setColor(HSSFColor.VIOLET.index);  
		        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		        //������Ӧ�õ���ǰ����ʽ  
		        style.setFont(font);  
		        // ���ɲ�������һ����ʽ,��������������ʽ  
		        HSSFCellStyle style2 = workbook.createCellStyle();  
		        style2.setFillForegroundColor(HSSFColor.BLACK.index);  
		        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
		        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
		        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
		        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
		        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
		        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
		        // ������һ������  
		        HSSFFont font2 = workbook.createFont();  
		        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
		        // ������Ӧ�õ���ǰ����ʽ  
		        style2.setFont(font2);
		        //������������  
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
	 * ��������
	 */
	public final static void exportExcel(HSSFWorkbook workbook, OutputStream out){
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
