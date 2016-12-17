<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/common/include.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<c:if test="${fn:contains(sessionInfo.resourceList, '/resource/edit')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionInfo.resourceList, '/resource/delete')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<title>资源管理</title>
<script type="text/javascript">
	var treeGrid;

	$(function() {
		
		treeGrid = $('#treeGrid').treegrid({
			url : '${root}/resource/treeGrid',
			idField : 'id',
			treeField : 'name',
			parentField : 'pid',
			fit : true,
			fitColumns : false,
			border : false,
			frozenColumns : [ [ {
				title : '编号',
				field : 'id',
				width : 40
			} ] ],
			columns : [ [ {
				field : 'name',
				title : '资源名称',
				width : 150
			}, {
				field : 'url',
				title : '资源路径',
				width : 200
			}, {
				field : 'seq',
				title : '排序',
				width : 40
			}, {
				field : 'icon',
				title : '图标',
				width : 100
			}, {
				field : 'resourceType',
				title : '资源类型',
				width : 80,
				formatter : function(value, row, index) {
					switch (value) {
					case 0:
						return '菜单';
					case 1:
						return '按钮';
					}
				}
			}, {
				field : 'pid',
				title : '上级资源ID',
				width : 150,
				hidden : true
			}, {
				field : 'rstate',
				title : '状态',
				width : 40,
				formatter : function(value, row, index) {
					switch (value) {
					case 0:
						return '正常';
					case 1:
						return '停用';
					}
				}
			}, {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '&nbsp;';
					if ($.canEdit) {
					str += $.formatString('<a href="javascript:void(0)" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
					}
					if ($.canDelete) {
					str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
					str += $.formatString('<a href="javascript:void(0)" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar'
		});
	});
	
	function editFun(id) {
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			layer.open({
				title:'编辑',
				type: 2,
				skin: 'layui-layer-rim', //加上边框
				area: ['500px', '350px'], //宽高
				content: '${root}/resource/editPage?id=' + node.id,
				btn: ['确定'], //只是为了演示
				yes: function(index,layero){

					var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，
					// 执行iframe页的方法：
					iframeWin.submit();
					layer.alert("编辑成功")
				}
			});
		}
	}
	

	function deleteFun(id) {
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			layer.confirm('您是否要删除当前资源？删除当前资源会连同子资源一起删除!', {icon: 3, title:'提示'}, function(index){
				progressLoad();
				$.post('${pageContext.request.contextPath}/resource/delete', {
					id : node.id
				}, function(result) {
					if (result.success) {
						layer.alert(result.msg, {icon: 1});
						reloadDG();
					}
					progressClose();
				}, 'JSON');
				layer.close(index);
			});

		}
	}
	
	function addFun() {
		layer.open({
			title:'添加',
			type: 2,
			skin: 'layui-layer-rim', //加上边框
			area: ['500px', '350px'], //宽高
			content: '${root}/resource/addPage',
			btn: ['确定'], //只是为了演示
			yes: function(index,layero){

				var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，
				// 执行iframe页的方法：
				iframeWin.submit();
				layer.alert("添加成功")
			}
		});
	}

	//刷新列表
	function reloadDG(){
		treeGrid.treegrid('reload');
		parent.layout_west_tree.tree('reload');
	}
	</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false"  style="overflow: hidden;">
			<table id="treeGrid"></table>
		</div>
	</div>
	
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionInfo.resourceList, '/resource/add')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
		</c:if>
	</div>

</body>
</html>