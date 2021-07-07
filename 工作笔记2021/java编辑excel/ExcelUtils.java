package com.skyworth.tvmemorymonitor.utils;

import android.os.Environment;
import android.text.TextUtils;

import com.skyworth.tvmemorymonitor.bean.MemInfoBean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtils {
    private static final String TAG = "ExcelUtils";

    public static void saveDataToExcel(HashMap<String, MemInfoBean> lastMemInfoMap, HashMap<String, MemInfoBean> currentMemInfoMap) throws IOException, WriteException {
        File file = new File(Environment.getExternalStorageDirectory()+"/MemoryMonitor.xls");

        WritableWorkbook workbook = Workbook.createWorkbook(file);
        createMemoryTable(workbook, lastMemInfoMap, currentMemInfoMap, 0);
        workbook.write();
        workbook.close();
    }

    private static void createMemoryTable(WritableWorkbook workbook, HashMap<String, MemInfoBean> lastMemInfoMap, HashMap<String, MemInfoBean> currentHashMap, int index) throws IOException, WriteException {
        WritableSheet sheet = workbook.createSheet("内存变化", index);  //单元格
        Label lab = null;
        // 设置每列的宽度
        CellView cellView = new CellView();
        cellView.setSize(8000);
        sheet.setColumnView(0, cellView);
        cellView.setSize(8000);
        sheet.setColumnView(1, cellView);
        cellView.setSize(8000);
        sheet.setColumnView(2, cellView);
        cellView.setSize(8000);
        sheet.setColumnView(3, cellView);


        /****标题部分****/
        // 标题的样式
        jxl.write.WritableFont wfcNav = new jxl.write.WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
        WritableCellFormat titleFormat = new WritableCellFormat(wfcNav);
        workbook.setColourRGB(Colour.ORANGE, 0x03, 0xA9, 0xF4);
        titleFormat.setBackground(Colour.GREEN);
        titleFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK); //BorderLineStyle边框
        titleFormat.setAlignment(Alignment.CENTRE); //设置水平对齐
        titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE); //设置垂直对齐
        titleFormat.setWrap(false); //设置自动换行

        // 标题的内容
        lab = new Label(0, 0, "应用", titleFormat); //Label(col,row,str);
        sheet.addCell(lab);
        long lastSaveTime = SpUtils.getInstance().getLastSaveTime();
        lab = new Label(1, 0, "之前" + DateUtils.formatDate(lastSaveTime)+"的内存参数", titleFormat); //Label(col,row,str);
        sheet.addCell(lab);
        lab = new Label(2, 0, "目前" + DateUtils.formatDate(System.currentTimeMillis())+"的内存参数", titleFormat); //Label(col,row,str);
        sheet.addCell(lab);
        lab = new Label(3, 0, "差值", titleFormat); //Label(col,row,str);
        sheet.addCell(lab);
        /****标题部分 end ****/

        /****内容部分****/
        jxl.write.WritableFont contentFont =new jxl.write.WritableFont(WritableFont.ARIAL,12, WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        WritableCellFormat contentFormat = new WritableCellFormat(contentFont);
        contentFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //BorderLineStyle边框
        contentFormat.setAlignment(Alignment.CENTRE);
        contentFormat.setVerticalAlignment(VerticalAlignment.CENTRE); //设置垂直对齐
        contentFormat.setWrap(true);

        ArrayList<String> keySet = new ArrayList<>(currentHashMap.keySet());
        for (int i = 0; i < keySet.size(); i++) {
            String key = keySet.get(i);
            MemInfoBean memInfoBean = currentHashMap.get(key);
            MemInfoBean lastMemInfoBean;
            lab = new Label(0, i+1, memInfoBean.labelName+"\r\n" +memInfoBean.packageName, contentFormat); //Label(col,row,str);
            sheet.addCell(lab);
            // 差值列
            lab = new Label(2, i+1, "程序大小差值："+memInfoBean.appBytes+"\r\n"
                    +"缓存大小差值："+memInfoBean.cacheBytes+"\r\n"
                    +"数据大小差值："+memInfoBean.dataBytes, contentFormat); //Label(col,row,str);
            sheet.addCell(lab);
            if (lastMemInfoMap.containsKey(key)) {
                lastMemInfoBean = lastMemInfoMap.get(key);
                // 之前的内存参数列
                lab = new Label(1, i+1, "程序大小："+lastMemInfoBean.appBytes+"\r\n"
                        +"缓存大小："+lastMemInfoBean.cacheBytes+"\r\n"
                        +"数据大小："+lastMemInfoBean.dataBytes, contentFormat); //Label(col,row,str);
                sheet.addCell(lab);
                // 差值列
                lab = new Label(3, i+1, "程序大小差值："+(memInfoBean.appBytes - lastMemInfoBean.appBytes)+"\r\n"
                        +"缓存大小差值："+(memInfoBean.cacheBytes - lastMemInfoBean.cacheBytes)+"\r\n"
                        +"数据大小差值："+(memInfoBean.dataBytes - lastMemInfoBean.dataBytes), contentFormat); //Label(col,row,str);
                sheet.addCell(lab);
            }
        }
        /****内容部分 end ****/
    }
}
