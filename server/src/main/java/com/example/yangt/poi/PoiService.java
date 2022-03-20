package com.example.yangt.poi;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PoiService {

    private Workbook wb = null;

    private Sheet sheet = null;

    private Cell cell = null;

    private Row row = null;


    /**
     * 导入数据至db
     * @param mapping
     * @param file
     * @param request
     * @param response
     * @return
     */
    public String batchImportUser(String mapping, MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        SheetBean sheetBean = this.parseExcleToSheetBean(mapping,file);
        String[][] objStr = sheetBean.getSheetData();
        // EXCEL中的列名
        List<String> varNameList = sheetBean.getVarNameList();
        varNameList = varNameList.stream().map(name -> StringUtils.trimAllWhitespace(name)).collect(Collectors.toList());

        return "SUCCESS";
    }

    /**
     * 读取excle数据
     * @param mapping
     * @param file
     * @return
     */
    public SheetBean parseExcleToSheetBean(String mapping, MultipartFile file) {
        SheetBean sheetBean =null;
        if (file != null) {
            String fileName = file.getOriginalFilename();
            try {
                InputStream inputStream = file.getInputStream();
                wb = ParseExcleUtils.getWorkbok(fileName,inputStream);
                /*int sheetNum = wb.getNumberOfSheets();
                for (int m = 0; m < sheetNum; m++) {
                    if (null != wb.getSheetAt(m)) {
                        sheet = wb.getSheetAt(m);
                    }
                    如果有多个表格 获取多个表格的sheet
                }*/
                sheet = wb.getSheetAt(0);//获取第一个sheet
                if (sheet != null) {
                    sheetBean = new SheetBean();
                    log.info("--- getHeader ---" + sheet.getHeader());
                    log.info("--- getLastRowNum ---" + sheet.getLastRowNum());
                    log.info("--- sheet.getRow(0).getPhysicalNumberOfCells() ---" + sheet.getRow(0).getPhysicalNumberOfCells());
                    sheetBean = ParseExcleUtils.transformExcleToSheetbean(sheet);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sheetBean;
        }
        return sheetBean;
    }
}
