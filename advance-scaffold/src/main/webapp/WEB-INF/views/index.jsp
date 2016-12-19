<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/views/common/include.jsp" %>
    <script type="text/javascript" src="${root}/static/jslib/custom/index.js" charset="utf-8"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>主页</title>
    <script type="text/javascript">
        var index_layout;
        var index_tabs;
        var index_tabsMenu;
        var layout_west_tree;
        var layout_west_tree_url = '';

        var sessionInfo_userId = '${sessionInfo.id}';
        if (sessionInfo_userId) {//如果没有登录,直接跳转到登录页面
            layout_west_tree_url = '${root}/resource/tree';
        } else {
            window.location.href = '${root}/welcome/index';
        }
        $(function () {
            index_layout = $('#index_layout').layout({
                fit: true
            });

            index_tabs = $('#index_tabs').tabs({
                fit: true,
                border: false,
                tools: [{
                    iconCls: 'icon-home',
                    handler: function () {
                        index_tabs.tabs('select', 0);
                    }
                }, {
                    iconCls: 'icon-refresh',
                    handler: function () {
                        var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                        index_tabs.tabs('getTab', index).panel('open').panel('refresh');
                    }
                }, {
                    iconCls: 'icon-del',
                    handler: function () {
                        var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                        var tab = index_tabs.tabs('getTab', index);
                        if (tab.panel('options').closable) {
                            index_tabs.tabs('close', index);
                        }
                    }
                }]
            });

            layout_west_tree = $('#layout_west_tree').tree({
                url: layout_west_tree_url,
                parentField: 'pid',
                lines: true,
                onClick: function (node) {
                    if (node.attributes && node.attributes.url) {
                        var url = '${root}' + node.attributes.url;
                        addTab({
                            url: url,
                            title: node.text,
                            iconCls: node.iconCls
                        });
                    }
                }
            });
        });

        function addTab(params) {
            var iframe = '<iframe src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:99.5%;"></iframe>';
            var t = $('#index_tabs');
            var opts = {
                title: params.title,
                closable: true,
                iconCls: params.iconCls,
                content: iframe,
                border: false,
                fit: true
            };
            if (t.tabs('exists', opts.title)) {
                t.tabs('select', opts.title);
            } else {
                t.tabs('add', opts);
            }
        }

        function logout() {

            //询问框
            layer.confirm('确定要退出?', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                progressLoad();
                $.post('${root}/welcome/logout', function (result) {
                    if (result.success) {
                        progressClose();
                        window.location.href = '${root}/welcome/index';
                    }
                }, 'json');
            });
        }


        function editUserPwd() {
            parent.$.modalDialog({
                title: '修改密码',
                width: 300,
                height: 250,
                href: '${root}/user/editPwdPage',
                buttons: [{
                    text: '修改',
                    handler: function () {
                        var f = parent.$.modalDialog.handler.find('#editUserPwdForm');
                        f.submit();
                    }
                }]
            });
        }

    </script>
</head>
<body>
<div id="loading"
     style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #fff;z-index: 100;overflow: hidden;">
    <img src="${root}/static/style/images/ajax-loader.gif"
         style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
</div>
<div id="index_layout">
    <div data-options="region:'north',border:false" style=" overflow: hidden;">

        <div id="app-header" data-options="region:'north'"
             style="color: rgb(254, 254, 254); border: 0px; overflow: hidden; width: 1366px; height: 40px;" title=""
             class="panel-body panel-body-noheader layout-body">
            <div style="float:left; width:150px;">
                <h1 class="app-logo" data-init="config['system.appname']">Web+</h1>
            </div>
            <ul style="float:left;">
                <li><a href="${root}/welcome/index" target="_blank" title="查看前台主页"><i class="fa fa-home"></i> 回首页</a>
                </li>
                <%--<li><a href="javascript:void(0);" id="fullscreen" title="全屏" onclick="$(document).toggleFullScreen();" style=""><i class="fa fa-expand"></i></a></li>--%>
            </ul>
            <ul style="float:right;">
                <li><span><i class="fa fa-user"></i> <label>${sessionInfo.name}</label>, <label>欢迎回来！</label></span>
                </li>
                <li><a href="javascript:void(0);" onclick="editUserPwd();"><i class="fa fa-unlock"></i> 修改密码</a></li>
                <li><a href="javascript:void(0);" onclick="logout()" title="安全退出"><i class="fa fa-sign-out"></i>
                    退出登录</a></li>
            </ul>
        </div>
    </div>
    <div data-options="region:'west',split:true" title="主导航" style="width: 160px; overflow: hidden;overflow-y:auto;">
        <div class="well well-small" style="padding: 5px 5px 5px 5px;">
            <ul id="layout_west_tree"></ul>
        </div>
    </div>
    <div data-options="region:'center'" style="overflow: hidden;">
        <div id="index_tabs" style="overflow: hidden;">
            <div title="首页" data-options="border:false" style="overflow: hidden;">
                <div style="padding:0 0 10px 10px">
                    <h2>系统介绍</h2>
                    <div class="light-info">
                        <div class="light-tip icon-tip"></div>
                        <div>后台管理平台。</div>
                    </div>
                </div>
            </div>
        </div>

        <div id="mm" class="easyui-menu" style="width:140px;">
            <div id="mm-tabrefresh" data-options="name:6">刷新</div>
            <div class="menu-sep"></div>
            <div id="mm-tabclose" data-options="name:1">关闭</div>
            <div id="mm-tabcloseall" data-options="name:2">全部关闭</div>
            <div id="mm-tabcloseother" data-options="name:3">除此之外全部关闭</div>
            <!-- <div id="mm-tabcloseright" data-options="name:4">当前页右侧全部关闭</div>
            <div id="mm-tabcloseleft" data-options="name:5">当前页左侧全部关闭</div> -->
        </div>

    </div>
    <div data-options="region:'south',border:false"
         style="height: 30px;line-height:30px; overflow: hidden;text-align: center;background-color: #eee"> 版权所有@Web+
    </div>
</div>

<style>
    /*ie6提示*/
    #ie6-warning {
        width: 100%;
        position: absolute;
        top: 0;
        left: 0;
        background: #fae692;
        padding: 5px 0;
        font-size: 12px
    }

    #ie6-warning p {
        width: 960px;
        margin: 0 auto;
    }
</style>
</body>
</html>