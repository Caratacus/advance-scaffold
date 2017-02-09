/*
* @Author: Larry
* @Date:   2016-12-15 17:20:54
* @Last Modified by:   qinsh
* @Last Modified time: 2016-12-24 21:51:17
* +----------------------------------------------------------------------
* | LarryBlogCMS [ LarryCMS网站内容管理系统 ]
* | Copyright (c) 2016-2017 http://www.larrycms.com All rights reserved.
* | Licensed ( http://www.larrycms.com/licenses/ )
* | Author: qinshouwei <313492783@qq.com>
* +----------------------------------------------------------------------
*/
'use strict';
layui.use(['jquery'],function(){
	window.jQuery = window.$ = layui.jquery;
   $(".layui-canvs").width($(window).width());
   $(".layui-canvs").height($(window).height());

});

layui.use(['form'],
    function () {
       var form = layui.form(),
           layer = layui.layer;
       //监听提交
       form.on('submit(login)',
           function (data) {
              $.post('/welcome/login', data.field, function (text) {
                 if (text.status === 200) {
                    location.href = "/welcome/index";
                 }
                 layer.msg(text.msg);
              }, 'json');
              return false;
           });

    });
