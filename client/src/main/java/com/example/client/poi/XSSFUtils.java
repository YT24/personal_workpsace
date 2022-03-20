package com.example.client.poi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.charts.AxisCrosses;
import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.DataSources;
import org.apache.poi.ss.usermodel.charts.LegendPosition;
import org.apache.poi.ss.usermodel.charts.LineChartSeries;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.charts.XSSFChartAxis;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;
import org.apache.poi.xssf.usermodel.charts.XSSFLineChartData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDispBlanksAs;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.STDispBlanksAs;

public class XSSFUtils {

    public static void test(XSSFSheet sheet) {
        Map<String, Object> params = new HashMap<String, Object> ();

        // 图表位置（B36左上角：AA53左上角）
        int[] position = new int[] {getColumnIndexByAddress("B"), 35, getColumnIndexByAddress("AA"), 52};

        // x轴坐标区域（B60：B90）
        int[] xAxisRange = new int[] {59, 89, getColumnIndexByAddress("B"), getColumnIndexByAddress("B")};

        // 每个系列的数据（D60：D90、J60：J90、P60：P90）
        // 每个系列的标题（D59、J59、P59）
        List<Integer[]> seriesDataRangeList = new ArrayList<Integer[]>();
        seriesDataRangeList .add(new Integer[] {59, 89, getColumnIndexByAddress("D"), getColumnIndexByAddress("D"),
                58, getColumnIndexByAddress("D")});
        seriesDataRangeList .add(new Integer[] {59, 89, getColumnIndexByAddress("J"), getColumnIndexByAddress("J"),
                58, getColumnIndexByAddress("J")});
        seriesDataRangeList .add(new Integer[] {59, 89, getColumnIndexByAddress("P"), getColumnIndexByAddress("P"),
                58, getColumnIndexByAddress("P")});

        params.put("chartPosition", position);
        params.put("xAxisRange", xAxisRange);
        params.put("seriesDataRangeList ", seriesDataRangeList );

        createLineChart(sheet, params);
    }

    /**
     * create line chart
     * @param sheet
     * @param params
     *             chartPosition            int[]{startRow, endRow, startCol, endCol}
     *             xAxisRange               int[]{startRow, endRow, startCol, endCol}
     *             seriesDataRangeList      List<Integer[]>{startRow, endRow, startCol, endCol, titleRow, titleCol}
     */
    private static void createLineChart(XSSFSheet sheet, Map<String, Object> params) {

        int[] position = (int[]) params.get("chartPosition");
        int[] xAxisRange = (int[]) params.get("xAxisRange");
        List<Integer[]> seriesDataRangeList = (List<Integer[]>) params.get("seriesDataRangeList ");

        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor =
                drawing.createAnchor(0, 0, 0, 0, position[0], position[1], position[2], position[3]);

        XSSFChart chart = drawing.createChart(anchor);
        XSSFChartLegend legned = chart.getOrCreateLegend();
        legned.setPosition(LegendPosition.TOP);

        // set blank values as gaps
        CTDispBlanksAs disp = CTDispBlanksAs.Factory.newInstance();
        disp.setVal(STDispBlanksAs.GAP);
        chart.getCTChart().setDispBlanksAs(disp);

        XSSFLineChartData chartData = chart.getChartDataFactory().createLineChartData();
        XSSFChartAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        XSSFChartAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        bottomAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        bottomAxis.setNumberFormat("yyyy/m/d");
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        leftAxis.setMinimum(0);

        // 设置x轴坐标区域（B60：B90）
        ChartDataSource<String> xAxisData =
                DataSources.fromStringCellRange(sheet,
                        new CellRangeAddress(xAxisRange[0], xAxisRange[1], xAxisRange[2], xAxisRange[3]));

        // 设置数据区域，即每个系列的数据（D60：D90、J60：J90、P60：P90）
        for (Integer[] seriesDataRange : seriesDataRangeList ) {
            ChartDataSource<Number> yAxisData =
                    DataSources.fromNumericCellRange(sheet,
                            new CellRangeAddress(seriesDataRange[0], seriesDataRange[1], seriesDataRange[2], seriesDataRange[3]));
            LineChartSeries series = chartData.addSeries(xAxisData, yAxisData);

            // 设置每个系列的标题（D59、J59、P59）
            series.setTitle(sheet.getRow(seriesDataRange[4]).getCell(seriesDataRange[5]).getStringCellValue());
        }

        chart.plot(chartData, bottomAxis, leftAxis);

        // unsmooth series(好像必须写在polt方法之后)
        CTPlotArea plotArea = chart.getCTChart().getPlotArea();
        for (CTLineChart ch : plotArea.getLineChartList()) {
            for (CTLineSer ser : ch.getSerList()) {
                CTBoolean ctBool = CTBoolean.Factory.newInstance();
                ctBool.setVal(false);
                ser.setSmooth(ctBool);
            }
        }
    }

    /**
     * get column index by column address
     */
    public static int getColumnIndexByAddress(String columnAddress) {
        int colNum = 0;

        for (int i = 0; i < columnAddress.length(); i++) {
            char ch = columnAddress.charAt(columnAddress.length() - 1 - i);
            colNum += (ch - 'A' + 1) * Math.pow(26, i);
        }

        return colNum - 1;
    }
}