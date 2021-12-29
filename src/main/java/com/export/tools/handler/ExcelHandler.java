package com.export.tools.handler;

import com.export.tools.dto.ExcelRecordDTO;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @author chenxianrui
 * @date $
 */
public interface ExcelHandler {

    /**
     * 文件名
     * @param obj
     * @return
     */
    String buildExcelFileName(Object obj);

    /**
     * 填充excel表头
     * @return
     */
    List<String> buildExcelHead(Object obj);


    /**
     * 填充excel数据
     * @return
     */
    List<List<String>> buildExcelData(Object obj);
}
