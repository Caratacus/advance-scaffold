package com.advance.scaffold.controller;

import com.advance.scaffold.core.constant.GlobalConstant;
import com.advance.scaffold.core.controller.ConsoleController;
import com.advance.scaffold.core.model.Json;
import com.advance.scaffold.core.model.SessionInfo;
import com.advance.scaffold.model.SysUser;
import com.advance.scaffold.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.common.Common;
import com.app.common.TypeConvert;
import com.advance.scaffold.service.SysUserService;

@Controller
@RequestMapping("/welcome")
public class IndexController extends ConsoleController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysResourceService sysResourceService;

	@RequestMapping("/index")
	public String index() {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(GlobalConstant.SESSION_INFO);
		if ((sessionInfo != null) && (sessionInfo.getId() != null)) {
			return "index";
		}
		return "login";
	}

	@RequestMapping("/login")
	@ResponseBody
	public void login(SysUser user) throws Exception {
		Json json = new Json();
		String loginname = TypeConvert.toString(user.getLoginName());
		String password = TypeConvert.toString(user.getPassword());
		if ("用户名".equals(loginname) || "密码".equals(password)) {
			Thread.sleep(1500);
			json.setMsg("账号或密码不能为空");
			this.printJson(json);
			return;
		}
		try {
			SysUser sysuser = sysUserService.login(user);
			if (sysuser != null) {
				if (sysuser.getState() == 0) {
					json.setSuccess(true);
					json.setMsg("登陆成功！");
					SessionInfo sessionInfo = new SessionInfo();
					sessionInfo.setId(sysuser.getId());
					sessionInfo.setLoginname(sysuser.getLoginName());
					sessionInfo.setName(sysuser.getName());
					sessionInfo.setResourceList(sysUserService.listResource(sysuser.getId()));
					sessionInfo.setResourceAllList(sysResourceService.listAllResource());
					session.setAttribute(GlobalConstant.SESSION_INFO, sessionInfo);
				} else {
					json.setMsg("用户已停用！");
				}
			} else {
				json.setMsg("用户名或密码错误！");
			}
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			logger.error(Common.method(), e);
		}
		this.printJson(json);

	}

	@RequestMapping("/logout")
	@ResponseBody
	public void logout() {
		Json json = new Json();
		if (session != null) {
			session.invalidate();
		}
		json.setSuccess(true);
		json.setMsg("注销成功！");
		this.printJson(json);
	}

}
