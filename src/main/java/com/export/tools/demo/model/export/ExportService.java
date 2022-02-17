package com.export.tools.demo.model.export;

import com.export.tools.annotation.ExcelFile;
import com.export.tools.dto.ExcelRecordDTO;
import com.export.tools.handler.ExcelHandler;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenxianrui
 * @date $
 */
@Service
@ExcelFile(fileValue = "bull")
public class ExportService implements ExcelHandler {


    @Override
    public String buildExcelFileName(Object obj) {
        return "fileName-10086";
    }

    @Override
    public List<String> buildExcelHead(Object obj) {
        List<String> excelHead = new ArrayList<>();
        excelHead.add("表头a");
        excelHead.add("表头b");
        return excelHead;
    }

    @Override
    public List<List<String>> buildExcelData(Object obj) {
        List<List<String>> excelData = new ArrayList<>();
        List<String> data = new ArrayList<>();
        data.add("sss");
        data.add("ooo");
        excelData.add(data);
        return excelData;
    }
}
