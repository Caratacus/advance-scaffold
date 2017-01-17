package com.advance.scaffold.core.interceptors;

import com.advance.scaffold.core.constant.GlobalConstant;
import com.advance.scaffold.core.model.UserSessionInfo;
import com.app.common.TypeConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 权限拦截器
 * </p>
 *
 * @author Caratacus
 * @Date 2016-11-11
 */
public class SecurityInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(getClass());
	/*
	 * 不需要拦截的资源
	 */
	private List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * 完成页面的render后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
			throws Exception {

	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView)
			throws Exception {

	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// ResourceHttpRequestHandler放行
		if (handler instanceof ResourceHttpRequestHandler) {
			return true;
		}
		UserSessionInfo sessionInfo = (UserSessionInfo) request.getSession().getAttribute(GlobalConstant.USER_INFO);
		String servletPath = TypeConvert.toString(request.getServletPath());
		logger.info("requestPath:" + request.getServletPath());
		// 不需要校验权限的路径
		if (excludeUrls.contains(servletPath)) {
			return true;
		}
		// 如果没有登录或登录超时
		if ((sessionInfo == null) || (sessionInfo.getId() == null)) {
			request.getRequestDispatcher("/static/error/NotLogin.jsp").forward(request, response);
			return false;
		}
		// 权限包含此路径
		if (!sessionInfo.getResourceAllList().contains(servletPath)) {
			return true;
		}
		// 如果当前用户没有访问此资源的权限
		if (!sessionInfo.getResourceList().contains(servletPath)) {
			request.getRequestDispatcher("/static/error/404.jsp").forward(request, response);
			return false;
		}
		return true;
	}
}
