package com.advance.scaffold.controller;

import com.advance.scaffold.core.constant.GlobalConstant;
import com.advance.scaffold.core.controller.ConsoleController;
import com.advance.scaffold.core.model.Json;
import com.advance.scaffold.core.model.Tree;
import com.advance.scaffold.core.model.UserSessionInfo;
import com.advance.scaffold.model.SysResource;
import com.advance.scaffold.service.SysResourceService;
import com.app.common.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/resource")
public class SysResourceController extends ConsoleController {

	@Autowired
	private SysResourceService sysResourceService;

	@RequestMapping("/manager")
	public String manager() {
		return "/resource/resourceIndex";
	}

	@RequestMapping("/tree")
	@ResponseBody
	public void tree() {
		UserSessionInfo sessionInfo = (UserSessionInfo) session.getAttribute(GlobalConstant.USER_SESSION);
		this.printJson(sysResourceService.tree(sessionInfo));
	}

	@RequestMapping("/allTree")
	@ResponseBody
	public void allTree(boolean flag) {// true获取全部资源,false只获取菜单资源
		try {
			List<Tree> trees = sysResourceService.listAllTree(flag);
			this.printJson(trees);
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}
	}

	@RequestMapping("/treeGrid")
	@ResponseBody
	public void treeGrid() {
		try {
			this.printJson(sysResourceService.treeGrid());
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}
	}

	@RequestMapping("/get")
	@ResponseBody
	public void get(Long id) {
		try {
			this.printJson(sysResourceService.selectById(id));
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}
	}

	@RequestMapping("/editPage")
	public String editPage(@RequestParam("id") Long id) {
		try {
			SysResource r = sysResourceService.selectById(id);
			request.setAttribute("resource", r);
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}
		return "/resource/resourceUpdate";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(SysResource resource) throws InterruptedException {
		Json json = new Json();
		try {
			sysResourceService.updateById(resource);
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
			sysResourceService.deleteById(id);
			json.setMsg("删除成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			logger.error(Common.method(), e);
		}
		this.printJson(json);
	}

	@RequestMapping("/addPage")
	public String addPage() {
		return "/resource/resourceAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(SysResource resource) {
		Json json = new Json();
		try {
			resource.setCreateTime(new Date());
			sysResourceService.insert(resource);
			json.setSuccess(true);
			json.setMsg("添加成功！");
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			logger.error(Common.method(), e);
		}
		this.printJson(json);
	}

}
