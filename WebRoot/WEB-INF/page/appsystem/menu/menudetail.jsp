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
    <script src="<%=path%>/lib/js/iconselector.js" type="text/javascript"></script>
    <script src="<%=path%>/lib/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script> 
</head>
<body style="padding-bottom:31px;">
    <form id="mainform" method="post"></form> 
    <script type="text/javascript"> 
        var config = {"Form":{ 
         fields : [
         {name:"id",type:"hidden"},
         {display:"菜单",name:"name",newline:true,labelWidth:100,width:220,space:30,type:"text",group:"基本信息",groupicon:"../lib/icons/32X32/communication.gif",validate:{maxlength:50,required:true,messages:{required:'请输入密码'}} },
         {display:"父菜单",name:"father",newline:false,labelWidth:100,width:220,space:30,type:"select",comboboxName:"fathername",options:{tree:{
            url :'/main!GetTreeJson.do?rnd='+Math.random(),
            checkbox:false,
            nodeWidth :220
        },valueFieldID:"father",valueField:"id"}},
         //{display:"图标",name:"icon",newline:true,labelWidth:100,width:220,space:30,validate:{maxlength:50,messages:{required:'请输入图标'}}},
          { display: '图标', name: 'icon', align: 'left', newline:true,labelWidth:100,width:220,space:30,type: 'select',
              	options:{ 
              	onBeforeSelect: function (newvalue)
                {
                    alert('要选择的是' + newvalue);
                    return confirm('onBeforeSelect事件可以阻止选择，是否继续');
                },
                
  							onBeforeOpen: function ()
                            {
                            	alert("hello");
                                //currentComboBox = this;
                                f_openIconsWin();
                                return false;
                            },
                            render: function ()
                            {
                                alert("hello1");
                                return '1';
                            }
                }
         },
                
         {display:"链接url",name:"url",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{required:true,minlength:1,maxlength:50,messages:{required:'请输入链接url',maxlength:'链接url有这么长嘛？'}}},
         {display:"排序号",name:"orderno",newline:true,labelWidth:100,width:220,space:30,type:"text",validate:{maxlength:255}},
         {display:"是否菜单",name:"ismenu",newline:false,labelWidth:100,width:220,space:30,type:"select",
         	comboboxName:"ismenuname",options:{data:[
                    { text: '是', id: '1' },
                    { text: '否', id: '0' }
            ],valueFieldID:"ismenu",valueField:"id"}}
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
            LG.loadForm(mainform, { type: 'menuAction', method: 'GetMenu', data: { "search.pid": "<s:property value='%{search.pid}' />"} },f_loaded);
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
                if (data.iserror) {  
                    win.LG.showError('错误:' + data.message);
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

