package com.qzgf.core.poi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.qzgf.core.common.Util;

 
public class ExportToPoiExcel implements ExportIface {
	private Log log = LogFactory.getLog(ExportToPoiExcel.class);

	//待修改程序
	public boolean export(PrintWriter out, ResultSet rs, String sheetName,
			String[] fields, String[] colName) {
			return false;
	}
	
	//待修改程序
	public boolean export(ServletOutputStream out, ResultSet rs, String sheetName,
			String[] fields, String[] colName) {
		if (out == null)
			return false;
		if (rs == null) {
			return false;
		}

		try {
			//创建工作簿
			HSSFWorkbook wb = new HSSFWorkbook();//创建excel文件
			HSSFSheet sheettemp = wb.createSheet();
			//wb.setSheetName(0, (String) sheetName, (short) 1);//新建一个sheet
			wb.setSheetName(0, (String) sheetName);//新建一个sheet
			//＝＝＝＝＝＝＝＝＝＝＝＝＝创建两行存储表头信息＝＝＝＝＝＝＝＝＝＝＝
//			HSSFRow rowtitle = sheettemp.createRow((short) 0);//添加表头
//			HSSFCell celltitle = rowtitle.createCell((short) 0);
//			celltitle.setEncoding(HSSFCell.ENCODING_UTF_16);//设置字体编码
//			celltitle.setCellValue((String) sheetName);//设置表头内容
//
//			rowtitle = sheettemp.createRow((short) 1);//新增一行用于存放表头内容
//			sheettemp.addMergedRegion(new Region(0, (short) 0, 0,
//					(short) (fields.length - 1)));//合并第一行
//			rowtitle = sheettemp.getRow(0);//获得第一行的引用
//			celltitle = rowtitle.getCell((short) 0);//获得第一行第一个单元格的引用
			//setTitleStyle(wb, celltitle);//设置表头格式

			//=========================行头信息====================
			int j=0;//占用第一行，这个是根据是否有行头确定的，没有设置为0,有设置为1；
			HSSFRow rowfield = sheettemp.createRow((short) j);//新增一行用于存放列头内容
			for (int i = 0; i < colName.length; i++)//根据获取到的列头数据，循环设置列头的内容
			{
				//HSSFCell cellfield = rowfield.createCell((short) i);
				HSSFCell cellfield = rowfield.createCell(i);
				//cellfield.setEncoding(HSSFCell.ENCODING_UTF_16);
				
				cellfield.setCellValue(colName[i]);
				//setFieldStyle(wb, cellfield);//设置列头格式
				int xx = cellfield.getStringCellValue().length();
				sheettemp.setColumnWidth((short) i, (short) (600 * xx));
			}

			//=========================表体信息======================
			int count = 0;
			while (rs.next()) {
				HSSFRow rowbody = sheettemp.createRow((short) (count + j+1));
				for (int i = 0; i < fields.length; i++) {
					HSSFCell cellbody = rowbody.createCell((short) i);
					//cellbody.setEncoding(HSSFCell.ENCODING_UTF_16);
					cellbody.setCellValue(Util.getNulltoStr(rs.getString(fields[i])));
				}
				count++;
			}
			//=======================页尾========================
			
			//=======================将工作薄信息输出
			wb.write(out);
			out.close();

		} catch (SQLException e) {
			if (log.isErrorEnabled())
				log.error(e);
			return false;
		}catch (IOException e) {
			e.printStackTrace(); 
			return false;
		}
		return true;
	}
	
	//设置样式与风格
    private void setFieldStyle(HSSFWorkbook wb ,HSSFCell row){
    	  HSSFCellStyle cellStyle = wb.createCellStyle();
    	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    HSSFFont font = wb.createFont();
	    font.setFontHeightInPoints( (short) 11);   ;
	    font.setFontName("宋体");
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	
	    cellStyle.setFont(font);

    	row.setCellStyle(cellStyle);
    }

}
