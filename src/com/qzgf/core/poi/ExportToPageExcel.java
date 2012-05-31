package com.qzgf.core.poi;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ExportToPageExcel implements ExportIface{
	private Log log = LogFactory.getLog(ExportToPageExcel.class);
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
			int i = 0;
			out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"); 
			out.println("<table border='1' cellpadding='1' cellspacing='0'>");
			out.println("	<thead>");
			out.println("		<tr>");
			for (i = 0; i<colName.length; i++){
				out.println("		<td>"+colName[i]+"</td>");
			}
			out.println("		</tr>");
			out.println("	</thead>");
			out.println("	<tbody>");
			
			while (rs.next()){
				out.println("	<tr>");
				for (i=0; i<fields.length; i++){
					out.println("	<td>"+com.qzgf.core.common.Util.getNulltoStr(rs.getString(fields[i]))+"</td>");
				}
				out.println("	</tr>");
			}
			
			out.println("	</tbody>");
			out.println("</table>");
			
		} catch (SQLException e) {
			if (log.isErrorEnabled())
				log.error(e);
			return false;
		}
		return true;
	}
}
