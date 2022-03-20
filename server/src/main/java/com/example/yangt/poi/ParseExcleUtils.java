package com.example.yangt.poi;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Slf4j
public class ParseExcleUtils {


    private static  Workbook wb = null;

    private static Sheet sheet = null;

    private static Cell cell = null;

    private static Row row = null;
    /**
     * 获取第一行 列表
     * @param row
     * @return
     */
    public static List<String> getHeaderRow(Row row) {
        List<String> list= new ArrayList<String>();
        for(int i = 0; i < row.getLastCellNum(); i++) {
            if(row.getCell(i) != null){
                row.getCell(i).setCellType(CellType.STRING);
            }
            list.add(row.getCell(i).getStringCellValue());
        }
        return list;
    }

    public static void writeExcel(Map<String, String> result, String finalXlsxPath,InputStream inputStream) {
        OutputStream out = null;
        try {
            File finalXlsxFile = new File(finalXlsxPath);
            if (!(finalXlsxFile.exists())) {
                finalXlsxFile.createNewFile();
            }
            Workbook workBook = getWorkbok(finalXlsxFile.getName(),inputStream);

            Sheet sheet = workBook.createSheet("测试导出excle");



            Row rowTitle = sheet.createRow(0);
            Cell first = rowTitle.createCell(0);
            first.setCellValue("1");

            Cell second = rowTitle.createCell(1);
            second.setCellValue("2");

            Cell third = rowTitle.createCell(2);
            third.setCellValue("3");

            Cell four = rowTitle.createCell(3);
            four.setCellValue("4");

            Cell five = rowTitle.createCell(4);
            five.setCellValue("5");



            try {
                out = new FileOutputStream(finalXlsxPath);
                workBook.write(out);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }


    /**
     * 根据excle文件后缀 回去Workbook
     * @param fileName
     * @param inputStream
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static Workbook getWorkbok(String fileName, InputStream inputStream) throws IOException, InvalidFormatException {
        Workbook wb = null;

        if (fileName.endsWith("xls"))
            wb = new HSSFWorkbook(inputStream);
        else if (fileName.endsWith("xlsx")) {
            wb = new XSSFWorkbook(inputStream);
        }
        return wb;
    }

    /**
     * 将excle表格数据转换为Sheetbean
     * @param sheet
     * @return
     */
    public static SheetBean transformExcleToSheetbean(Sheet sheet){
        SheetBean sheetBean = null;
        if (sheet.getLastRowNum() != 0 && sheet.getRow(0).getPhysicalNumberOfCells() != 0) {
            // 总行数，总列数
            int row_total = sheet.getLastRowNum()+1, cell_total = sheet.getRow(0).getPhysicalNumberOfCells();
            //将表格数据转为二维数组
            String[][] dataArray = new String[row_total][cell_total];
            for (int i = 0; i < row_total; i++) {
                for (int j = 0; j <cell_total ; j++) {
                    if (sheet.getRow(i) != null) {
                        row = sheet.getRow(i);
                        cell = row.getCell(j);
                        if(cell != null){
                            cell.setCellType(CellType.STRING);
                            log.info("第 {} 行，第 {} 列，数据 {}",i+1,j+1,cell.getStringCellValue());
                            dataArray[i][j] = cell.getStringCellValue();
                        }
                    }
                }
            }
            log.info("dataArray:"+dataArray.length);
            sheetBean = new SheetBean();
            sheetBean.setSheetData(dataArray);
            sheetBean.setVarNameList(ParseExcleUtils.getHeaderRow(sheet.getRow(0)));
        }
        return sheetBean;
    }
}
