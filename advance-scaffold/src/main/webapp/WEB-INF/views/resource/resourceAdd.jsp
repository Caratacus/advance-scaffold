<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/common/include.jsp" %>
	<%@ include file="/WEB-INF/views/common/config.jsp" %>
	<meta http-equiv="X-UA-Compatible" content="edge" />
	<script type="text/javascript">


		$(function() {

			$('#pid').combotree({
				url : '/resource/allTree?flag=false',
				parentField : 'pid',
				lines : true,
				panelHeight : 'auto'
			});

			$('#resourceAddForm').form({
				url : '/resource/add',
				onSubmit : function() {
					progressLoad();
					var isValid = $(this).form('validate');
					if (!isValid) {
						progressClose();
					}
					return isValid;
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						//刷新父页面列表
						parent.window.reloadDG();
						parent.layer.close(index);
					}
				}
			});

		});

		function submit(){
			$("#resourceAddForm").submit();
		}
	</script>
</head>
<body>
<div style="padding: 3px;">
	<form id="resourceAddForm" name="resourceAddForm" method="post">
		<table class="grid">
			<tr>
				<td>资源名称</td>
				<td><input name="name" type="text" placeholder="请输入资源名称" class="easyui-validatebox span2" data-options="required:true" ></td>
				<td>资源类型</td>
				<td><select name="resourceType" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
					<option value="0">菜单</option>
					<option value="1">按钮</option>
				</select></td>
			</tr>
			<tr>
				<td>资源路径</td>
				<td><input name="url" type="text" placeholder="请输入资源路径" class="easyui-validatebox span2" data-options="width:140,height:29" ></td>
				<td>排序</td>
				<td><input name="seq" value="0"  class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false"></td>
			</tr>
			<tr>
				<td>菜单图标</td>
				<td ><input  name="icon" /></td>
				<td>状态</td>
				<td ><select name="rstate" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
					<option value="0">正常</option>
					<option value="1">停用</option>
				</select></td>
			</tr>
			<tr>
				<td>上级资源</td>
				<td colspan="3"><select id="pid" name="pid" style="width: 200px; height: 29px;"></select>
					<a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#pid').combotree('clear');" >清空</a></td>
			</tr>
		</table>
	</form>
</div>
</body>