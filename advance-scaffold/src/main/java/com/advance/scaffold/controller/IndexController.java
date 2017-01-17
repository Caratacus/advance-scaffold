package com.advance.scaffold.controller;

import com.advance.scaffold.core.constant.GlobalConstant;
import com.advance.scaffold.core.controller.ConsoleController;
import com.advance.scaffold.core.model.UserSessionInfo;
import com.advance.scaffold.model.SysUser;
import com.advance.scaffold.service.SysResourceService;
import com.advance.scaffold.service.SysUserService;
import com.app.common.Common;
import com.app.common.aes.MD5Util;
import com.app.common.base.model.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.advance.scaffold.core.constant.ErrorCode.x10001;
import static com.advance.scaffold.core.constant.ErrorCode.x10002;
import static com.advance.scaffold.core.constant.ErrorCode.x10003;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Controller
@RequestMapping("/welcome")
public class IndexController extends ConsoleController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysResourceService sysResourceService;

	@RequestMapping("/index")
	public String index() {
		UserSessionInfo sessionInfo = (UserSessionInfo) session.getAttribute(GlobalConstant.USER_INFO);
		if ((sessionInfo != null) && (sessionInfo.getId() != null)) {
			return "index";
		}
		return "login";
	}

	@PostMapping("/login")
	@ResponseBody
	public void login(@RequestParam("loginName") String loginName, @RequestParam("password") String password) throws Exception {
		RestResult result = restMap();
		try {
			SysUser sysUser = new SysUser();
			sysUser.setLoginName(loginName);
			sysUser.setPassword(MD5Util.md5(password));
			sysUser = sysUserService.login(sysUser);
			if (sysUser != null) {
				if (sysUser.getState() == 0) {
					UserSessionInfo sessionInfo = new UserSessionInfo();
					sessionInfo.setId(sysUser.getId());
					sessionInfo.setLoginName(sysUser.getLoginName());
					sessionInfo.setName(sysUser.getName());
					sessionInfo.setResourceList(sysUserService.listResource(sysUser.getId()));
					sessionInfo.setResourceAllList(sysResourceService.listAllResource());
					session.setAttribute(GlobalConstant.USER_INFO, sessionInfo);
				} else {
					result = failRest(result, BAD_REQUEST, x10002);
				}
			} else {
				result = failRest(result, BAD_REQUEST, x10001);
			}
		} catch (Exception e) {
			result = failRest(result, INTERNAL_SERVER_ERROR, x10003);
			logger.error(Common.method(), e);
		}
		this.printJson(result);

	}

	@RequestMapping("/logout")
	@ResponseBody
	public void logout() {
		RestResult result = restMap();
		if (session != null) {
			session.invalidate();
		}
		result.setMsg("注销成功！");
		this.printJson(result);
	}

	@RequestMapping("/view")
	public String welcome() {
		return "welcome";
	}

}
