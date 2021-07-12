//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kit.demo.excel;

import com.kit.demo.util.BeanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import org.codehaus.jackson.map.util.BeanUtil;
//import org.springframework.beans.BeanUtils;

public class DefaultExportLayout extends AbstractExportLayout {
    public DefaultExportLayout() {
    }

    @Override
    public void writeContent(ExportContext ctx)  {
        List<?> dataList = ctx.getDataList();
        Sheet sheet = ctx.getCurSheet();
        List<FieldInfo> fieldInfoList = this.parseFieldInfos(ctx.getClz());
        if (fieldInfoList != null && !fieldInfoList.isEmpty()) {
            int rowIdx = this.writeHeader(sheet, fieldInfoList);
            if (dataList != null && !dataList.isEmpty()) {
                Iterator var6 = dataList.iterator();

                while(var6.hasNext()) {
                    Object data = var6.next();
                    Row row = sheet.createRow(rowIdx++);
                    int cellIdx = 0;
                    Iterator var10 = fieldInfoList.iterator();

                    while(var10.hasNext()) {
                        FieldInfo fieldInfo = (FieldInfo)var10.next();
                        Cell cell = row.createCell(cellIdx++);

                        try {
                            this.setCellValue(cell, BeanUtils.getPropertyValue(data, fieldInfo.getPropName()));
                        } catch (Exception var14) {
                            this.logger.warn("failed to set value for column:{}", fieldInfo.getPropName(), var14);
                        }
                    }
                }
            }

        } else {

        }
    }

    private int writeHeader(Sheet sheet, List<FieldInfo> fieldInfoList) {
        int rowIdx = 0;
//        rowIdx = rowIdx + 1;
        Row row = sheet.createRow(rowIdx);
        int cellIdx = 0;
        Iterator var6 = fieldInfoList.iterator();

        while(var6.hasNext()) {
            FieldInfo fieldInfo = (FieldInfo)var6.next();
            Cell cell = row.createCell(cellIdx++);
            cell.setCellValue(fieldInfo.getColumnName());
        }

        return rowIdx;
    }

    private List<FieldInfo> parseFieldInfos(Class<?> clz) {
        List<FieldInfo> ret = new ArrayList();
        Field[] fields = clz.getDeclaredFields();
        Field[] var4 = fields;
        int var5 = fields.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            if (!"serialVersionUID".equalsIgnoreCase(field.getName())) {
                ExportProperty anno = (ExportProperty)field.getAnnotation(ExportProperty.class);
                if (anno != null) {
                    ret.add(new FieldInfo(field.getName(), anno.columnName(), anno.order()));
                }
            }
        }

        ret.sort((o1, o2) -> {
            return o1.getOrder().compareTo(o2.getOrder());
        });
        return ret;
    }

    private static class FieldInfo {
        private String propName;
        private String columnName;
        private Integer order;

        FieldInfo(String propName, String columnName, int order) {
            this.propName = propName;
            this.columnName = columnName;
            this.order = order;
        }

        public String getPropName() {
            return this.propName;
        }

        public String getColumnName() {
            return this.columnName;
        }

        public Integer getOrder() {
            return this.order;
        }

        public void setPropName(final String propName) {
            this.propName = propName;
        }

        public void setColumnName(final String columnName) {
            this.columnName = columnName;
        }

        public void setOrder(final Integer order) {
            this.order = order;
        }

        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof FieldInfo)) {
                return false;
            } else {
                FieldInfo other = (FieldInfo)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    label47: {
                        Object this$propName = this.getPropName();
                        Object other$propName = other.getPropName();
                        if (this$propName == null) {
                            if (other$propName == null) {
                                break label47;
                            }
                        } else if (this$propName.equals(other$propName)) {
                            break label47;
                        }

                        return false;
                    }

                    Object this$columnName = this.getColumnName();
                    Object other$columnName = other.getColumnName();
                    if (this$columnName == null) {
                        if (other$columnName != null) {
                            return false;
                        }
                    } else if (!this$columnName.equals(other$columnName)) {
                        return false;
                    }

                    Object this$order = this.getOrder();
                    Object other$order = other.getOrder();
                    if (this$order == null) {
                        if (other$order != null) {
                            return false;
                        }
                    } else if (!this$order.equals(other$order)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof FieldInfo;
        }

//        public int hashCode() {
//            int PRIME = true;
//            int result = 1;
//            Object $propName = this.getPropName();
//            int result = result * 59 + ($propName == null ? 43 : $propName.hashCode());
//            Object $columnName = this.getColumnName();
//            result = result * 59 + ($columnName == null ? 43 : $columnName.hashCode());
//            Object $order = this.getOrder();
//            result = result * 59 + ($order == null ? 43 : $order.hashCode());
//            return result;
//        }

        @Override
        public String toString() {
            return "DefaultExportLayout.FieldInfo(propName=" + this.getPropName() + ", columnName=" + this.getColumnName() + ", order=" + this.getOrder() + ")";
        }
    }
}
