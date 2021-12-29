package com.export.tools.factory;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.export.tools.annotation.ExcelFile;
import com.export.tools.constant.StrConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * @author chenxianrui
 * @date $
 */
@Aspect
@Configuration
@Slf4j
public class ExportFactory {
    private static HttpServletResponse response = null;
    private static HSSFWorkbook workbook = new HSSFWorkbook();
    private static HSSFSheet sheet = workbook.createSheet("chenxianrui");
    private static String fileName = null;

    @Pointcut("@within(com.export.tools.annotation.ExcelFile)")
    public void excelPoint(){

    }

    @Pointcut("@annotation(com.export.tools.annotation.Test)")
    public void testPoint(){

    }

    @Around("testPoint()")
    public void test(){
        log.info("切面无问题");
    }

    @Around("excelPoint()")
    public void excelAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("环绕执行");
        response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//        Class<?> targetCls = joinPoint.getTarget().getClass();
//        ExcelFile excelFile = targetCls.getAnnotation(ExcelFile.class);
//        sheet = workbook.createSheet(excelFile.fileValue());
        joinPoint.proceed();

    }

    @AfterReturning(value = "excelPoint()", returning="returnValue")
    public void excelCall(JoinPoint jp, Object returnValue) throws Throwable {
        log.info("解析数据");
        String name = jp.getSignature().getName();
        List<String> head = null;
        List<List<String>> data = null;
        if (name.equals("buildExcelHead")){
            head = (List<String>) returnValue;
        }else if (name.equals("buildExcelData")){
            data = (List<List<String>>) returnValue;
        }else {
            fileName = (String) returnValue;
        }
        fillHeadAndData(head, data);
    }

    private void fillHeadAndData(List<String> head, List<List<String>> data) throws IOException {
        if (head != null){
            fillExcelHead(head);
        }
        if (data != null){
            fillExcelData(data);
        }
    }

    private void fillExcelHead(List<String> head){
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<head.size();i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(head.get(i));
            cell.setCellValue(text);
        }
    }

    private void fillExcelData(List<List<String>> data) throws IOException {
        int rowNum = 1;
        for (int i = 0; i < data.size(); i++) {
            HSSFRow row1 = sheet.createRow(rowNum);
            for (int j = 0; j<data.get(i).size(); j++){
                row1.createCell(j).setCellValue(data.get(i).get(j));
            }
            rowNum++;
        }
        fill();
    }

    private void fill() throws IOException {
        response.setContentType(StrConstants.CONSTANT_TYPE);
        response.setHeader(StrConstants.HEADER_S1,
                String.format(StrConstants.HEADER_S2,
                        String.format(StrConstants.FILE_NAME, fileName)));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}
