package com.advance.scaffold.controller;

import java.util.Arrays;
import java.util.List;

import com.advance.scaffold.service.SysRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.advance.scaffold.core.controller.ConsoleController;
import com.advance.scaffold.core.model.Grid;
import com.advance.scaffold.core.model.Json;
import com.advance.scaffold.model.SysRole;
import com.advance.scaffold.model.SysRoleResource;
import com.advance.scaffold.service.SysRoleService;
import com.app.common.CollectionUtils;
import com.app.common.Common;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;

@Controller
@RequestMapping("/role")
public class SysRoleController extends ConsoleController {

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private SysRoleResourceService sysRoleResourceService;

	@RequestMapping("/manager")
	public String manager() {
		return "/role/roleIndex";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public void dataGrid(SysRole role) {
		Grid grid = new Grid();
		try {
			Page<SysRole> sysRolePage = sysRoleService.dataGrid(role, getPage());
//			grid.setRows(sysRolePage.getRecords());
			grid.setRows(sysRoleService.selectList(Condition.Empty()));
			grid.setTotal(sysRolePage.getTotal());
			this.printJson(grid);
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}
	}

	@RequestMapping("/tree")
	@ResponseBody
	public void tree() {
		try {
			this.printJson(sysRoleService.tree());
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}
	}

	@RequestMapping("/addPage")
	public String addPage() {
		return "/role/roleAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(SysRole role) {
		Json json = new Json();
		try {
			sysRoleService.insert(role);
			json.setSuccess(true);
			json.setMsg("添加成功！");
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			logger.error(Common.method(), e);
		}
		this.printJson(json);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public void delete(Long id) {
		Json json = new Json();
		try {
			sysRoleService.deleteById(id);
			json.setMsg("删除成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			logger.error(Common.method(), e);
		}
		this.printJson(json);
	}

	@RequestMapping("/deleteByIds")
	@ResponseBody
	public void deleteByIds(String ids) {
		Json json = new Json();
		try {
			String[] idsStr = ids.split(",");
			List list = Arrays.asList(idsStr);
			sysRoleService.deleteBatchIds(list);

			json.setMsg("删除成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			logger.error(Common.method(), e);
		}
		this.printJson(json);
	}

	@RequestMapping("/get")
	@ResponseBody
	public void get(Long id) {
		try {
			this.printJson(sysRoleService.getBySelect(id));
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}
	}

	@RequestMapping("/editPage")
	public String editPage(Long id) {
		try {
			SysRole r = sysRoleService.selectById(id);
			request.setAttribute("role", r);
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}
		return "/role/roleUpdate";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(SysRole role) {
		Json json = new Json();
		try {
			sysRoleService.updateById(role);
			json.setSuccess(true);
			json.setMsg("编辑成功！");
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			logger.error(Common.method(), e);
		}
		this.printJson(json);
	}

	@RequestMapping("/grantPage")
	public String grantPage(Long id) {
		try {
			SysRole r = sysRoleService.selectById(id);
			request.setAttribute("role", r);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(Common.method(), e);
		}
		return "/role/roleGrant";
	}

	@RequestMapping("/grant")
	@ResponseBody
	public void grant(SysRole role) {
		Json json = new Json();
		try {
			List<SysRoleResource> roleResources = sysRoleService.getRoleResources(role);
			boolean flag = false;
			if (CollectionUtils.isNotEmpty(roleResources)) {
				flag = sysRoleResourceService.insertBatch(roleResources);
			}
			if (flag) {
				json.setMsg("授权成功！");

			} else {
				json.setMsg("授权失败！");

			}
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			logger.error(Common.method(), e);
		}
		this.printJson(json);
	}

}
