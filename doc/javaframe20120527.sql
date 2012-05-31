/*
Navicat MySQL Data Transfer

Source Server         : self
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : javaframe

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2012-05-27 22:18:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tab_sequences`
-- ----------------------------
DROP TABLE IF EXISTS `tab_sequences`;
CREATE TABLE `tab_sequences` (
  `seq_name` varchar(200) NOT NULL default '',
  `value` int(11) default '0',
  PRIMARY KEY  (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab_sequences
-- ----------------------------
INSERT INTO `tab_sequences` VALUES ('tab_system_department', '0');
INSERT INTO `tab_sequences` VALUES ('tab_system_log', '4');
INSERT INTO `tab_sequences` VALUES ('tab_system_menu', '0');
INSERT INTO `tab_sequences` VALUES ('tab_system_menufield', '0');
INSERT INTO `tab_sequences` VALUES ('tab_system_role', '0');
INSERT INTO `tab_sequences` VALUES ('tab_system_rolefiledpower', '0');
INSERT INTO `tab_sequences` VALUES ('tab_system_rolemenu', '0');
INSERT INTO `tab_sequences` VALUES ('tab_system_user', '9');
INSERT INTO `tab_sequences` VALUES ('tab_system_userrole', '0');

-- ----------------------------
-- Table structure for `tab_system_department`
-- ----------------------------
DROP TABLE IF EXISTS `tab_system_department`;
CREATE TABLE `tab_system_department` (
  `id` char(20) NOT NULL COMMENT '主键',
  `departname` varchar(256) default NULL COMMENT '部门名称',
  `charger` varchar(50) default NULL COMMENT '部门负责人',
  `tel` varchar(20) default NULL COMMENT '负责人电话',
  `address` varchar(256) default NULL COMMENT '负责人地址',
  `father` char(20) default NULL COMMENT '上级结点',
  `type` char(2) default NULL COMMENT '0~3备用,4 区县，5公司，6驻点，7网格',
  `remark` varchar(256) default NULL COMMENT '备???',
  `state` char(2) default NULL COMMENT '0:删除,1:正常,2停用',
  `createman` char(20) default NULL,
  `createdate` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '创建时间',
  `orderno` char(10) default NULL COMMENT '排序',
  `isrepair` char(2) default NULL COMMENT '作用在于权限控制时，移动的按区域，代维的按机构',
  `level` char(2) default NULL COMMENT '暂定内部处理',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='机构信息';

-- ----------------------------
-- Records of tab_system_department
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_system_log`
-- ----------------------------
DROP TABLE IF EXISTS `tab_system_log`;
CREATE TABLE `tab_system_log` (
  `id` char(20) NOT NULL COMMENT '主键',
  `opercode` varchar(100) default NULL COMMENT '操作名称',
  `controllerscode` varchar(100) default NULL COMMENT '控制器名称',
  `userid` char(20) default NULL COMMENT '用户id',
  `operdate` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '操作时间',
  `operresult` varchar(256) default NULL COMMENT '操作结果',
  `operip` varchar(50) default NULL COMMENT '操作地址ip',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='日志信息';

-- ----------------------------
-- Records of tab_system_log
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_system_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tab_system_menu`;
CREATE TABLE `tab_system_menu` (
  `id` char(20) NOT NULL COMMENT '主键',
  `name` varchar(256) default NULL COMMENT '菜单名称',
  `icon` varchar(256) default NULL COMMENT '菜单图片',
  `url` varchar(256) default NULL COMMENT '菜单地址',
  `orderno` char(10) default NULL COMMENT '排序',
  `father` char(20) default NULL COMMENT '父结点',
  `ismenu` char(1) default NULL COMMENT '菜单仅显示,叶子可做为功能结点(如:添加,册除,金额)',
  `permissionsflag` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主要实现菜单包括action即增加,删除,修改';

-- ----------------------------
-- Records of tab_system_menu
-- ----------------------------
INSERT INTO `tab_system_menu` VALUES ('0', '菜单', 'lib/icons/32X32/basket.gif', '', '1', '-1', '0', '');
INSERT INTO `tab_system_menu` VALUES ('2000', '业务管理', 'lib/icons/32X32/basket.gif', '', '2000', '0', '0', '');
INSERT INTO `tab_system_menu` VALUES ('2001', '日常工作', 'lib/icons/32X32/basket.gif', 'work/tdailywork.do', '2001', '2000', '0', '');
INSERT INTO `tab_system_menu` VALUES ('2002', '督办工作', 'lib/icons/32X32/basket.gif', 'work/ttomonitor.do', '2002', '2000', '0', '');
INSERT INTO `tab_system_menu` VALUES ('2003', '事项申报', 'lib/icons/32X32/basket.gif', 'work/tdeclare.do', '2003', '2000', '0', '');
INSERT INTO `tab_system_menu` VALUES ('2004', '位置管理', 'lib/icons/32X32/basket.gif', 'report/repattendance.do', '2004', '2000', '0', '');
INSERT INTO `tab_system_menu` VALUES ('2011011300000011', '报表管理', 'lib/icons/32X32/basket.gif', 'report/treportpattern.do', '0', '2000', '0', '');
INSERT INTO `tab_system_menu` VALUES ('2011012700000012', '数据字典', 'lib/icons/32X32/basket.gif', 'dictionary.do', '1', '2000', '0', '');
INSERT INTO `tab_system_menu` VALUES ('2012001', '新增', 'lib/icons/silkicons/add.png', 'add', '1', '0', '3', ' ');
INSERT INTO `tab_system_menu` VALUES ('2012002', '修改', 'lib/icons/silkicons/application_edit.png', 'modify', '1', '0', '3', ' ');
INSERT INTO `tab_system_menu` VALUES ('2012003', '删除', 'lib/icons/silkicons/delete.png', 'delete', '1', '0', '3', ' ');
INSERT INTO `tab_system_menu` VALUES ('2012004', '查看', 'lib/icons/silkicons/application_view_detail.png', 'view', '1', '0', '3', ' ');
INSERT INTO `tab_system_menu` VALUES ('201201', '系统管理', 'lib/icons/32X32/basket.gif', '1', '1', '0', '1', ' ');
INSERT INTO `tab_system_menu` VALUES ('201202', '角色管理', 'lib/icons/32X32/my_account.gif', 'appsystem/role.do', '1', '201201', '1', ' ');
INSERT INTO `tab_system_menu` VALUES ('201203', '用户管理', 'lib/icons/32X32/role.gif', 'appsystem/user.do', '1', '201201', '1', ' ');
INSERT INTO `tab_system_menu` VALUES ('201204', '机构管理', 'lib/icons/32X32/customers.gif', 'appsystem/department.do', '1', '201201', '1', ' ');
INSERT INTO `tab_system_menu` VALUES ('201205', '菜单管理', 'lib/icons/32X32/sitemap.gif', 'appsystem/menu.do', '1', '201201', '1', ' ');
INSERT INTO `tab_system_menu` VALUES ('201206', '日志管理', 'lib/icons/32X32/basket.gif', 'appsystem/log.do', '1', '201201', '1', ' ');
INSERT INTO `tab_system_menu` VALUES ('5000', '自定义管理', 'lib/icons/32X32/basket.gif', '', '5000', '0', '0', '');
INSERT INTO `tab_system_menu` VALUES ('5001', '自定义模板', 'lib/icons/32X32/basket.gif', '/custom.do?action=list&actionType=toQueryPage', '5001', '5000', '0', '');
INSERT INTO `tab_system_menu` VALUES ('5005', '报表查询', 'lib/icons/32X32/basket.gif', 'customReport.do', '5005', '5000', '0', '');
INSERT INTO `tab_system_menu` VALUES ('9000', '系统管理', 'lib/icons/32X32/basket.gif', '', '9000', '0', '0', '');
INSERT INTO `tab_system_menu` VALUES ('9006', '组织机构', 'lib/icons/32X32/basket.gif', 'branch.do', '9006', '9000', '0', '');
INSERT INTO `tab_system_menu` VALUES ('9007', '用户管理', 'lib/icons/32X32/basket.gif', 'appsystem/user.do', '9007', '9000', '0', '');
INSERT INTO `tab_system_menu` VALUES ('9008', '用户组', 'lib/icons/32X32/basket.gif', 'group.do', '9008', '9000', '0', '');
INSERT INTO `tab_system_menu` VALUES ('9009', '角色权限', 'lib/icons/32X32/basket.gif', 'role.do', '9009', '9000', '0', '');
INSERT INTO `tab_system_menu` VALUES ('9099', '系统菜单', 'lib/icons/32X32/basket.gif', 'permission.do', '9099', '9000', '0', '');
INSERT INTO `tab_system_menu` VALUES ('9100', '公告管理', 'lib/icons/32X32/basket.gif', 'notice.do', '9100', '9000', '0', '');

-- ----------------------------
-- Table structure for `tab_system_menufield`
-- ----------------------------
DROP TABLE IF EXISTS `tab_system_menufield`;
CREATE TABLE `tab_system_menufield` (
  `id` char(20) NOT NULL COMMENT '主键',
  `menuid` char(20) default NULL COMMENT 'control主键',
  `fieldcode` char(50) default NULL COMMENT '字段代码',
  `fieldname` char(100) default NULL COMMENT '字段名称',
  `tablecode` char(50) default NULL,
  `tablename` char(100) default NULL,
  `remark` char(100) default NULL,
  `permissionsflag` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='功能控制字段和具体功能模块关连的字段';

-- ----------------------------
-- Records of tab_system_menufield
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_system_role`
-- ----------------------------
DROP TABLE IF EXISTS `tab_system_role`;
CREATE TABLE `tab_system_role` (
  `id` char(20) NOT NULL COMMENT '主键',
  `rolename` varchar(256) default NULL COMMENT '角色名称',
  `createman` char(20) default NULL,
  `remark` varchar(256) default NULL COMMENT '备注',
  `createtime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '创建时间',
  `state` char(2) default NULL COMMENT '0:删除,1正常,2停用',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tab_system_role
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_system_rolefiledpower`
-- ----------------------------
DROP TABLE IF EXISTS `tab_system_rolefiledpower`;
CREATE TABLE `tab_system_rolefiledpower` (
  `id` char(20) NOT NULL COMMENT '主键',
  `roleid` char(20) default NULL COMMENT '角色id',
  `fieldid` char(20) default NULL COMMENT '功能字段权限',
  `powerval` int(11) default NULL COMMENT '十进制转化为二进制处理,从右开始第一位查看,打印,导出,例当值为6时,转化为二进制是110,没有查看功能,有打印,导出功能',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='角色权限';

-- ----------------------------
-- Records of tab_system_rolefiledpower
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_system_rolemenu`
-- ----------------------------
DROP TABLE IF EXISTS `tab_system_rolemenu`;
CREATE TABLE `tab_system_rolemenu` (
  `id` char(20) NOT NULL COMMENT '主键',
  `roleid` char(20) default NULL COMMENT '角色id',
  `menuid` char(20) default NULL COMMENT '菜单id',
  `optval` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='角色权限';

-- ----------------------------
-- Records of tab_system_rolemenu
-- ----------------------------

-- ----------------------------
-- Table structure for `tab_system_user`
-- ----------------------------
DROP TABLE IF EXISTS `tab_system_user`;
CREATE TABLE `tab_system_user` (
  `id` char(20) NOT NULL COMMENT '主键',
  `username` varchar(100) default NULL COMMENT '用户名',
  `nickname` varchar(100) default NULL,
  `tel` varchar(50) default NULL COMMENT '电话',
  `email` varchar(100) default NULL COMMENT '邮箱',
  `password` varchar(100) default NULL COMMENT '密码',
  `departmentid` char(20) default NULL COMMENT '部门id',
  `areaid` char(20) default NULL COMMENT '移动业务权限用',
  `remark` varchar(200) default NULL COMMENT '备注',
  `createman` char(20) default NULL COMMENT '创建人编号',
  `createdate` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '创建时间',
  `state` char(2) default NULL COMMENT '0:删除,1:正常,2.停用',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户信息';

-- ----------------------------
-- Records of tab_system_user
-- ----------------------------
INSERT INTO `tab_system_user` VALUES ('1', '1', '1', '1', '1', 'c4ca4238a0b923820dcc509a6f75849b', '1', '1', '1', '1', '2012-05-22 16:46:03', '1');
INSERT INTO `tab_system_user` VALUES ('2012052700000001', 'mmmmm', 'm', null, null, 'm', null, null, null, null, '2012-05-27 17:57:46', '0');
INSERT INTO `tab_system_user` VALUES ('2012052700000002', 'cccccc', 'c', null, null, '4A8A08F09D37B73795649038408B5F33', null, null, null, null, '2012-05-27 22:06:14', '0');
INSERT INTO `tab_system_user` VALUES ('2012052700000003', 'rrrrrrr', 'r1', null, null, 'C4CA4238A0B923820DCC509A6F75849B', null, null, null, null, '2012-05-27 21:52:13', '0');
INSERT INTO `tab_system_user` VALUES ('2012052700000004', 'kkkkk', 'kkk', null, null, 'kkk', null, null, null, null, '2012-05-27 19:10:40', '0');
INSERT INTO `tab_system_user` VALUES ('2012052700000005', 'tttttt', 't', null, null, 't', null, null, null, null, '2012-05-27 19:09:44', '0');
INSERT INTO `tab_system_user` VALUES ('2012052700000006', 'hhhh', 'hh', null, null, 'hh', null, null, null, null, '2012-05-27 19:06:55', '0');
INSERT INTO `tab_system_user` VALUES ('2012052700000007', 'mmmmm', 'm', null, null, 'm', null, null, null, null, '2012-05-27 19:11:49', '0');
INSERT INTO `tab_system_user` VALUES ('2012052700000008', 'ppppppp', 'x3', null, null, '1414C9756505DDFB8BA0A3B6FC9D691F', null, null, null, null, '2012-05-27 21:52:08', '0');
INSERT INTO `tab_system_user` VALUES ('2012052700000009', 'kkkk', '123', null, null, 'FA7F08233358E9B466EFFA1328168527', null, null, null, null, '2012-05-27 21:42:33', '0');

-- ----------------------------
-- Table structure for `tab_system_userrole`
-- ----------------------------
DROP TABLE IF EXISTS `tab_system_userrole`;
CREATE TABLE `tab_system_userrole` (
  `id` char(20) NOT NULL COMMENT '主键',
  `userid` char(20) default NULL COMMENT '用户id',
  `roleid` char(20) default NULL COMMENT '角色id',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='用户角色的关系表';

-- ----------------------------
-- Records of tab_system_userrole
-- ----------------------------

-- ----------------------------
-- Function structure for `seqformat`
-- ----------------------------
DROP FUNCTION IF EXISTS `seqformat`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `seqformat`(in_seq_name char(200) CHARSET utf8) RETURNS char(100) CHARSET utf8
begin
	update tab_sequences set value=value + 1 where seq_name =in_seq_name;
	select ifnull(value,0) into @v_value from tab_sequences where seq_name=in_seq_name;
        select CURDATE()+0  into @v_date ;
        select lpad(concat(@v_value,''),8,'0') into @v_result;
        select concat(@v_date,@v_result) into @v_result;
        return @v_result;
end
;;
DELIMITER ;
