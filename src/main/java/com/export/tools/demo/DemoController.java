package com.export.tools.demo;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author chenxianrui
 * @date $
 */
@Controller
public class DemoController {

    @RequestMapping(value = "export")
    public void Export(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        // 设置要导出的文件的名字
        String fileName = "users"  + new Date() + ".xls";

        // 新增数据行，并且设置单元格数据
        int rowNum = 1;

        // headers表示excel表中第一行的表头 在excel表中添加表头
        String[] headers = { "id", "uid", "地址", "城市"};
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        HSSFRow row1 = sheet.createRow(rowNum);
        row1.createCell(0).setCellValue(1);
        row1.createCell(1).setCellValue(2);
        row1.createCell(2).setCellValue(3);
        row1.createCell(3).setCellValue(4);


        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
