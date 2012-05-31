<%@page contentType="text/html; charset=utf8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<head>
    <link href="lib/css/welcome.css" rel="stylesheet" type="text/css" />
    <script src="lib/jquery.form.js" type="text/javascript"></script>
    <script src="lib/js/addfavorite.js" type="text/javascript"></script> 
</head>
<body style="padding:10px; overflow:auto; text-align:center;background:#FFFFFF;"> 
        <div class="navbar"><div class="navbar-l"></div><div class="navbar-r"></div>
        <div class="navbar-icon"><img src="lib/icons/32X32/hire_me.gif" /></div>
        <div class="navbar-inner"> 
        <b><span id="labelusername"></span><span>，</span><span id="labelwelcome"></span><span>欢迎使用liger权限管理系统</span></b>
        <a href="javascript:void(0)" id="usersetup" style="display:none">账号设置</a>
        </div>
        </div>

        <div class="withicon">
            <div class="icon"><img src="lib/images/index/time.gif" /></div>
            <span id="labelLastLoginTime"></span>
        </div>


        <div class="navline">
        </div>
        
        <div class="links"> 
        </div>

        <div class="l-clear"></div>

        <div class="button" onclick="LG.addfavorite(loadMyFavorite)">
            <div class="button-l"> </div>
            <div class="button-r"> </div>
            <div class="button-icon"> <img src="lib/ligerUI/skins/icons/add.gif" /> </div> 
            <span>增加快捷方式</span>  
        </div>

          


        <div class="navbar"><div class="navbar-l"></div><div class="navbar-r"></div>
        <div class="navbar-icon"><img src="lib/icons/32X32/collaboration.gif" /></div>
        <div class="navbar-inner"> 
        <b>LigerRM 使用说明</b> 
        </div>
        </div>

        <p style="margin:10px;">您可以快速进行产品增加管理操作</p>

         <p style="margin:10px;">也可以快速进行系统权限的管理操作，全部的菜单都在左侧。。。</p>
       


        <div class="navline">
        </div>

         
           <div class="withicon">
            <div class="icon"> 
                <img src="lib/icons/32X32/communication.gif" /></div>
            <span><b>LigerRM是基于ligerui开发的一套权限管理系统，系统中大量使用了grid、tree以及各种表单插件，欢迎体验。在第二版中引入了Northwind实例数据库,更大程度地展示了ligerui的一些功能的使用。</b></span>
        </div>



           <p class="p1"><b>特别说明</b>：此程序版权归作者所有，仅供学习参考，请勿用于任何商业用途！如有问题，请发邮件至gd_star@163.com</p>
           
           <script type="text/javascript">
               $("div.link").live("mouseover", function ()
               {
                   $(this).addClass("linkover");
                    
               }).live("mouseout", function ()
               {
                   $(this).removeClass("linkover");
                    

               }).live('click', function (e)
               { 
                   var text = $("a", this).html();
                   var url = $(this).attr("url");
                   parent.f_addTab(null, text, url);
               });

               $("div.link .close").live("mouseover", function ()
               {
                   $(this).addClass("closeover");
               }).live("mouseout", function ()
               {
                   $(this).removeClass("closeover");
               }).live('click', function ()
               {
                   var id = $(this).parent().attr("id");

                   LG.ajax({
                       loading: '正在删除用户收藏中...',
                       type: 'mainAction',
                       method: 'RemoveMyFavorite',
                       data: { "search.pid": id },
                       success: function ()
                       {
                           loadMyFavorite();
                       },
                       error: function (message)
                       {
                           LG.showError(message);
                       }
                   });

                   return false;
               });


               var links = $(".links");

               

               function loadMyFavorite()
               {
                   LG.ajax({
                       loading: '正在加载用户收藏中...',
                       type: 'mainAction',
                       method: 'GetMyFavorite',
                       success: function (Favorite)
                       {
                           links.html("");
                           $(Favorite).each(function (i, data)
                           {
                               var item = $('<div class="link"><img src="" /><a href="javascript:void(0)"></a><div class="close"></div></div>');
                               $("img", item).attr("src", data.icon);
                               $("a", item).html(data.menuname);
                               item.attr("id", data.id);
                               //item.attr("title", data.FavoriteContent || data.FavoriteTitle);
                               item.attr("url", data.url);
                               links.append(item);
                           });
                       },
                       error: function (message)
                       {
                           LG.showError(message);
                       }
                   }); 
               }

               function loadInfo()
               {
                   LG.ajax({
                       type: 'mainAction',
                       method: 'GetCurrentUser',
                       success: function (user)
                       {
                           $("#labelusername").html(user.nickname);

                           $("#usersetup").attr("url", "appsystem/user!Listdetail.do?search.pid=" + user.id); 
                       },
                       error: function ()
                       {
                           LG.tip('用户信息加载失败');
                       }
                   });


                   var now = new Date(), hour = now.getHours();
                   if (hour > 4 && hour < 6) { $("#labelwelcome").html("凌晨好！") }
                   else if (hour < 9) { $("#labelwelcome").html("早上好！") }
                   else if (hour < 12) { $("#labelwelcome").html("上午好！") }
                   else if (hour < 14) { $("#labelwelcome").html("中午好！") }
                   else if (hour < 17) { $("#labelwelcome").html("下午好！") }
                   else if (hour < 19) { $("#labelwelcome").html("傍晚好！") }
                   else if (hour < 22) { $("#labelwelcome").html("晚上好！") }
                   else { $("#labelwelcome").html("夜深了，注意休息！") }

                   var lastlogintime = LG.cookies.get("CurrentUserLastLoginTime") || "从不";
                   $("#labelLastLoginTime").html("您上次的登录时间是：" + lastlogintime);

                   $("#usersetup").click(function ()
                   {
                       var url = $(this).attr("url");
                       if (!url) return;
                       var text = "修改用户信息";
                       parent.f_addTab(null, text, url);
                   });
               }

               loadInfo();
               loadMyFavorite();
           </script>  
</body>
</html>
