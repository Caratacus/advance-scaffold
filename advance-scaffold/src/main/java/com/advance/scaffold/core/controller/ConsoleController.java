package com.advance.scaffold.core.controller;

import com.advance.scaffold.core.common.RestUtils;
import com.advance.scaffold.core.constant.ErrorCode;
import com.advance.scaffold.core.constant.GlobalConstant;
import com.app.common.TypeConvert;
import com.app.common.base.controller.SuperController;
import com.app.common.base.model.RestResult;
import com.app.common.rest.RestHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 * 基础控制器
 * </p>
 *
 * @author Caratacus
 * @Date 2016-07-22
 */
public class ConsoleController extends SuperController implements HandlerInterceptor {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private final TypeReference<Map<String, Object>> mapType = new TypeReference<Map<String, Object>>() {
	};

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		/*
		 * 方法调用后调用该方法，返回数据给请求页
		 */
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	/**
	 * 判断是否为合法的视图地址
	 *
	 * @param modelAndView
	 * @return
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/26 0026
	 * @version 1.0
	 */
	protected boolean isLegalView(ModelAndView modelAndView) {
		boolean legal = false;
		if (modelAndView != null) {
			String viewUrl = modelAndView.getViewName();
			if (viewUrl != null && viewUrl.contains("redirect:")) {
				legal = false;
			} else {
				legal = true;
			}
		}
		return legal;
	}

	/**
	 * 获取mybatis plus分页对象
	 *
	 * @param size
	 *            每页显示数量
	 * @return
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/26 0026
	 * @version 1.0
	 */
	protected Page getPage(int size) {

		int _size = size, _index = 1;
		// 页数
		Integer cursor = TypeConvert.toInteger(request.getParameter("pageNumber"));
		// 分页大小
		Integer limit = TypeConvert.toInteger(request.getParameter("pageSize"));
		// 排序
		boolean order = TypeConvert.toString(request.getParameter("order")).equalsIgnoreCase("asc") ? true : false;
		String sort = TypeConvert.toString(request.getParameter("sort"));

		if (limit != null) {
			_size = limit;
		}
		if (limit != null && limit > GlobalConstant.MAX_LIMIT) {
			_size = GlobalConstant.MAX_LIMIT;
		}
		if (cursor != null) {
			_index = cursor;
		}else{
			cursor = _index;
		}
		Page page = new Page(cursor, limit);
		page.setOrderByField(sort);
		page.setAsc(order);
		return page;
	}

	/**
	 * 获取mybatis plus分页对象
	 *
	 * @return
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/26 0026
	 * @version 1.0
	 */
	protected Page getPage() {
		return getPage(GlobalConstant.DEFAULT_LIMIT);
	}

	/**
	 * 重定向
	 *
	 * @param url
	 *            重定向路径
	 * @return String
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/26 0026
	 * @version 1.0
	 */
	protected String redirectTo(String url) {
		StringBuffer rto = new StringBuffer("redirect:");
		rto.append(url);
		return rto.toString();
	}

	/**
	 * REST错误返回
	 *
	 * @param status
	 * @param errorCode
	 * @param isShow
	 *            是否展示
	 * @return RestResult
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/26 0026
	 * @version 1.0
	 */
	protected RestResult failRest(RestResult result, HttpStatus status, ErrorCode errorCode, Boolean isShow) {
		return RestUtils.failResult(result, response, status, errorCode, isShow);
	}

	/**
	 * REST错误返回(默认展示消息)
	 *
	 * @param status
	 * @param errorCode
	 * @return RestResult
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/26 0026
	 * @version 1.0
	 */
	protected RestResult failRest(RestResult result, HttpStatus status, ErrorCode errorCode) {
		return RestUtils.failResult(result, response, status, errorCode, true);
	}

	/**
	 * REST错误返回
	 *
	 * @param status
	 * @param errorCode
	 * @param exception
	 * @param isShow
	 *            是否展示
	 * @return RestResult
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/26 0026
	 * @version 1.0
	 */
	protected RestResult failRest(RestResult result, HttpStatus status, ErrorCode errorCode, Exception exception, Boolean isShow) {
		return RestUtils.failResult(result, response, status, errorCode, exception, isShow);
	}

	/**
	 * REST错误返回(默认展示消息)
	 *
	 * @param status
	 * @param errorCode
	 * @param exception
	 * @return RestResult
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/26 0026
	 * @version 1.0
	 */
	protected RestResult failRest(RestResult result, HttpStatus status, ErrorCode errorCode, Exception exception) {
		return RestUtils.failResult(result, response, status, errorCode, exception, true);
	}

	/**
	 * 输出JSON(默认加密,打印日志)
	 *
	 * @param object
	 *            需要转换JSON的对象
	 * @return
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/26 0026
	 * @version 1.0
	 */
	protected void printJson(Object object, boolean log) {
		printJsonConsole(object, log);
	}

	/**
	 * 输出JSON(默认加密,打印日志)
	 *
	 * @param object
	 *            需要转换JSON的对象
	 * @return
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/26 0026
	 * @version 1.0
	 */
	protected void printJson(Object object) {
		printJsonConsole(object, true);
	}

	/**
	 * 获取请求参数
	 *
	 * @param
	 * @return Map<String, Object>
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/26 0026
	 * @version 1.0
	 */
	protected Map<String, Object> getData() {
		return RestHelper.getData(request);
	}

	/**
	 * 校验请求参数
	 *
	 * @param cls
	 *            class对象
	 * @return 返回值不为空即为校验失败
	 */
	protected String checkClass(Class cls) {
		return RestHelper.checkClass(request, cls);
	}

	/**
	 * 获取请求参数
	 *
	 * @return T
	 */
	protected <E> E getData(Class<E> cls) {
		return RestHelper.getData(request, cls);
	}

	/**
	 * 获取请求参数
	 *
	 * @return
	 */
	protected <E> E getParams() {
		return RestHelper.getParams(request);
	}

	/**
	 * 获取一个新的RestResult
	 *
	 * @return
	 */
	protected RestResult restMap() {
		return RestHelper.restMap();
	}

	/**
	 * 获取一个新的RestResult
	 *
	 * @return
	 */
	protected RestResult restList() {
		return RestHelper.restList();
	}
}
