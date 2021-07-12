package com.kit.demo.model;

import com.kit.demo.excel.ExportProperty;
import lombok.Data;

/**
 * @author wangheng
 * @date 2021/7/12
 */
@Data
public class ExcelTest {

    @ExportProperty(columnName = "id", order = 0)
    private Integer id;

    @ExportProperty(columnName = "名称", order = 1)
    private String name;

}
