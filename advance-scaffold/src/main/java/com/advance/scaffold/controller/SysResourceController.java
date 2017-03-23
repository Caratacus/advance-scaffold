package com.advance.scaffold.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.advance.scaffold.core.constant.GlobalConstant;
import com.advance.scaffold.core.controller.ConsoleController;
import com.advance.scaffold.core.model.Grid;
import com.advance.scaffold.core.model.Json;
import com.advance.scaffold.core.model.UserSessionInfo;
import com.advance.scaffold.core.model.ZTree;
import com.advance.scaffold.model.Department;
import com.advance.scaffold.model.SysResource;
import com.advance.scaffold.model.TreeList;
import com.advance.scaffold.service.SysResourceService;
import com.app.common.Common;
import com.app.common.JsonUtils;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;

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
		UserSessionInfo sessionInfo = (UserSessionInfo) session.getAttribute(GlobalConstant.USER_INFO);
		request.setAttribute("trees", sysResourceService.treeResources(sessionInfo.getId()));
		System.out.println(JsonUtils.toJson(sysResourceService.treeResources(sessionInfo.getId())));
	}

	@RequestMapping("/allTree")
	@ResponseBody
	public void allTree(boolean flag) {// true获取全部资源,false只获取菜单资源
		try {
//			List<Tree> trees = sysResourceService.listAllTree(flag);
			List<ZTree> ztrees = sysResourceService.listAllZTree(flag);

			this.printJson(ztrees);
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public void dataGrid(SysResource resource) {
		Grid grid = new Grid();
		try {
			Page<SysResource> sysResourcePage = sysResourceService.dataGrid(resource, getPage());
//			grid.setRows(sysRolePage.getRecords());
			grid.setRows(sysResourceService.selectList(Condition.Empty()));
			grid.setTotal(sysResourcePage.getTotal());
			this.printJson(grid);
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}
	}

	@RequestMapping("/treeGrid")
	@ResponseBody
	public void treeGrid() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			List<Department> list = sysResourceService.treeGrid1();
			TreeList tree = new TreeList(list);
			List<Department> listTree = tree.buildTree();
			map.put("rows", listTree);
			map.put("total", listTree.size());

			this.printJson(map);
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
