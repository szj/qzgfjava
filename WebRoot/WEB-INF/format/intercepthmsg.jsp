<%@page contentType="text/html; charset=gbk"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
	//����ȫ�ֱ���
	String path = request.getContextPath();
%>
<script type="text/javascript">
	  //var msg='<s:property value="%{interceptError}" escape="false"/>';
	  //var isSure = confirm('ҳ��Session���ڣ��Ƿ�ת����½ҳ?');if(isSure)
      jQuery.ligerDialog.confirm('ҳ��Session���ڣ��Ƿ�ת����½ҳ?',function (confirm) {
           if(confirm) {
               if(parent.window==this){
               		location.href = '<%=path%>/login.do';
               }else{
               		parent.window.location.href='<%=path%>/login.do';
               }
     		}
      });
</script>