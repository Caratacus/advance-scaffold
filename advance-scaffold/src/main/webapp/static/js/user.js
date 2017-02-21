/*
* @Author: Larry
* @Date:   2016-12-15 17:20:54
* @Last Modified by:   qinsh
* @Last Modified time: 2016-12-24 22:06:18
* +----------------------------------------------------------------------
* | LarryBlogCMS [ LarryCMS网站内容管理系统 ]
* | Copyright (c) 2016-2017 http://www.larrycms.com All rights reserved.
* | Licensed ( http://www.larrycms.com/licenses/ )
* | Author: qinshouwei <313492783@qq.com>
* +----------------------------------------------------------------------
*/
'use strict';
var index ;
layui.use(['jquery','layer','element','form'],function(){
	window.jQuery = window.$ = layui.jquery;
	window.layer = layui.layer;
    //layui.tree(options);
    var element = layui.element();
    var form = layui.form();

    form.on('submit(formDemo)', function(data){
        $.post('/user/add',data.field,function(res){
            var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引

            var data = $.parseJSON(res);
            if(data.success){
                parent.layer.msg(data.msg, {
                    icon: 1,
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function(){
                    //do something
                    parent.layer.close(index); //执行关闭
                    parent.location.reload();
                });

            }else{
                layer.alert(data.msg);
            }
        });
        return false;
    });

    $("#btn_add").click(function(){

        layer.open({
            title:'新增',
            type: 2,
            skin: 'layui-layer-rim', //加上边框
            area: ['600px', '450px'], //宽高
            content: '/user/addPage'
        });
    });

});
