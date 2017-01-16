package com.advance.scaffold.core.common;

import com.advance.scaffold.core.constant.ErrorCode;
import com.app.common.Common;
import com.app.common.ObjectUtils;
import com.app.common.base.model.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>
 * REST帮助类
 * </p>
 *
 * @author Caratacus
 * @Date 2016-11-11
 */
public class RestUtils {

	private static Logger logger = LoggerFactory.getLogger(RestUtils.class);

	/**
	 * 获得本机IP
	 *
	 * @return
	 */
	public static String getLocalIp() {
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			return addr.getHostAddress().toString();
		} catch (UnknownHostException e) {
			logger.error(Common.method(), e);
		}
		return null;
	}

	/**
	 * 错误返回
	 *
	 * @param response
	 * @param status
	 * @param errorCode
	 * @param exception
	 * @param isShow
	 *            是否展示
	 * @return RestWrong
	 */
	public static RestResult failResult(HttpServletResponse response, HttpStatus status, ErrorCode errorCode,
                                        Exception exception, Boolean isShow) {
		//response.setStatus(status.value());
		String code = errorCode.code();
		RestResult restWrong = new RestResult();
		restWrong.setError(code);
		restWrong.setMsg(errorCode.desc());
		restWrong.setException(ObjectUtils.isEmpty(exception) ? null : exception.toString());
		restWrong.setShow(isShow);
		restWrong.setStatus(status.value());
		restWrong.setTimestamp(System.currentTimeMillis());
		return restWrong;
	}

	/**
	 * 错误返回
	 *
	 * @param response
	 * @param status
	 * @param error
	 * @param isShow
	 *            是否展示
	 * @return RestWrong
	 */
	public static RestResult failResult(HttpServletResponse response, HttpStatus status, ErrorCode error, Boolean isShow) {
		return failResult(response, status, error, null, isShow);
	}

}
