<%@page contentType="text/html; charset=utf8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
	//定义全局变量
	String path = request.getContextPath();
%>
<head>
    <title>菜单信息</title> 
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
      //列表结构
      var grid = $("#maingrid").ligerGrid({
          columns: [
          { display: "名称", name: "name", width: 180, type: "text", align: "left" }, 
          { display: "图标", name: "icon", width: 180, type: "text", align: "left" },
          { display: "键接", name: "url", width: 180, type: "text", align: "left" }, 
          { display: "排序号", name: "orderno", width: 180, type: "text", align: "left" }, 
          { display: "是否菜单", name: "ismenuname", width: 100, type: "text", align: "left" }, 
          { display: "父菜单", name: "fathername", width: 180, type: "text", align: "left" }
          ], dataAction: 'server', pageSize: 20, toolbar: {},
          url: '<%=path%>/appsystem/menu!Grid.do', sortName: 'id', 
          width: '98%', height: '100%',heightDiff:-10, checkbox: false
      });

        //双击事件
      LG.setGridDoubleClick(grid, 'modify');

      //搜索表单应用ligerui样式
      $("#formsearch").ligerForm({
		   fields:[
		   { display: "名称", name: "name", newline: false, labelWidth: 100, width: 220, space: 30,  cssClass: "field", attr: { "op": "like"} }
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
                  top.f_addTab(null, '增加菜单信息', '<%=path%>/appsystem/menu!Listadd.do');
                  break;
              case "view":
                  var selected = grid.getSelected();
                  if (!selected) { LG.tip('请选择行!'); return }
                  top.f_addTab(null, '查看菜单信息', '<%=path%>/appsystem/menu!Listdetail.do?search.pid=' + selected.id);
                  break;
              case "modify":
                  var selected = grid.getSelected();
                  if (!selected) { LG.tip('请选择行!'); return }
                  top.f_addTab(null, '修改菜单信息', '<%=path%>/appsystem/menu!Listedit.do?search.pid=' + selected.id);
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
                  type: 'menuAction',
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
