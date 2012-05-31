项目说明
src下配置文件
spring的配置文件
applicationContext.xml  //共用类及数据库连接的配置
applicationContext-action.xml //控制层注入的action
applicationContext-domian.xml //实现层注入的domain

export-config.xml //poi数据库导入设制
log4jjavaframe.properties //log4j的配置信息

sqlMap-common.xml//ibatis的共用sql配置，如主键生成等。
sqlMap-config.xml//加载sqlmap的文件
在业务模块的sqlmap文件夹下有sqlmap-业务模块名称.xml

struts.xml//加载struts的文件
在业务模块的action文件夹下有xwork-业务模块名称.xml

com.qzgf下有两个包
com.qzgf.application为业务模块包
    BaseAction为基类的action封装了统一的json返回格式
	appsystem为系统的“系统管理”模块，当业务模块为"基础档案管理"时，可增加包名archive(即com.qzgf.appliction.archive)
	以用户管理为例，他就位于appsystem下的user包下。
	  action:为该业务模块的控制层。
	  domain:为该业务模块的逻辑实现层。通过注入方式给控制层调用，包括接口及实现方法。
	  sqlmap:为该业务模块的基本的sql操作语句
com.qzgf.core框架核心包
	common:一些共用方法的封装如:统一ajax返回的数据格式(AjaxResult)，Base64,Md5,字符串验证。
	dao:底层dao的实现，共用ibatis处理核心层与数据层的交互
	interceptor:一些拦截器，处理日志，session过期等。
	poi:Excel的导入导出实现，暂未整合
	servlet:1.AJAX的后台处理用反射实现
			2.AuthImg为登录验证码生成

doc：存放文档，如：初始化的sql语句，业务的模型，需求文件，详细设计等信息。

WebRoot
   common:页面层的统一修饰。
   decorator:修饰用的模板。
   lib:LigerUI的核心脚本库信息。
   WEB-INF
   	 format:格式化页面输出
   	 lib:java第三方类库的jar包。
   	 page:业务实现层的页面层显示
   	 






项目结构
1.引入 http://www.ligerui.com作为UI.
2.spring(控制层),struct2(表示层),ibatis(数据层)
3.WebRoot下WEB-INF下有两个文件夹
A.BackModual后台模块的页面层
B.FrontModual前台模块的页面层
4.核心页面脚本在WebRoot的lib下。
5.数据库连接,示例暂为mysql.


