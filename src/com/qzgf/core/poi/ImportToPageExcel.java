package com.qzgf.core.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ImportToPageExcel implements ImportIface {
	private Log log = LogFactory.getLog(ImportToPageExcel.class);
	// 文件类型
	private String filetype;
	// 文件二进制输入流
	private InputStream is = null;
	// 当前的Sheet
	private int currSheet;
	// 当前位置
	private int currPosition=1;//默认第一行为标题行,所以不读,从第二行开始读取为字段名
	// Sheet数量
	private int numOfSheets;
	// HSSFWorkbook
	HSSFWorkbook workbook = null;

	//将每行记录存入HashMap中
	private HashMap headHash=new HashMap();//头部HashMap
	
	String[] columnsvalue;
	String[] fieldsvalue;
	// 构造函数创建一个ExcelReader
	public ImportToPageExcel(String inputfile,String[] columns,String[] Fields) throws IOException, Exception {
		// 判断参数是否为空或没有意义
		if (inputfile == null || inputfile.trim().equals("")) {
			throw new IOException("no input file specified");
		}
		//初始化行头与列头
		columnsvalue=columns;
		fieldsvalue=Fields;
		// 取得文件名的后缀名赋值给filetype
		this.filetype = inputfile.substring(inputfile.lastIndexOf(".") + 1);
		// 设置开始行为0 
		currPosition =1;//默认第一行为标题行,所以不读,从第二行开始读取为字段名
		// 设置当前位置为0
		currSheet = 0;
		// 创建文件输入流
		is = new FileInputStream(inputfile);
		if (filetype.equalsIgnoreCase("xls")) {
			// 如果是Excel文件则创建HSSFWorkbook读取
			workbook = new HSSFWorkbook(is);
			// 设置Sheet数
			numOfSheets = workbook.getNumberOfSheets();
		}
		else {
			throw new Exception("文件类型不支持");
		}
		//设置行头
		headHash=readLine();
	}
	// 函数readLine读取文件的一行
	public HashMap readLine() throws IOException {
		// 如果是XLS文件则通过POI提供的API读取文件
		if (filetype.equalsIgnoreCase("xls")) {
			// 根据currSheet值获得当前的sheet
			HSSFSheet sheet = workbook.getSheetAt(currSheet);
			// 判断当前行是否到但前Sheet的结尾
			if (currPosition > sheet.getLastRowNum()) {
				// 当前行位置清零
				currPosition = 1;//默认第一行为标题行,所以不读,从第二行开始读取为字段名
				// 判断是否还有Sheet
				while (currSheet != numOfSheets - 1) {
					// 得到下一张Sheet
					currSheet++;
					sheet = workbook.getSheetAt(currSheet);
					// 当前行数是否已经到达文件末尾
					if (currPosition == sheet.getLastRowNum()) {
						// 当前Sheet指向下一张Sheet
						continue;
					} else {
						// 获取当前行数
						int row = currPosition;
						//设置行头
						headHash.clear();
						headHash=getLine(sheet, row);
						//读取第一列，其他，主要是外层循环起作用
						currPosition++;
						row = currPosition;
						currPosition++;
						// 读取当前行数据
						return convertHash(headHash,getLine(sheet, row),columnsvalue,fieldsvalue);
					}
				}
				return null;
			}
			// 获取当前行数
			int row = currPosition;
			currPosition++;
			// 读取当前行数据
			return convertHash(headHash,getLine(sheet, row),columnsvalue,fieldsvalue);
		}
		return null;
	}
	/**
	 * 
	 * @param hdata行头
	 * @param ddata数据信息
	 * @param columns列名称
	 * @param fileds列代码
	 * @return
	 */
	private HashMap convertHash(HashMap hdata,HashMap ddata,String[] columns,String[] fileds)
	{
		HashMap resultHash=new HashMap();
		if(hdata.size()!=0)
		{
			String value="";
			String key="";
			for(int i=0;i<ddata.size();i++)
			{
				value=ddata.get(i).toString();
				key=hdata.get(i).toString();
				key=fileds[getIndex(columns,key)];
				resultHash.put(key, value);
			}
		}
		else
		{
			resultHash=ddata;
		}
		return resultHash;
	}
	
	private int getIndex(String[] columns,String value)
	{
		int result=-1;
		int length=columns.length;
		for(int i=0;i<length;i++)
		{
			if(columns[i].equals(value))
			{
				result=i;
				break;
			}
		}
		return result;
	}

	// 函数getLine返回Sheet的一行数据

	private HashMap getLine(HSSFSheet sheet, int row) {
		// 根据行数取得Sheet的一行
		HSSFRow rowline = sheet.getRow(row);
		// 创建字符创缓冲区
		StringBuffer buffer = new StringBuffer();
		// 获取当前行的列数
		int filledColumns = rowline.getLastCellNum();
		HSSFCell cell = null;
		// 循环遍历所有列
		//在放入数据之前，先清除数据
		HashMap dataHash=new HashMap();//数据HashMap
		dataHash.clear();
		for (int i = 0; i < filledColumns; i++) {
			// 取得当前Cell
			cell = rowline.getCell(i);//当为空时,列出错.
			String cellvalue = null;
			if (cell != null) {
				// 判断当前Cell的Type
				switch (cell.getCellType()) {
				// 如果当前Cell的Type为NUMERIC
				case HSSFCell.CELL_TYPE_NUMERIC: {
					// 判断当前的cell是否为Date
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						// 如果是Date类型则，取得该Cell的Date值
						Date date = cell.getDateCellValue();
						// 把Date转换成本地格式的字符串
						//cellvalue = cell.getDateCellValue().toLocaleString();
						cellvalue = cell.getDateCellValue().toString();
					}
					// 如果是纯数字
					else {
						// 取得当前Cell的数值
						double num =cell.getNumericCellValue();
						DecimalFormat  df=new DecimalFormat("###########");
						cellvalue=df.format(num);
					}
					break;
				}
				// 如果当前Cell的Type为STRIN
				case HSSFCell.CELL_TYPE_STRING:
					// 取得当前的Cell字符串
					cellvalue = cell.getStringCellValue().replaceAll("'", "''");
					cellvalue=cellvalue.replace(" ", "");
					//cell.getRichStringCellValue().toString().replace(oldChar, newChar)
					break;
				// 默认的Cell值
				default:
					cellvalue = " ";//列为空值时,用空串代替
				}
			} else {
				cellvalue = "";//异常列信息时
				continue;
			}
			dataHash.put(i, cellvalue);//将数据放入HashMap
		}
		// 以字符串返回该行的数据
		return dataHash;
	}

	// close函数执行流的关闭操作

	public void close() {
		// 如果is不为空，则关闭InputSteam文件输入流
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				is = null;
			}
		}
	}

	
	public static void main(String[] args){   
	      try{   
	    	  String[] tmp={"号码","总金额","最近缴费时间","所属地","用户名","品牌","缴费网点","更新网点"};
	    	  String[] tmp1 = {"AA","BB","CC","DD","EE","FF","GG","HH"};
	    	  ImportToPageExcel er=new ImportToPageExcel("d:/shen.xls",tmp,tmp1);      
	          HashMap line=er.readLine();
	          while(line != null){   
	              System.out.println(line);   
	              line=er.readLine();   
	          }
	          er.close();   
	      }catch(Exception e){   
	          e.printStackTrace();   
	      }   
	  }  
}
