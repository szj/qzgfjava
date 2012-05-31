package com.qzgf.core.poi;

import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 报表一表头
 * @author Administrator
 *
 */
public class ExportToReportOneExcel implements ExportIface{
	private Log log = LogFactory.getLog(ExportToReportOneExcel.class);
	public boolean export(ServletOutputStream out, ResultSet rs, String sheetName,
			String[] fields, String[] colName){
		return false;
	}	
	public boolean export(PrintWriter out, ResultSet rs, String sheetName, String[] fields, String[] colName){
		if (out == null)
			return false;
		if (rs == null) {
			return false;
		}
		
		try {
			out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"); 
			out.println("<table border='1' cellpadding='1' cellspacing='0'>");
			out.println("	<thead>");
			out.println("		<tr>");
				
			out.println("			<td colspan='3' height='30' right='right'>机构名称：</td>");
			out.println("			<td colspan='3' height='30'>&nbsp;</td>");
			out.println("			<td colspan='15' height='30'>&nbsp;</td>");
			out.println("			<td colspan='5' align='right'>制表日期：</td>");
			out.println("			<td colspan='2'>&nbsp;　</td>");
			
			out.println("		</tr>");
			
			out.println("		<tr>");
			
			out.println("			<td  rowspan='4' height='108'>单位</td>");
			out.println("			<td colspan='22'>干部、职工婚育情况</td>");
			out.println("			<td rowspan='4' width='22'>临时工人数</td>");
			out.println("			<td colspan='3'>流动人口</td>");
			
			out.println("		</tr>");
			
			out.println("		<tr>");
			
			out.println("			<td colspan='7' height='27'>干部、职工总人数</td>");
			out.println("			<td colspan='2'>未婚干部职工</td>");
			out.println("			<td colspan='6'>已婚夫妇</td>");
			out.println("			<td colspan='7'>采取各种节育措施的已婚育龄夫妇人数</td>");
			out.println("			<td rowspan='3' width='16'>人数</td>");
			out.println("			<td colspan='2'>其中</td>");
			
			out.println("		</tr>");
			
			
			out.println("		<tr>");
			
			out.println("			<td rowspan='2' height='62' width='16'>合计</td>");
			out.println("			<td colspan='4'>其中：</td>");
			out.println("			<td rowspan='2' width='31'>单职工</td>");
			out.println("			<td rowspan='2' width='31'>双职工</td>");
			out.println("			<td rowspan='2' width='16'>男</td>");
			out.println("			<td rowspan='2' width='35'>女</td>");
			out.println("			<td rowspan='2' width='16'>合计</td>");
			out.println("			<td colspan='4'>其中：</td>");
			out.println("			<td rowspan='2' width='37'>独生子女父母光荣证</td>");
			out.println("			<td rowspan='2' width='16'>合计</td>");
			out.println("			<td rowspan='2' width='27'>男性绝育</td>");
			out.println("			<td rowspan='2' width='26'>女性绝育</td>");
			out.println("			<td rowspan='2' width='37'>放置宫内节育器</td>");
			out.println("			<td rowspan='2' width='32'>皮下埋植</td>");
			out.println("			<td rowspan='2' width='40'>综合措施</td>");
			out.println("			<td rowspan='2' width='16'>其他</td>");
			out.println("			<td rowspan='2' width='16'>持婚育证</td>");
			out.println("			<td rowspan='2' width='280'>纳入管理</td>");
			
			out.println("		</tr>");
			
			
			out.println("		<tr>");
			
			out.println("			<td height='39' width='16'>干部</td>");
			out.println("			<td width='30'>职工</td>");
			out.println("			<td width='39'>合同制工人</td>");
			out.println("			<td width='42'>不在岗人数</td>");
			out.println("			<td width='16'>无孩</td>");
			out.println("			<td width='33'>一孩</td>");
			out.println("			<td width='31'>二孩</td>");
			out.println("			<td width='30'>多孩</td>");
			
			out.println("		</tr>");
			
			
			out.println("	</thead>");
			/*out.println("	<tbody>");
			
			while (rs.next()){
				out.println("	<tr>");
				for (i=0; i<28; i++){
					out.println("	<td>"+PubFunction.getNulltoStr(rs.getString(fields[i]))+"</td>");
				}
				out.println("	</tr>");
			}
			
			out.println("	</tbody>");*/
			out.println("</table>");
			
		} catch (Exception e) {
			if (log.isErrorEnabled())
				log.error(e);
			return false;
		}
		return true;
	}
}