//数据调试信息
[{\"icon\":\"lib/icons/silkicons/application.png\",\"id\":42,\"children\":[{\"icon\":\"lib/icons/32X32/basket.gif\",\"id\":43,\"MenuName\":\"类别\",\"MenuID\":43,\"text\":\"类别\",\"MenuUrl\":\"BaseManage/Categories.aspx\",\"MenuIcon\":\"lib/icons/32X32/basket.gif\",\"MenuNo\":\"BaseManageCategories\",\"MenuParentNo\":\"BaseManage\"},{\"icon\":\"lib/icons/32X32/product_169.gif\",\"id\":44,\"MenuName\":\"产品\",\"MenuID\":44,\"text\":\"产品\",\"MenuUrl\":\"BaseManage/Products.aspx\",\"MenuIcon\":\"lib/icons/32X32/product_169.gif\",\"MenuNo\":\"BaseManageProducts\",\"MenuParentNo\":\"BaseManage\"},{\"icon\":\"lib/icons/32X32/suppliers.gif\",\"id\":45,\"MenuName\":\"供应商\",\"MenuID\":45,\"text\":\"供应商\",\"MenuUrl\":\"BaseManage/Suppliers.aspx\",\"MenuIcon\":\"lib/icons/32X32/suppliers.gif\",\"MenuNo\":\"BaseManageSuppliers\",\"MenuParentNo\":\"BaseManage\"},{\"icon\":\"lib/icons/32X32/my_account.gif\",\"id\":46,\"MenuName\":\"员工\",\"MenuID\":46,\"text\":\"员工\",\"MenuUrl\":\"BaseManage/Employees.aspx\",\"MenuIcon\":\"lib/icons/32X32/my_account.gif\",\"MenuNo\":\"BaseManageEmployees\",\"MenuParentNo\":\"BaseManage\"}],\"MenuName\":\"基础信息管理\",\"MenuID\":42,\"text\":\"基础信息管理\",\"MenuUrl\":null,\"MenuIcon\":\"lib/icons/silkicons/application.png\",\"MenuNo\":\"BaseManage\",\"MenuParentNo\":null},{\"icon\":\"lib/icons/silkicons/application.png\",\"id\":52,\"children\":[{\"icon\":\"lib/icons/32X32/comment.gif\",\"id\":53,\"MenuName\":\"客户\",\"MenuID\":53,\"text\":\"客户\",\"MenuUrl\":\"CustomerManage/Customers.aspx\",\"MenuIcon\":\"lib/icons/32X32/comment.gif\",\"MenuNo\":\"CustomerManageCustomers\",\"MenuParentNo\":\"CustomerManage\"}],\"MenuName\":\"客户管理\",\"MenuID\":52,\"text\":\"客户管理\",\"MenuUrl\":null,\"MenuIcon\":\"lib/icons/silkicons/application.png\",\"MenuNo\":\"CustomerManage\",\"MenuParentNo\":null},{\"icon\":\"lib/icons/32X32/order_159.gif\",\"id\":54,\"children\":[{\"icon\":\"lib/icons/32X32/product_169.gif\",\"id\":56,\"MenuName\":\"订单\",\"MenuID\":56,\"text\":\"订单\",\"MenuUrl\":\"OrderManage/Orders.aspx\",\"MenuIcon\":\"lib/icons/32X32/product_169.gif\",\"MenuNo\":\"OrderManageOrders\",\"MenuParentNo\":\"OrderManage\"},{\"icon\":\"lib/icons/32X32/order_159.gif\",\"id\":57,\"MenuName\":\"托运人\",\"MenuID\":57,\"text\":\"托运人\",\"MenuUrl\":\"OrderManage/Shippers.aspx\",\"MenuIcon\":\"lib/icons/32X32/order_159.gif\",\"MenuNo\":\"OrderManageShippers\",\"MenuParentNo\":\"OrderManage\"}],\"MenuName\":\"订单管理\",\"MenuID\":54,\"text\":\"订单管理\",\"MenuUrl\":null,\"MenuIcon\":\"lib/icons/32X32/order_159.gif\",\"MenuNo\":\"OrderManage\",\"MenuParentNo\":null},{\"icon\":\"lib/icons/silkicons/application.png\",\"id\":58,\"children\":[{\"icon\":\"lib/icons/32X32/customers.gif\",\"id\":59,\"MenuName\":\"部门\",\"MenuID\":59,\"text\":\"部门\",\"MenuUrl\":\"MemberManage/Department.aspx\",\"MenuIcon\":\"lib/icons/32X32/customers.gif\",\"MenuNo\":\"MemberManageDepartment\",\"MenuParentNo\":\"MemberManage\"},{\"icon\":\"lib/icons/32X32/my_account.gif\",\"id\":61,\"MenuName\":\"角色\",\"MenuID\":61,\"text\":\"角色\",\"MenuUrl\":\"MemberManage/Role.aspx\",\"MenuIcon\":\"lib/icons/32X32/my_account.gif\",\"MenuNo\":\"MemberManageRole\",\"MenuParentNo\":\"MemberManage\"},{\"icon\":\"lib/icons/32X32/role.gif\",\"id\":62,\"MenuName\":\"用户\",\"MenuID\":62,\"text\":\"用户\",\"MenuUrl\":\"MemberManage/User.aspx\",\"MenuIcon\":\"lib/icons/32X32/role.gif\",\"MenuNo\":\"MemberManageUser\",\"MenuParentNo\":\"MemberManage\"}],\"MenuName\":\"组织架构\",\"MenuID\":58,\"text\":\"组织架构\",\"MenuUrl\":null,\"MenuIcon\":\"lib/icons/silkicons/application.png\",\"MenuNo\":\"MemberManage\",\"MenuParentNo\":null},{\"icon\":\"lib/icons/32X32/future_projects.gif\",\"id\":1,\"children\":[{\"icon\":\"lib/icons/32X32/sitemap.gif\",\"id\":8,\"MenuName\":\"菜单管理\",\"MenuID\":8,\"text\":\"菜单管理\",\"MenuUrl\":\"system/menu.aspx\",\"MenuIcon\":\"lib/icons/32X32/sitemap.gif\",\"MenuNo\":\"sysmenu\",\"MenuParentNo\":\"system\"},{\"icon\":\"lib/icons/32X32/link.gif\",\"id\":19,\"MenuName\":\"权限中心\",\"MenuID\":19,\"text\":\"权限中心\",\"MenuUrl\":\"system/right.aspx\",\"MenuIcon\":\"lib/icons/32X32/link.gif\",\"MenuNo\":\"sysright\",\"MenuParentNo\":\"system\"},{\"icon\":\"lib/icons/32X32/setup.gif\",\"id\":64,\"MenuName\":\"数据权限\",\"MenuID\":64,\"text\":\"数据权限\",\"MenuUrl\":\"system/DataPrivilege.aspx\",\"MenuIcon\":\"lib/icons/32X32/setup.gif\",\"MenuNo\":\"sysDataPrivilege\",\"MenuParentNo\":\"system\"},{\"icon\":\"lib/icons/32X32/config.gif\",\"id\":65,\"MenuName\":\"底层权限\",\"MenuID\":65,\"text\":\"底层权限\",\"MenuUrl\":\"system/AdminMethod.aspx\",\"MenuIcon\":\"lib/icons/32X32/config.gif\",\"MenuNo\":\"sysAdminMethodPrivilege\",\"MenuParentNo\":\"system\"},{\"icon\":\"lib/icons/32X32/consulting.gif\",\"id\":66,\"MenuName\":\"页面配置(字段权限)\",\"MenuID\":66,\"text\":\"页面配置(字段权限)\",\"MenuUrl\":\"system/Configuration.aspx\",\"MenuIcon\":\"lib/icons/32X32/consulting.gif\",\"MenuNo\":\"sysConfiguration\",\"MenuParentNo\":\"system\"}],\"MenuName\":\"系统管理\",\"MenuID\":1,\"text\":\"系统管理\",\"MenuUrl\":null,\"MenuIcon\":\"lib/icons/32X32/future_projects.gif\",\"MenuNo\":\"system\",\"MenuParentNo\":null}]



[{\"icon\":\"lib/icons/silkicons/application.png\",\"id\":42,
    \"children\":[
                 { \"icon\":\"lib/icons/32X32/basket.gif\",\"id\":43,\"MenuName\":\"类别\",\"MenuID\":43,\"text\":\"类别\",\"MenuUrl\":\"BaseManage/Categories.aspx\",\"MenuIcon\":\"lib/icons/32X32/basket.gif\",\"MenuNo\":\"BaseManageCategories\",\"MenuParentNo\":\"BaseManage\"},
                 {\"icon\":\"lib/icons/32X32/product_169.gif\",\"id\":44,\"MenuName\":\"产品\",\"MenuID\":44,\"text\":\"产品\",\"MenuUrl\":\"BaseManage/Products.aspx\",\"MenuIcon\":\"lib/icons/32X32/product_169.gif\",\"MenuNo\":\"BaseManageProducts\",\"MenuParentNo\":\"BaseManage\"},
                 {\"icon\":\"lib/icons/32X32/suppliers.gif\",\"id\":45,\"MenuName\":\"供应商\",\"MenuID\":45,\"text\":\"供应商\",\"MenuUrl\":\"BaseManage/Suppliers.aspx\",\"MenuIcon\":\"lib/icons/32X32/suppliers.gif\",\"MenuNo\":\"BaseManageSuppliers\",\"MenuParentNo\":\"BaseManage\"},
                 {\"icon\":\"lib/icons/32X32/my_account.gif\",\"id\":46,\"MenuName\":\"员工\",\"MenuID\":46,\"text\":\"员工\",\"MenuUrl\":\"BaseManage/Employees.aspx\",\"MenuIcon\":\"lib/icons/32X32/my_account.gif\",\"MenuNo\":\"BaseManageEmployees\",\"MenuParentNo\":\"BaseManage\"}
                 ],
               \"MenuName\":\"基础信息管理\",\"MenuID\":42,\"text\":\"基础信息管理\",\"MenuUrl\":null,\"MenuIcon\":\"lib/icons/silkicons/application.png\",\"MenuNo\":\"BaseManage\",\"MenuParentNo\":null}
              ,
{\"icon\":\"lib/icons/silkicons/application.png\",\"id\":52,
         \"children\":[
         {\"icon\":\"lib/icons/32X32/comment.gif\",\"id\":53,\"MenuName\":\"客户\",\"MenuID\":53,\"text\":\"客户\",\"MenuUrl\":\"CustomerManage/Customers.aspx\",\"MenuIcon\":\"lib/icons/32X32/comment.gif\",\"MenuNo\":\"CustomerManageCustomers\",\"MenuParentNo\":\"CustomerManage\"}]
         ,\"MenuName\":\"客户管理\",\"MenuID\":52,\"text\":\"客户管理\",\"MenuUrl\":null,\"MenuIcon\":\"lib/icons/silkicons/application.png\",\"MenuNo\":\"CustomerManage\",\"MenuParentNo\":null},
{\"icon\":\"lib/icons/32X32/order_159.gif\",\"id\":54,
           \"children\":[{\"icon\":\"lib/icons/32X32/product_169.gif\",\"id\":56,\"MenuName\":\"订单\",\"MenuID\":56,\"text\":\"订单\",\"MenuUrl\":\"OrderManage/Orders.aspx\",\"MenuIcon\":\"lib/icons/32X32/product_169.gif\",\"MenuNo\":\"OrderManageOrders\",\"MenuParentNo\":\"OrderManage\"},
           					{\"icon\":\"lib/icons/32X32/order_159.gif\",\"id\":57,\"MenuName\":\"托运人\",\"MenuID\":57,\"text\":\"托运人\",\"MenuUrl\":\"OrderManage/Shippers.aspx\",\"MenuIcon\":\"lib/icons/32X32/order_159.gif\",\"MenuNo\":\"OrderManageShippers\",\"MenuParentNo\":\"OrderManage\"}],
         \"MenuName\":\"订单管理\",\"MenuID\":54,\"text\":\"订单管理\",\"MenuUrl\":null,\"MenuIcon\":\"lib/icons/32X32/order_159.gif\",\"MenuNo\":\"OrderManage\",\"MenuParentNo\":null},
{\"icon\":\"lib/icons/silkicons/application.png\",\"id\":58,
              \"children\":[{\"icon\":\"lib/icons/32X32/customers.gif\",\"id\":59,\"MenuName\":\"部门\",\"MenuID\":59,\"text\":\"部门\",\"MenuUrl\":\"MemberManage/Department.aspx\",\"MenuIcon\":\"lib/icons/32X32/customers.gif\",\"MenuNo\":\"MemberManageDepartment\",\"MenuParentNo\":\"MemberManage\"},
                                {\"icon\":\"lib/icons/32X32/my_account.gif\",\"id\":61,\"MenuName\":\"角色\",\"MenuID\":61,\"text\":\"角色\",\"MenuUrl\":\"MemberManage/Role.aspx\",\"MenuIcon\":\"lib/icons/32X32/my_account.gif\",\"MenuNo\":\"MemberManageRole\",\"MenuParentNo\":\"MemberManage\"},
                                {\"icon\":\"lib/icons/32X32/role.gif\",\"id\":62,\"MenuName\":\"用户\",\"MenuID\":62,\"text\":\"用户\",\"MenuUrl\":\"MemberManage/User.aspx\",\"MenuIcon\":\"lib/icons/32X32/role.gif\",\"MenuNo\":\"MemberManageUser\",\"MenuParentNo\":\"MemberManage\"}],
                \"MenuName\":\"组织架构\",\"MenuID\":58,\"text\":\"组织架构\",\"MenuUrl\":null,\"MenuIcon\":\"lib/icons/silkicons/application.png\",\"MenuNo\":\"MemberManage\",\"MenuParentNo\":null},
{\"icon\":\"lib/icons/32X32/future_projects.gif\",\"id\":1,
                \"children\":[{\"icon\":\"lib/icons/32X32/sitemap.gif\",\"id\":8,\"MenuName\":\"菜单管理\",\"MenuID\":8,\"text\":\"菜单管理\",\"MenuUrl\":\"system/menu.aspx\",\"MenuIcon\":\"lib/icons/32X32/sitemap.gif\",\"MenuNo\":\"sysmenu\",\"MenuParentNo\":\"system\"},
                                    {\"icon\":\"lib/icons/32X32/link.gif\",\"id\":19,\"MenuName\":\"权限中心\",\"MenuID\":19,\"text\":\"权限中心\",\"MenuUrl\":\"system/right.aspx\",\"MenuIcon\":\"lib/icons/32X32/link.gif\",\"MenuNo\":\"sysright\",\"MenuParentNo\":\"system\"},
                                    {\"icon\":\"lib/icons/32X32/setup.gif\",\"id\":64,\"MenuName\":\"数据权限\",\"MenuID\":64,\"text\":\"数据权限\",\"MenuUrl\":\"system/DataPrivilege.aspx\",\"MenuIcon\":\"lib/icons/32X32/setup.gif\",\"MenuNo\":\"sysDataPrivilege\",\"MenuParentNo\":\"system\"},
                                    {\"icon\":\"lib/icons/32X32/config.gif\",\"id\":65,\"MenuName\":\"底层权限\",\"MenuID\":65,\"text\":\"底层权限\",\"MenuUrl\":\"system/AdminMethod.aspx\",\"MenuIcon\":\"lib/icons/32X32/config.gif\",\"MenuNo\":\"sysAdminMethodPrivilege\",\"MenuParentNo\":\"system\"},
                                    {\"icon\":\"lib/icons/32X32/consulting.gif\",\"id\":66,\"MenuName\":\"页面配置(字段权限)\",\"MenuID\":66,\"text\":\"页面配置(字段权限)\",\"MenuUrl\":\"system/Configuration.aspx\",\"MenuIcon\":\"lib/icons/32X32/consulting.gif\",\"MenuNo\":\"sysConfiguration\",\"MenuParentNo\":\"system\"}],
               \"MenuName\":\"系统管理\",\"MenuID\":1,\"text\":\"系统管理\",\"MenuUrl\":null,\"MenuIcon\":\"lib/icons/32X32/future_projects.gif\",\"MenuNo\":\"system\",\"MenuParentNo\":null}]


{"Rows":[{"UserID":1,"LoginName":"daomi","LoginPassword":"1","DeptID":null,"SupplierID":null,"EmployeeID":null,"RealName":"稻米","Title":"稻米","Sex":"男","Phone":null,"Fax":null,"Email":"gd_star@163.com","QQ":"175932810","NickName":"稻米","Address":null,"LastLoginTime":"\/Date(1337446502000)\/","CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null},{"UserID":2,"LoginName":"test3","LoginPassword":"1","DeptID":5,"SupplierID":null,"EmployeeID":5,"RealName":"录入员1","Title":"基础数据录入员1","Sex":"男","Phone":null,"Fax":null,"Email":null,"QQ":null,"NickName":null,"Address":null,"LastLoginTime":"\/Date(1331978644000)\/","CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null},{"UserID":3,"LoginName":"test2","LoginPassword":"1","DeptID":1,"SupplierID":null,"EmployeeID":null,"RealName":"录入员2","Title":"客户数据录入员2","Sex":"男","Phone":"44","Fax":"44","Email":null,"QQ":null,"NickName":null,"Address":null,"LastLoginTime":"\/Date(1337445804000)\/","CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null},{"UserID":4,"LoginName":"test1","LoginPassword":"1","DeptID":null,"SupplierID":null,"EmployeeID":null,"RealName":"高级演示账号1","Title":"高级演示账号1","Sex":null,"Phone":null,"Fax":null,"Email":null,"QQ":null,"NickName":null,"Address":null,"LastLoginTime":"\/Date(1337444610000)\/","CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null},{"UserID":5,"LoginName":"test4","LoginPassword":"1","DeptID":3,"SupplierID":null,"EmployeeID":1,"RealName":"订单查看员1","Title":"订单查看员1 - Sales Representative","Sex":null,"Phone":null,"Fax":null,"Email":null,"QQ":null,"NickName":null,"Address":null,"LastLoginTime":"\/Date(1332009024000)\/","CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null},{"UserID":6,"LoginName":"test5","LoginPassword":"1","DeptID":null,"SupplierID":1,"EmployeeID":null,"RealName":"供应商1","Title":"供应商1 - Exotic Liquids44","Sex":null,"Phone":null,"Fax":null,"Email":null,"QQ":null,"NickName":null,"Address":null,"LastLoginTime":"\/Date(1332009007000)\/","CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null},{"UserID":7,"LoginName":"kkkk","LoginPassword":"kkkk","DeptID":1,"SupplierID":null,"EmployeeID":null,"RealName":"kk","Title":"kk","Sex":null,"Phone":null,"Fax":null,"Email":null,"QQ":null,"NickName":null,"Address":null,"LastLoginTime":"\/Date(1337446293000)\/","CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null}],"Total":"7"}


{"Rows": [
	{"UserID":1,"LoginName":"daomi","LoginPassword":"1","DeptID":null,"SupplierID":null,"EmployeeID":null,"RealName":"稻米","Title":"稻米","Sex":"男","Phone":null,"Fax":null,"Email":"gd_star@163.com","QQ":"175932810","NickName":"稻米","Address":null,"LastLoginTime":"\/Date(1337446502000)\/","CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null},
	{"UserID":2,"LoginName":"test3","LoginPassword":"1","DeptID":5,"SupplierID":null,"EmployeeID":5,"RealName":"录入员1","Title":"基础数据录入员1","Sex":"男","Phone":null,"Fax":null,"Email":null,"QQ":null,"NickName":null,"Address":null,"LastLoginTime":"\/Date(1331978644000)\/","CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null},
	{"UserID":3,"LoginName":"test2","LoginPassword":"1","DeptID":1,"SupplierID":null,"EmployeeID":null,"RealName":"录入员2","Title":"客户数据录入员2","Sex":"男","Phone":"44","Fax":"44","Email":null,"QQ":null,"NickName":null,"Address":null,"LastLoginTime":"\/Date(1337445804000)\/","CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null},
	{"UserID":4,"LoginName":"test1","LoginPassword":"1","DeptID":null,"SupplierID":null,"EmployeeID":null,"RealName":"高级演示账号1","Title":"高级演示账号1","Sex":null,"Phone":null,"Fax":null,"Email":null,"QQ":null,"NickName":null,"Address":null,"LastLoginTime":"\/Date(1337444610000)\/","CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null},
	{"UserID":5,"LoginName":"test4","LoginPassword":"1","DeptID":3,"SupplierID":null,"EmployeeID":1,"RealName":"订单查看员1","Title":"订单查看员1 - Sales Representative","Sex":null,"Phone":null,"Fax":null,"Email":null,"QQ":null,"NickName":null,"Address":null,"LastLoginTime":"\/Date(1332009024000)\/","CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null},
	{"UserID":6,"LoginName":"test5","LoginPassword":"1","DeptID":null,"SupplierID":1,"EmployeeID":null,"RealName":"供应商1","Title":"供应商1 - Exotic Liquids44","Sex":null,"Phone":null,"Fax":null,"Email":null,"QQ":null,"NickName":null,"Address":null,"LastLoginTime":"\/Date(1332009007000)\/","CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null},
	{"UserID":7,"LoginName":"kkkk","LoginPassword":"kkkk","DeptID":1,"SupplierID":null,"EmployeeID":null,"RealName":"kk","Title":"kk","Sex":null,"Phone":null,"Fax":null,"Email":null,"QQ":null,"NickName":null,"Address":null,"LastLoginTime":"\/Date(1337446293000)\/","CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null}
	],"Total":"7"}


{"IsError":false,"Message":null,"Data":[
	{"BtnID":153,"BtnName":"新增","BtnNo":"add","BtnClass":null,"BtnIcon":"lib/icons/silkicons/add.png","BtnScript":null,"MenuNo":"MemberManageUser","InitStatus":null,"SeqNo":null},
	{"BtnID":154,"BtnName":"修改","BtnNo":"modify","BtnClass":null,"BtnIcon":"lib/icons/silkicons/application_edit.png","BtnScript":null,"MenuNo":"MemberManageUser","InitStatus":null,"SeqNo":null},
	{"BtnID":155,"BtnName":"删除","BtnNo":"delete","BtnClass":null,"BtnIcon":"lib/icons/silkicons/delete.png","BtnScript":null,"MenuNo":"MemberManageUser","InitStatus":null,"SeqNo":null},
	{"BtnID":156,"BtnName":"查看","BtnNo":"view","BtnClass":null,"BtnIcon":"lib/icons/silkicons/application_view_detail.png","BtnScript":null,"MenuNo":"MemberManageUser","InitStatus":null,"SeqNo":null}]}

{"message":"","data":[{"id":"2012001","icon":"lib/icons/silkicons/add.png","name":"新增","orderno":"1","ismenu":"3","url":"add","father":"0","permissionsflag":" "},{"id":"2012002","icon":"lib/icons/silkicons/application_edit.png","name":"修改","orderno":"1","ismenu":"3","url":"modify","father":"0","permissionsflag":" "},{"id":"2012003","icon":"lib/icons/silkicons/delete.png","name":"删除","orderno":"1","ismenu":"3","url":"delete","father":"0","permissionsflag":" "},{"id":"2012004","icon":"lib/icons/silkicons/application_view_detail.png","name":"查看","orderno":"1","ismenu":"3","url":"view","father":"0","permissionsflag":" "}],"isError":false}



{"Rows":[{"id":"1","username":"1","departmentid":"1","nickname":"1","email":"1","remark":"1","tel":"1","createdate":{"nanos":0,"time":1337676363000,"minutes":46,"seconds":3,"hours":16,"month":4,"year":112,"timezoneOffset":-480,"day":2,"date":22},"state":"1","areaid":"1","password":"c4ca4238a0b923820dcc509a6f75849b","createman":"1"}],"Total":1}

D:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\ROOT\WEB-INF\classes
D:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\ROOT\WEB-INF\logs\javaframe.log


{"IsError":false,"Message":"加载成功","Data":{"UserID":0,"LoginName":null,"LoginPassword":null,"DeptID":null,"SupplierID":null,"EmployeeID":null,"RealName":null,"Title":null,"Sex":null,"Phone":null,"Fax":null,"Email":null,"QQ":null,"NickName":null,"Address":null,"LastLoginTime":null,"CreateUserID":null,"CreateDate":null,"ModifyUserID":null,"ModifyDate":null,"RecordStatus":null}}
{"message":"","error":false,"data":{"id":"1","username":"1","departmentid":"1","nickname":"1","email":"1","remark":"1","tel":"1","createdate":{"nanos":0,"time":1337676363000,"minutes":46,"seconds":3,"hours":16,"month":4,"year":112,"timezoneOffset":-480,"day":2,"date":22},"state":"1","areaid":"1","password":"c4ca4238a0b923820dcc509a6f75849b","createman":"1"}} 

[{"icon":"lib/icons/32X32/basket.gif", "id":"201201","MenuName":"系统管理","MenuID":"201201","text":"系统管理","MenuUrl":"1","MenuIcon":"lib/icons/32X32/basket.gif","MenuNo":"1","MenuParentNo":"0","children":[{"icon":"lib/icons/32X32/my_account.gif", "id":"201202","MenuName":"角色管理","MenuID":"201202","text":"角色管理","MenuUrl":"appsystem/role.do","MenuIcon":"lib/icons/32X32/my_account.gif","MenuNo":"1","MenuParentNo":"201201"},{"icon":"lib/icons/32X32/role.gif", "id":"201203","MenuName":"用户管理","MenuID":"201203","text":"用户管理","MenuUrl":"appsystem/user.do","MenuIcon":"lib/icons/32X32/role.gif","MenuNo":"1","MenuParentNo":"201201"},{"icon":"lib/icons/32X32/customers.gif", "id":"201204","MenuName":"机构管理","MenuID":"201204","text":"机构管理","MenuUrl":"appsystem/department.do","MenuIcon":"lib/icons/32X32/customers.gif","MenuNo":"1","MenuParentNo":"201201"},{"icon":"lib/icons/32X32/sitemap.gif", "id":"201205","MenuName":"菜单管理","MenuID":"201205","text":"菜单管理","MenuUrl":"appsystem/menu.do","MenuIcon":"lib/icons/32X32/sitemap.gif","MenuNo":"1","MenuParentNo":"201201"},{"icon":"lib/icons/32X32/basket.gif", "id":"201206","MenuName":"日志管理","MenuID":"201206","text":"日志管理","MenuUrl":"appsystem/log.do","MenuIcon":"lib/icons/32X32/basket.gif","MenuNo":"1","MenuParentNo":"201201"}]}]

Error writing request body to server

//====================================================
//查询
{"op":"and","rules":[{"op":"equal","field":"search.pusername","value":"123132","type":"string"}]}

{"groups":[{"op":"and"}],
"rules":[{"field":"username","op":"equal","value":"123","type":"string"},
		{"field":"username","op":"notequal","value":"1234","type":"string"},
		{"field":"nickname","op":"equal","value":"5656","type":"string"}
],"op":"and"}


{"groups":[{"op":"and"}],
"rules":[{"field":"username","op":"equal","value":"123","type":"string"},
		{"field":"username","op":"notequal","value":"1234","type":"string"},
		{"field":"nickname","op":"equal","value":"5656","type":"string"}
],"op":"and"}

WHERE=( search.pusername  =  'ggg')

../handler/ajax.ashx?type=AjaxSystem&method=GetMyFavorite

{"IsError":false,"Message":null,"Data":[{"FavoriteID":12,"FavoriteTitle":"供应商","FavoriteAddTime":"\/Date(1331797822000)\/","FavoriteContent":"444","UserID":1,"Url":"BaseManage/Suppliers.aspx","Icon":"lib/icons/32X32/suppliers.gif"},{"FavoriteID":13,"FavoriteTitle":"数据权限","FavoriteAddTime":"\/Date(1331797833000)\/","FavoriteContent":"444","UserID":1,"Url":"system/DataPrivilege.aspx","Icon":"lib/icons/32X32/setup.gif"},{"FavoriteID":14,"FavoriteTitle":"订单","FavoriteAddTime":"\/Date(1331797851000)\/","FavoriteContent":"444","UserID":1,"Url":"OrderManage/Orders.aspx","Icon":"lib/icons/32X32/product_169.gif"}]}
