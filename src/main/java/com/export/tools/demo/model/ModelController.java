package com.export.tools.demo.model;

import com.export.tools.annotation.ExcelFile;
import com.export.tools.demo.model.export.ExportService;
import com.export.tools.dto.ExcelRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenxianrui
 * @date $
 */
@Controller
public class ModelController {

    @Autowired
    private ExportService exportService;


    @RequestMapping(value = "test")
    @ResponseBody
    public void test(){
        ExcelRecordDTO excelRecordDTO = new ExcelRecordDTO();
        exportService.buildExcelFileName(excelRecordDTO);
        exportService.buildExcelHead(excelRecordDTO);
        exportService.buildExcelData(excelRecordDTO);
        System.out.println("--");
    }

}
