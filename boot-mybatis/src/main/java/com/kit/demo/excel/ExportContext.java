//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kit.demo.excel;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.Arrays;
import java.util.List;

public class ExportContext {
    private List<?> dataList;
    private Class<?> clz;
    private String[] headers;
    private Sheet curSheet;
    private AbstractExportLayout layout;

    public ExportContext(List<?> dataList, Class<?> targetCls) {
        this.dataList = dataList;
        this.clz = targetCls;
    }

    public AbstractExportLayout getLayout() {
        return (AbstractExportLayout)(this.layout == null ? new DefaultExportLayout() : this.layout);
    }

    public List<?> getDataList() {
        return this.dataList;
    }

    public Class<?> getClz() {
        return this.clz;
    }

    public String[] getHeaders() {
        return this.headers;
    }

    public Sheet getCurSheet() {
        return this.curSheet;
    }

    public void setDataList(final List<?> dataList) {
        this.dataList = dataList;
    }

    public void setClz(final Class<?> clz) {
        this.clz = clz;
    }

    public void setHeaders(final String[] headers) {
        this.headers = headers;
    }

    public void setCurSheet(final Sheet curSheet) {
        this.curSheet = curSheet;
    }

    public void setLayout(final AbstractExportLayout layout) {
        this.layout = layout;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ExportContext)) {
            return false;
        } else {
            ExportContext other = (ExportContext)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label63: {
                    Object this$dataList = this.getDataList();
                    Object other$dataList = other.getDataList();
                    if (this$dataList == null) {
                        if (other$dataList == null) {
                            break label63;
                        }
                    } else if (this$dataList.equals(other$dataList)) {
                        break label63;
                    }

                    return false;
                }

                Object this$clz = this.getClz();
                Object other$clz = other.getClz();
                if (this$clz == null) {
                    if (other$clz != null) {
                        return false;
                    }
                } else if (!this$clz.equals(other$clz)) {
                    return false;
                }

                if (!Arrays.deepEquals(this.getHeaders(), other.getHeaders())) {
                    return false;
                } else {
                    Object this$curSheet = this.getCurSheet();
                    Object other$curSheet = other.getCurSheet();
                    if (this$curSheet == null) {
                        if (other$curSheet != null) {
                            return false;
                        }
                    } else if (!this$curSheet.equals(other$curSheet)) {
                        return false;
                    }

                    Object this$layout = this.getLayout();
                    Object other$layout = other.getLayout();
                    if (this$layout == null) {
                        if (other$layout != null) {
                            return false;
                        }
                    } else if (!this$layout.equals(other$layout)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ExportContext;
    }

//    @Override
//    public int hashCode() {
//        int PRIME = true;
//        int result = 1;
//        Object $dataList = this.getDataList();
//        int result = result * 59 + ($dataList == null ? 43 : $dataList.hashCode());
//        Object $clz = this.getClz();
//        result = result * 59 + ($clz == null ? 43 : $clz.hashCode());
//        result = result * 59 + Arrays.deepHashCode(this.getHeaders());
//        Object $curSheet = this.getCurSheet();
//        result = result * 59 + ($curSheet == null ? 43 : $curSheet.hashCode());
//        Object $layout = this.getLayout();
//        result = result * 59 + ($layout == null ? 43 : $layout.hashCode());
//        return result;
//    }

    public String toString() {
        return "ExportContext(dataList=" + this.getDataList() + ", clz=" + this.getClz() + ", headers=" + Arrays.deepToString(this.getHeaders()) + ", curSheet=" + this.getCurSheet() + ", layout=" + this.getLayout() + ")";
    }
}
