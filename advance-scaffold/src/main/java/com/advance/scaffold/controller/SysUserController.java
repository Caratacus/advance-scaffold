package com.advance.scaffold.controller;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.advance.scaffold.core.constant.GlobalConstant;
import com.advance.scaffold.core.controller.ConsoleController;
import com.advance.scaffold.core.model.Grid;
import com.advance.scaffold.core.model.Json;
import com.advance.scaffold.core.model.UserSessionInfo;
import com.advance.scaffold.model.SysRole;
import com.advance.scaffold.model.SysUser;
import com.advance.scaffold.service.SysRoleService;
import com.advance.scaffold.service.SysUserService;
import com.app.common.Common;
import com.app.common.MapUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

@Controller
@RequestMapping("/user")
public class SysUserController extends ConsoleController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping("/manager")
	public String manager() {
		return "user/userIndex";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public void dataGrid(SysUser user) {
		Grid grid = new Grid();
		try {
			// 查询参数
			Page<SysUser> page = getPage();
			page.setCondition(MapUtils.beanToMapNotNull(user));
			Page<SysUser> sysUsers = sysUserService.getUsers(page);
			//服务器端分页
			grid.setRows(sysUsers.getRecords());
			//客户端分页
			grid.setRows(sysUserService.selectList(Condition.Empty()));
			grid.setTotal(sysUsers.getTotal());
			this.printJson(grid);
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}
	}

	@RequestMapping("/editPwdPage")
	public String editPwdPage() {
		return "/user/userEditPwd";
	}

	@RequestMapping("/editUserPwd")
	@ResponseBody
	public void editUserPwd(String oldPwd, String pwd) {
		UserSessionInfo sessionInfo = (UserSessionInfo) request.getSession().getAttribute(GlobalConstant.USER_INFO);
		Json json = new Json();
		try {
			boolean pwdflag = sysUserService.editUserPwd(sessionInfo, oldPwd, pwd);
			if (pwdflag) {
				json.setSuccess(true);
				json.setMsg("密码修改成功！");
			} else {
				json.setSuccess(false);
				json.setMsg("原密码错误！");
			}
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			logger.error(Common.method(), e);
		}
		this.printJson(json);
	}

	@RequestMapping("/addPage")
	public String addPage() {
		List<SysRole> roles = sysRoleService.selectList(Condition.Empty());
		request.setAttribute("roles",roles);
//		request.setAttribute("treeLay", JsonUtils.toJson(sysRoleService.treeLay()));
		return "/user/userAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public void add(SysUser user) {
		Json json = new Json();
		int count = sysUserService.selectCount(new EntityWrapper<SysUser>(user));
		if (count > 0) {
			json.setMsg("用户名已存在!");
		} else {
			try {
				sysUserService.saveInfo(user);
				json.setSuccess(true);
				json.setMsg("添加成功！");
			} catch (Exception e) {
				json.setMsg(e.getMessage());
				logger.error(Common.method(), e);
			}

		}
		this.printJson(json);
	}

	@RequestMapping("/get")
	@ResponseBody
	public void get(Long id) {
		try {
			this.printJson(sysUserService.selectById(id));
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public void delete(Long id) {
		Json json = new Json();
		try {
			List<Long> ids = new ArrayList<Long>();
			sysUserService.deleteById(id);
			sysUserService.deleteBatchIds(ids);
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
	public void deleteByIds(List<Long> ids) {
		Json json = new Json();
		try {
			sysUserService.deleteBatchIds(ids);
			json.setMsg("删除成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			logger.error(Common.method(), e);
		}
		this.printJson(json);
	}



	@RequestMapping("/editPage")
	public String editPage(Long id) {
		try {
			SysUser sysUser = sysUserService.getInfo(id);
			List<SysRole> roles = sysRoleService.selectList(Condition.Empty());
			request.setAttribute("user", sysUser);
			request.setAttribute("roles",roles);
		} catch (Exception e) {
			logger.error(Common.method(), e);
		}
		return "/user/userUpdate";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public void edit(SysUser user) {
		Json json = new Json();
		try {
			sysUserService.updateInfo(user);
			json.setSuccess(true);
			json.setMsg("编辑成功！");
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			logger.error(Common.method(), e);
		}
		this.printJson(json);
	}

}
