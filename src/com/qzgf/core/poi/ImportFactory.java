package com.qzgf.core.poi;

import java.io.IOException;

/**
 * 一个单例模式的工厂类，用于创建导出的具体类，有ExportToExcel,ExportXml等。
 * @author chenf
 * */
public class ImportFactory {
	public static ImportIface getInstance(String type,String filename,String[] FileName,String[] Fields){
		ImportIface importdata=null;
		if(type==null){
			return null;
		}else if(type.equals("xls")){
			if(importdata==null || !(importdata instanceof ImportToPageExcel)){
				try {
					importdata = new ImportToPageExcel(filename,FileName,Fields);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(type.equals("pdf")){
			
		}
		return importdata;
		
	}
	
}
