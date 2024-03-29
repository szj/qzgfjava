<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page contentType="text/html; charset=utf8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
	//定义全局变量
	String path = request.getContextPath();
%> 
<head>
	<title>权限管理系统 LigerRightManager</title>
    <script src="<%=path%>/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <link href="<%=path%>/lib/ligerUI/skins/Aqua/css/ligerui-dialog.css" rel="stylesheet" type="text/css" />
    <link href="<%=path%>/lib/ligerUI/skins/Gray/css/dialog.css" rel="stylesheet" type="text/css" />
    <script src="<%=path%>/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="<%=path%>/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="<%=path%>lib/js/common.js" type="text/javascript"></script>
    <script src="<%=path%>lib/js/LG.js" type="text/javascript"></script>
    <style type="text/css">
        *{ padding:0; margin:0;}
        body{ text-align:center; background:#4974A4;}
        #login{ width:740px; margin:0 auto; font-size:12px;}
        #loginlogo{ width:700px; height:100px; overflow:hidden; background:url('<%=path%>/lib/images/login/logo.jpg') no-repeat; margin-top:50px;   }
        #loginpanel{ width:729px; position:relative;height:300px;}
        .panel-h{ width:729px; height:20px; background:url('<%=path%>/lib/images/login/panel-h.gif') no-repeat; position:absolute; top:0px; left:0px; z-index:3;}
        .panel-f{ width:729px; height:13px; background:url('<%=path%>/lib/images/login/panel-f.gif') no-repeat; position:absolute; bottom:0px; left:0px; z-index:3;}
        .panel-c{ z-index:2;background:url('<%=path%>/lib/images/login/panel-c.gif') repeat-y;width:729px; height:300px;}
        .panel-c-l{ position:absolute; left:60px; top:40px;}
        .panel-c-r{ position:absolute; right:20px; top:50px; width:222px; line-height:200%; text-align:left;}
        .panel-c-l h3{ color:#556A85; margin-bottom:10px;}
        .panel-c-l td{ padding:7px;}
        
        
        .login-text{ height:24px; left:24px; border:1px solid #e9e9e9; background:#f9f9f9;}
        .login-text-focus{ border:1px solid #E6BF73;}
        .login-btn{width:114px; height:29px; color:#E9FFFF; line-height:29px; background:url('<%=path%>/lib/images/login/login-btn.gif') no-repeat; border:none; overflow:hidden; cursor:pointer;}
        #txtUsername,#txtPassword{ width:191px;} 
        #logincopyright{ text-align:center; color:White; margin-top:50px;}
    </style>
    <script type="text/javascript">
        var FromUrl = getQueryStringByName("FromUrl");
        if (!FromUrl)
        {
            FromUrl = encodeURIComponent("main.do");
        }
        $(function ()
        {
            $(".login-text").focus(function ()
            {
                $(this).addClass("login-text-focus");
            }).blur(function ()
            {
                $(this).removeClass("login-text-focus");
            });

            $(document).keydown(function (e)
            {
                if (e.keyCode == 13)
                {
                    dologin();
                }
            });

            $("#btnLogin").click(function ()
            {
                dologin();
            });


            function dologin()
            {
                var username = $("#pcode").val();
                var password = $("#ppasswd").val();
                var authcode = $("#pverifycode").val();
                if (username == "")
                {
                    alert('账号不能为空!');
                    $("#pcode").focus();
                    return;
                }
                if (password == "")
                {
                    alert('密码不能为空!');
                    $("#ppasswd").focus();
                    return;
                }
                $.ajax({
                    type: 'post', cache: false, dataType: 'json',
                    url: '<%=path%>/main!Login.do',
                    data: [ 
                    { name: 'search.username', value: username },
                    { name: 'search.authcode', value: authcode },
                    { name: 'search.password', value: password }
                    ],
                    success: function (result)
                    {
                        if (!result)
                        {
                            $.ligerDialog.error('登陆失败,账号或密码有误!');
                            $("#pcode").focus();
                            return;
                        } else
                        {
                            location.href = decodeURIComponent(FromUrl);
                        }
                    },
                    error: function ()
                    {
                        //alert('发送系统错误,请与系统管理员联系!');
                        $.ligerDialog.error('发送系统错误,请与系统管理员联系!');
                    },
                    beforeSend: function ()
                    {
                        $.ligerDialog.waitting("正在登陆中,请稍后...");
                        $("#btnLogin").attr("disabled", true);
                    },
                    complete: function ()
                    {
                        $.ligerDialog.closeWaitting();
                        $("#btnLogin").attr("disabled", false);
                    }
                });
            }
        });
    </script>
</head>
<body style="padding:10px"> 
    <div id="login">
        <div id="loginlogo"></div>
        <div id="loginpanel">
            <div class="panel-h"></div>
            <div class="panel-c">
                <div class="panel-c-l">
                   
                    <table cellpadding="0" cellspacing="0">
                        <tbody>
                         <tr>
                            <td align="left" colspan="2"> 
                             <h3>请使用LigerUI 权限管理系统账号登陆</h3>
                            </td>
                            </tr> 
                            <tr>
                            <td align="right">账号：</td><td align="left"><input type="text" name="search.pcode" value="1" id="pcode" class="login-text" /></td>
                            </tr>
                            <tr>
                            <td align="right">密码：</td><td align="left"><input type="password" name="search.ppasswd" value="1" id="ppasswd" class="login-text" /></td>
                            </tr> 
                            <tr>
                            <td align="right">验证码：</td><td align="left"><input type="text" name="search.pverifycode" id="pverifycode" class="login-text" />
                            <IMG id="imgcode" title="看不清？！换一个" style="CURSOR: pointer;vertical-align:middle"  onclick="document.getElementById('imgcode').src='/authimg?action='+Math.random();"  alt="看不清？！换一个" src="/authimg"/>
                            </td>
                            </tr> 
                            <tr>
                            <td align="center" colspan="2">
                                <input type="submit" id="btnLogin" value="登陆" class="login-btn" />
                            </td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
                <div class="panel-c-r">
                <p>请从左侧输入登录账号和密码登录</p>
                <p>如果遇到系统问题，请联系网络管理员。</p>
                <p>如果没有账号，请联系网站管理员。 </p>
                <p>......</p>
                </div>
            </div>
            <div class="panel-f"></div>
        </div>
         <div id="logincopyright">Copyright © 2012 LigerUI </div>
    </div>
</body>
</html>
