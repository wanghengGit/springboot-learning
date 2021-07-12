package com.kit.demo.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Data
public class WoCommonExcelModel extends BaseRowModel{
	
//	  @ExcelProperty(index = 0)
//	  private String number;

	  @ExcelProperty(index = 0)
	  private String firstValue;
	  
	  @ExcelProperty(index = 1)
	  private String secondValue;
	  
	  @ExcelProperty(index = 2)
	  private String thirdValue;
	  
	  @ExcelProperty(index = 3)
	  private String fourthValue;
	  
	  private static List<Object> parseSheet(MultipartFile excelFile, Integer sheetNumber) throws IOException {
	        InputStream inputStream = excelFile.getInputStream();
	        List<Object> data = EasyExcelFactory.read(inputStream, new Sheet(sheetNumber, 0, WoCommonExcelModel.class));
	        inputStream.close();
	        return data; 
	  }
	  
	  
	  public static List<Object> parseWoSheet(MultipartFile excelFile, Integer sheetNumber) throws IOException {
	        return parseSheet(excelFile,sheetNumber);
	  }
	
	
}
