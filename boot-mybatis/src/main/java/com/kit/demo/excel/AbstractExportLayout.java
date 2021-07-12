//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kit.demo.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public abstract class AbstractExportLayout {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AbstractExportLayout() {
    }

    public abstract void writeContent(ExportContext ctx) ;

    protected void setCellValue(Cell cell, Object obj) {
        if (obj != null) {
            if (obj instanceof Number) {
                cell.setCellValue(((Number)obj).doubleValue());
            } else if (obj instanceof Date) {
                cell.setCellValue((Date)obj);
            } else {
                cell.setCellValue(obj.toString());
            }
        }

    }
}
