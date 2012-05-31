<%@page contentType="text/html; charset=utf8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
	//定义全局变量
	String path = request.getContextPath();
%>
<head>
    <title>用户 明细</title>
    <script src="<%=path%>/lib/jquery.form.js" type="text/javascript"></script>
    <script src="<%=path%>/lib/js/validator.js" type="text/javascript"></script>
</head>
<body style="padding-bottom:31px;">
    <form id="mainform" method="post"></form> 
    <script type="text/javascript"> 
        var config = {"Form":{ 
         fields : [
         {name:"id",type:"hidden"},
         {display:"菜单",name:"name",newline:true,labelWidth:100,width:220,space:30,type:"text",group:"基本信息",groupicon:"../lib/icons/32X32/communication.gif",validate:{maxlength:50,required:true,messages:{required:'请输入密码'}} },
         {display:"父菜单",name:"father",newline:false,labelWidth:100,width:220,space:30,type:"select",comboboxName:"DepartmentDeptName",options:{tree:{
            url :'/main!GetTreeJson.do?rnd='+Math.random(),
            checkbox:false,
            nodeWidth :220
        },valueFieldID:"id",valueField:"father"}},
         {display:"图标",name:"icon",newline:true,labelWidth:100,width:220,space:30,type:"password",validate:{maxlength:50,required:true,messages:{required:'请输入密码'}}},
         {display:"链接url",name:"url",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true,minlength:1,maxlength:50,messages:{required:'请输入姓名',maxlength:'你的名字有这么长嘛？'}}},
         {display:"排序号",name:"orderno",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{maxlength:255}},
         {display:"是否菜单",name:"ismenu",newline:false,labelWidth:100,width:220,space:30,type:"text",validate:{maxlength:50}}
         ]
 }};

        var forbidFields = [];

        LG.adujestConfig(config,forbidFields);

        var action="<s:property value='%{search.action}' />";
        var isView=false;
        var isEdit=false;
        var isAddNew=false;
        if(action=="View"){
        	isView=true;
        }else if (action=="Edit"){
        	isEdit=true;
        }else if (action=="Add"){
        	isAddNew=true;
        }
        //覆盖本页面grid的loading效果
        LG.overrideGridLoading(); 

        //表单底部按钮 
        LG.setFormDefaultBtn(f_cancel,isView ? null : f_save);

        var deptTree = {
            url :'../handler/tree.ashx?view=CF_Department&idfield=DeptID&textfield=DeptName&pidfield=DeptParentID',
            checkbox:false,
            nodeWidth :220
        };

        //创建表单结构
        var mainform = $("#mainform");  
        mainform.ligerForm({ 
         inputWidth: 280,
         fields : config.Form.fields,
		 toJSON:JSON2.stringify
        });

        var actionRoot = "/appsystem/menu!";
        if(isEdit){ 
            $("#username").attr("readonly", "readonly").removeAttr("validate");
            mainform.attr("action", actionRoot + "Edit.do"); 
        }
        
        if (isAddNew) {
            mainform.attr("action", actionRoot + "Add.do");
        }
        else { 
            LG.loadForm(mainform, { type: 'userAction', method: 'GetUser', data: { "search.pid": "<s:property value='%{search.pid}' />"} },f_loaded);
        }  

        
          
        if(!isView) 
        {
            //验证
            jQuery.metadata.setType("attr", "validate"); 
            LG.validate(mainform);
        } 

		function f_loaded()
        {
            if(!isView) return; 
            //查看状态，控制不能编辑
            $("input,select,textarea",mainform).attr("readonly", "readonly");
        }
        function f_save()
        {
            LG.submitForm(mainform, function (data) {
                var win = parent || window;
                if (data.IsError) {  
                    win.LG.showError('错误:' + data.Message);
                }
                else { 
                    win.LG.showSuccess('保存成功', function () { 
                        win.LG.closeAndReloadParent(null, "201205");
                    });
                }
            });
        }
        function f_cancel()
        {
            var win = parent || window;
            win.LG.closeCurrentTab(null);
        }
    </script>
</body>
</html>

