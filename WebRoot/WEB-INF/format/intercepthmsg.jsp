<%@page contentType="text/html; charset=utf8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
	//定义全局变量
	String path = request.getContextPath();

%>
<script>
	  var msg='<s:property value="%{interceptError}" escape="false"/>';
	
      var isSure = confirm('页面Session过期，是否转到登陆页?');
      if(isSure) {
               if(parent.window==this){
               	location.href = '<%=path%>/login.do';
               }else{
               parent.window.location.href='<%=path%>/login.do';
               }
     }
</script>