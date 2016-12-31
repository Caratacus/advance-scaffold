$(function(){
		//右键菜单
		$('#index_tabs').tabs({
            onContextMenu: function (e, title, index) {
                e.preventDefault();
                if (index > 0) {
                    $('#mm').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    }).data("tabTitle", title);
                }
            }
		});
		
		//右键菜单click
        $("#mm").menu({
            onClick: function (item) {
                closeTab(this, item.name);
            }
        });
		
	});
	
	 //删除Tabs
    function closeTab(menu, type) {
        var allTabs = $("#index_tabs").tabs('tabs');
        var allTabtitle = [];
        $.each(allTabs, function (i, n) {
            var opt = $(n).panel('options');
            if (opt.closable)
                allTabtitle.push(opt.title);
        });
        var curTabTitle = $(menu).data("tabTitle");
        var curTabIndex = $("#index_tabs").tabs("getTabIndex", $("#index_tabs").tabs("getTab", curTabTitle));
       // alert(type)
        switch (type) {
            case 1:
                $("#index_tabs").tabs("close", curTabIndex);
                return false;
                break;
            case 2:
                for (var i = 0; i < allTabtitle.length; i++) {
                    $('#index_tabs').tabs('close', allTabtitle[i]);
                }
                break;
            case 3:
                for (var i = 0; i < allTabtitle.length; i++) {
                    if (curTabTitle != allTabtitle[i])
                        $('#index_tabs').tabs('close', allTabtitle[i]);
                }
                $('#index_tabs').tabs('select', curTabTitle);
                break;
            case 4:
                for (var i = curTabIndex; i < allTabtitle.length; i++) {
                    $('#index_tabs').tabs('close', allTabtitle[i]);
                }
                $('#index_tabs').tabs('select', curTabTitle);
                break;
            case 5:
                for (var i = 0; i < curTabIndex-1; i++) {
                    $('#index_tabs').tabs('close', allTabtitle[i]);
                }
                $('#index_tabs').tabs('select', curTabTitle);
                break;
            case 6: //刷新
            	//var tab = $('#index_tabs').tabs('getSelected'); 
            	//var url = $(tab.panel('options').content).attr('src');  
            	//tab.panel('open').panel('refresh',url);
            	//var panel = $("#index_tabs").tabs("getTab", curTabTitle).panel("refresh");
            	var currTab = $('#index_tabs').tabs('getSelected'); //获取选中的标签项
    			var href = currTab.panel('options').href;
    			var $frame = $(currTab.panel('options').content); //获取面板iframe的jquery元素
    			var url = $frame.attr('src'); //获取该选项卡中内容标签（iframe）的 src 属性
    			var name = $frame.attr('name');
    			var frameId = $frame.attr('id');
    			if (!url) {
    				$('#index_tabs').tabs('update', {
    					tab : currTab,
    					options : {
    						href : href
    					}
    				});
    			} else {
    				/* 重新设置该标签 */
    				$('#index_tabs').tabs('update', {
    					tab : currTab,
    					options : {
    						content : createTabContent(name, frameId, url)
    					}
    				});
    			}
                break;
        }
    }
	 
    function createTabContent(name, frameId, url) {
    	return '<iframe name="'
    			+ name
    			+ '" id="'
    			+ frameId
    			+ '" src="'
    			+ url
    			+ '" width="100%" height="100%" frameborder="0" scrolling="auto" ></iframe>';
    }