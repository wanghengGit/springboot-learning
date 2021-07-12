package com.kit.demo.controller;

import com.kit.demo.excel.ExcelManager;
import com.kit.demo.excel.ExportContext;
import com.kit.demo.excel.WoCommonExcelModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author wangheng
 * @date 2021/7/12
 * 文件相关操作
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private ExcelManager excelMgr;
    /**
     * 提交Excel
     *
     * @param excelFile
     * @throws IOException
     */
    @PostMapping("uploadExcel")
    public void uploadExcel(@RequestParam("excelFile") MultipartFile excelFile) throws IOException {

        //excel解析结果
        List<Object> objectList = WoCommonExcelModel.parseWoSheet(excelFile, 1);
        if (objectList == null || objectList.size() < 2) {
            return;
        }
        //移除表头
        objectList.remove(0);

        for (Object o : objectList) {
            WoCommonExcelModel model = (WoCommonExcelModel) o;

            String firstValue = model.getFirstValue();
            System.out.println(firstValue);
            String thirdValue = model.getThirdValue();
            System.out.println(thirdValue);
        }
    }

    /**
     * 文件下载
     * @param response
     */
    @GetMapping
    public void exportModel(HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;fileName=expertModel.xlsx");
        ExportContext ctx = new ExportContext(null, null);
        Workbook wb = excelMgr.exportToWb(ctx);
        wb.write(response.getOutputStream());
        response.getOutputStream().close();
    }
}
