<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/common/include.jsp" %>
	<%@ include file="/WEB-INF/views/common/config.jsp" %>
	<meta http-equiv="X-UA-Compatible" content="edge" />
	<script type="text/javascript">
	$(function() {

		$('#rstate').combobox('setValue', '${resource.rstate}');
		$('#resourceType').combobox('setValue', '${resource.resourceType}');

		$('#pid').combotree({
			url : '/resource/allTree?flag=false',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto',
			value : '${resource.pid}'
		});
		
		$('#resourceEditForm').form({
			url : '${pageContext.request.contextPath}/resource/edit',
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
		$("#resourceEditForm").submit();
	}
</script>
</head>
<body>
<div style="padding: 3px;">
	<form id="resourceEditForm" method="post">
		<table  class="grid">
			<tr>
				<td>资源名称</td>
				<td><input name="id" type="hidden"  value="${resource.id}" >
				<input name="name" type="text" placeholder="请输入资源名称" value="${resource.name}" class="easyui-validatebox span2" data-options="required:true" ></td>
				<td>资源类型</td>
				<td><select id="resourceType" name="resourceType" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="0">菜单</option>
							<option value="1">按钮</option>
				</select></td>
			</tr>
			<tr>
				<td>资源路径</td>
				<td><input name="url" type="text" value="${resource.url}" placeholder="请输入资源路径" class="easyui-validatebox span2" ></td>
				<td>排序</td>
				<td><input name="seq" value="${resource.seq}"  class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false"></td>
			</tr>
			<tr>
				<td>菜单图标</td>
				<td ><input  name="icon" value="${resource.icon}"/></td>

				<%--<c:if test="${resource.pid != null}">--%>
				<td>状态</td>
				<td ><select id="rstate" name="rstate" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="0">正常</option>
							<option value="1">停用</option>
				</select></td>
				<%--</c:if>--%>
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
