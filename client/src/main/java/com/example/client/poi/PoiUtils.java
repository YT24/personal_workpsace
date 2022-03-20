package com.example.client.poi;

import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PoiUtils {

    public static void main(String[] args) {
        try {
            FileInputStream is = new FileInputStream("刚才创建的文件所在目录+文件名");
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
            FileOutputStream os = new FileOutputStream("导出的位置");
            //获取创建工作簿的第一页
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            //自动计算
            sheet.setForceFormulaRecalculation(true);
            //给指定的sheet命名
            xssfWorkbook.setSheetName(0, "sheet0");
            //初始化当前的索引，设为当前sheet的最后一行行数
            int allRows = sheet.getLastRowNum();
            //存储当前表格的样式
            XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
            //填充数据
            for (int i = allRows; i <= allRows; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                //遍历列
                for (int j = 1; j <= 10; j++) {
                    XSSFCell cell = row.getCell(j) != null ? row.getCell(j) : row.createCell(j);
                    String cellValue = cell.getStringCellValue();
                    if (cellValue.startsWith("#a1")) {
                        cell.setCellValue(1);
                    }

                }

            }
            //写出
            xssfWorkbook.write(os);
            //TODO 流的处理
            is.close();
            os.flush();
            os.close();
        }catch (Exception e){

        }finally {

        }
    }


}
