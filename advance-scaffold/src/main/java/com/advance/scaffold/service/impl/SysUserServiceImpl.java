package com.advance.scaffold.service.impl;

import com.app.common.StringUtils;
import com.app.common.TypeConvert;
import com.app.common.aes.MD5Util;
import com.advance.scaffold.core.constant.GlobalConstant;
import com.advance.scaffold.core.model.SessionInfo;
import com.advance.scaffold.model.SysUser;
import com.advance.scaffold.model.SysUserRole;
import com.advance.scaffold.mapper.SysUserMapper;
import com.advance.scaffold.service.SysUserRoleService;
import com.advance.scaffold.service.SysUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SysUser 表数据服务层接口实现类
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Override
	public SysUser login(SysUser user) {
		user.setPassword(MD5Util.md5(user.getPassword()));
		SysUser sysUser = this.selectOne(new EntityWrapper<SysUser>(user));
		return sysUser;
	}

	@Override
	public Page<SysUser> getUsers(Page page) {
		List<SysUser> users = sysUserMapper.getUsers(page,page.getCondition());
		page.setRecords(users);
		return page;
	}

	@Override
	public boolean editUserPwd(SessionInfo sessionInfo, String oldPwd, String pwd) {

		SysUser sysUser = this.selectById(sessionInfo.getId());
		if (sysUser.getPassword().equalsIgnoreCase(MD5Util.md5(oldPwd))) {// 说明原密码输入正确
			sysUser.setPassword(MD5Util.md5(pwd));
			this.updateById(sysUser);
			return true;
		}
		return false;
	}

	@Override
	public List<String> listResource(Long id) {
		List<String> resource = sysUserMapper.listResource(id);
		return resource;
	}

	@Override
	public void saveInfo(SysUser user) {
		String password = user.getPassword();
		user.setPassword(MD5Util.md5(password));
		user.setState(GlobalConstant.ENABLE);
		user.setCreateTime(new Date());
		insert(user);

		// 添加角色
		List<SysUserRole> userRoles = new ArrayList<SysUserRole>();
		if (StringUtils.isNotBlank(user.getRoleIds())) {
			String roleIds = user.getRoleIds();
			if (roleIds.contains(",")) {
				for (String roleId : roleIds.split(",")) {
					SysUserRole userRole = new SysUserRole();
					userRole.setRoleId(TypeConvert.toLong(roleId));
					userRole.setUserId(user.getId());
					userRoles.add(userRole);
				}
			} else {
				SysUserRole userRole = new SysUserRole();
				userRole.setRoleId(TypeConvert.toLong(user.getRoleIds()));
				userRole.setUserId(user.getId());
				userRoles.add(userRole);
			}

			sysUserRoleService.insertBatch(userRoles);
		}

	}

	@Override
	public void updateInfo(SysUser user) {

		String password = user.getPassword();
		if (StringUtils.isNotBlank(password))
			user.setPassword(MD5Util.md5(password));
		else
			user.setPassword(null);
		updateById(user);

		// 删除当前用户的角色关系
		SysUserRole delUserRole = new SysUserRole();
		delUserRole.setUserId(user.getId());
		sysUserRoleService.delete(new EntityWrapper<SysUserRole>(delUserRole));

		// 重新添加角色关系
		List<SysUserRole> userRoles = new ArrayList<SysUserRole>();
		if (StringUtils.isNotBlank(user.getRoleIds())) {
			String roleIds = user.getRoleIds();
			if (roleIds.contains(",")) {
				for (String roleId : roleIds.split(",")) {
					SysUserRole userRole = new SysUserRole();
					userRole.setRoleId(TypeConvert.toLong(roleId));
					userRole.setUserId(user.getId());
					userRoles.add(userRole);
				}
			} else {
				SysUserRole userRole = new SysUserRole();
				userRole.setRoleId(TypeConvert.toLong(user.getRoleIds()));
				userRole.setUserId(user.getId());
				userRoles.add(userRole);
			}

			sysUserRoleService.insertBatch(userRoles);
		}
	}

	@Override
	public SysUser getInfo(Long id) {
		return sysUserMapper.getInfo(id);
	}

}