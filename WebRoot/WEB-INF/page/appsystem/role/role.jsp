<%@page contentType="text/html; charset=utf8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
	//定义全局变量
	String path = request.getContextPath();
%>
<head>
    <title>角色信息</title> 
</head>
<body style="padding:10px;height:100%; text-align:center;">
  <div id="mainsearch" style=" width:98%">
    <div class="searchtitle">
        <span>搜索</span><img src="<%=path%>/lib/icons/32X32/searchtool.gif" />
        <div class="togglebtn"></div> 
    </div>
    <div class="navline" style="margin-bottom:4px; margin-top:4px;"></div>
    <div class="searchbox">
        <form id="formsearch" class="l-form"></form> 
    </div>
  </div>
  <div id="maingrid"></div> 
  <script type="text/javascript">
          //权限 保存按钮
        var toolbarOptions = {
            items: [
            { text: '权限分配', click: f_right, img: "../lib/icons/silkicons/page_save.png" }
        ]
        };
        
        function f_right(){
                  top.f_addTab(null, '权限分配', '<%=path%>/appsystem/role!Right.do');
        }
        
        
       var grid = $("#maingrid").ligerGrid({
          columns: [
          { display: "名称", name: "rolename", width: 180, type: "text", align: "left" }, 
          { display: "备注", name: "remark", width: 180, type: "text", align: "left" }
          ], dataAction: 'server', pageSize: 20, toolbar: toolbarOptions,
          url: '<%=path%>/appsystem/role!Grid.do', sortName: 'id', 
          width: '98%', height: '100%',heightDiff:-10, checkbox: false
      });

        //双击事件
      LG.setGridDoubleClick(grid, 'modify');

      //搜索表单应用ligerui样式
      $("#formsearch").ligerForm({
		   fields:[
		   { display: "角色名称", name: "rolename", newline: false, labelWidth: 100, width: 220, space: 30,  cssClass: "field", attr: { "op": "like"} }
		   ],
		   appendID:false,
		   toJSON: JSON2.stringify 
	  });

      //增加搜索按钮,并创建事件
      LG.appendSearchButtons("#formsearch", grid);

      //加载toolbar
      LG.loadToolbar(grid, toolbarBtnItemClick);

      //工具条事件
      function toolbarBtnItemClick(item) {
          switch (item.id) {
              case "add":
                  top.f_addTab(null, '增加角色信息', '<%=path%>/appsystem/role!Listadd.do');
                  break;
              case "view":
                  var selected = grid.getSelected();
                  if (!selected) { LG.tip('请选择行!'); return }
                  top.f_addTab(null, '查看角色信息', '<%=path%>/appsystem/role!Listdetail.do?search.pid=' + selected.id);
                  break;
              case "modify":
                  var selected = grid.getSelected();
                  if (!selected) { LG.tip('请选择行!'); return }
                  top.f_addTab(null, '修改角色信息', '<%=path%>/appsystem/role!Listedit.do?search.pid=' + selected.id);
                  break;
              case "delete":
                  jQuery.ligerDialog.confirm('确定删除吗?', function (confirm) {
                      if (confirm)
                          f_delete();
                  });
                  break;
          }
      }
      function f_reload() {
          grid.loadData();
      }
      
      function f_delete() {
          var selected = grid.getSelected();
          if (selected) { 
				LG.ajax({
                  type: 'roleAction',
                  method: 'Delete',
                  loading: '正在删除中...',
                  data: { "search.pid" : selected.id },
                  success: function () {
                      LG.showSuccess('删除成功');
                      f_reload();
                  },
                  error: function (message) {
                      LG.showError(message);
                  }
              });
          }
          else {
              LG.tip('请选择行!');
          }
      }
  </script>
</body>
</html>
