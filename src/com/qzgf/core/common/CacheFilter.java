package com.qzgf.core.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.LogRecord;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CacheFilter implements Filter{    
	   
    private static transient Log logger = LogFactory.getLog(CacheFilter.class);    
        
    private Integer cacheTime = 3600 * 24;    
    private List<Pattern> patternList = new ArrayList<Pattern>();    
        
    private static ResourceBundle rb = ResourceBundle.getBundle("cache-pattern");    
    public void destroy() {    
            
     }    
   
    public void doFilter(ServletRequest rq, ServletResponse rqs,    
             FilterChain fc) throws IOException, ServletException {    
            
         fc.doFilter(rq, rqs);    
        if (rq instanceof HttpServletRequest && rqs instanceof HttpServletResponse) {    
             HttpServletRequest request = (HttpServletRequest) rq;    
             HttpServletResponse response = (HttpServletResponse) rqs;    
                
            if (matchPattern(request.getRequestURI())) {    
                 response.setHeader("Cache-Control", "max-age=" + cacheTime);    
                if (logger.isDebugEnabled()) {    
                     StringBuilder sb = new StringBuilder();    
                     sb.append(" set cache control for uri = ").append(request.getRequestURI());    
                     sb.append(" and the cache time is ").append(cacheTime).append(" second");    
                     logger.debug(sb.toString());    
                 }    
             }    
            
         } else {    
            if (logger.isWarnEnabled()) {    
                 logger.warn("---- the request instance is not instanceof HttpServletRequest ---");    
                 logger.warn("---- the response instance is not instanceof HttpServletResponse ---");    
             }    
         }    
            
     }    
   
    public void init(FilterConfig arg0) throws ServletException {    
         Enumeration<String> keys = rb.getKeys();    
        while (keys.hasMoreElements()) {    
             String p = keys.nextElement();    
             String value = rb.getString(p);    
             patternList.add(Pattern.compile(value, Pattern.CASE_INSENSITIVE));    
                
            if (logger.isInfoEnabled()) {    
                 logger.info(">>>>>>>>>>> init the cache pattern " + value);    
             }    
         }    
            
        if (arg0 != null) {    
             String ct = arg0.getInitParameter("cache-time");    
            if (!"".equals(ct) && null != ct) {    
                 cacheTime = new Integer(ct);    
                if (logger.isInfoEnabled()) {    
                     logger.info(">>>>>>>>>> the cache time is " + cacheTime);    
                 }    
             }    
         }    
     }    
        
    private boolean matchPattern(String url) {    
        for (Pattern pattern : patternList) {    
            if (pattern.matcher(url).matches()) {    
                return true;    
             }    
         }    
            
        return false;    
     }    
   
    public static void main(String [] args) throws ServletException {    
         CacheFilter cf = new CacheFilter();    
         cf.init(null);    
         System.out.println(cf.matchPattern("/css/admin.css"));    
     }


}   
