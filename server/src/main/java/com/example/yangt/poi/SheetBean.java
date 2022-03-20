package com.example.yangt.poi;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class SheetBean implements Serializable {
	private static final long serialVersionUID = 7937590451967228518L;
	
	/**
	 * Excel Sheet中的第二行的变量名集合
	 */
	private List<String> varNameList;
	/**
	 * 从Sheet中第三行开始的所有数据，行列转换成二维数组格式
	 */
	private String[][] sheetData;
	
	public List<String> getVarNameList() {
		return varNameList;
	}
	public void setVarNameList(List<String> varNameList) {
		this.varNameList = varNameList;
	}
	public String[][] getSheetData() {
		for (int i = 0; i < sheetData.length; i++) {
		}
		return sheetData;
	}
	public void setSheetData(String[][] sheetData) {
		this.sheetData = sheetData;
	}
	
}
