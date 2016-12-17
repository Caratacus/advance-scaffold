package com.advance.scaffold.controller;

import java.util.Date;

import com.advance.scaffold.core.controller.ConsoleController;
import com.advance.scaffold.core.model.Json;
import com.advance.scaffold.model.SysOrganization;
import com.advance.scaffold.service.SysOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.common.Common;


@Controller
@RequestMapping("/organization")
public class SysOrganizationController extends ConsoleController {

	@Autowired
	private SysOrganizationService sysOrganizationService;

	@RequestMapping("/manager")
	public String manager() {
		return "/organization/organizationIndex";
	}

	@RequestMapping("/treeGrid")
	@ResponseBody
	public void treeGrid() {
		try {
			this.printJson(sysOrganizationService.treeGrid());
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}

	}

	@RequestMapping("/tree")
	@ResponseBody
	public void tree() {
		try {
			this.printJson(sysOrganizationService.tree());
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}

	}

	@RequestMapping("/addPage")
	public String addPage() {
		return "/organization/organizationAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(SysOrganization organization) {
		Json json = new Json();
		try {
			organization.setCreateTime(new Date());
			sysOrganizationService.insert(organization);
			json.setSuccess(true);
			json.setMsg("添加成功！");
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			logger.error(Common.method(), e);
		}
		this.printJson(json);
	}

	@RequestMapping("/get")
	@ResponseBody
	public void get(Long id) {
		this.printJson(sysOrganizationService.selectById(id));
	}

	@RequestMapping("/editPage")
	public String editPage(Long id) {
		try {
			SysOrganization o = sysOrganizationService.selectById(id);
			request.setAttribute("organization", o);
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}
		return "/organization/organizationUpdate";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(SysOrganization org) {
		Json json = new Json();
		try {
			sysOrganizationService.updateById(org);
			json.setSuccess(true);
			json.setMsg("编辑成功！");
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
			sysOrganizationService.deleteById(id);
			json.setMsg("删除成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			logger.error(Common.method(), e);
		}
		this.printJson(json);
	}
}
