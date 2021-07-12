//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kit.demo.excel;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelManager {
    public ExcelManager() {
    }

    public Workbook exportToWb(ExportContext ctx)  {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        ctx.setCurSheet(sheet);
        ctx.getLayout().writeContent(ctx);
        return wb;
    }
}
