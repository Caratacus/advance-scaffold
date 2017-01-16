package com.advance.scaffold.core.constant;

/**
 * 错误代码常量类
 *
 * @author Caratacus
 * @version 1.0
 * @desc 错误代码结构说明
 *       <p>
 *       第一部分由x组成 固定
 *       </p>
 *       <p>
 *       第二部分由1位数字组成 模块划分 01 陈幸 02 董晨红
 *       </p>
 *       <p>
 *       第三部分由5位数字组成 模块名底下错误详情
 *       </p>
 * @date 2016/6/2 0002
 */
public enum ErrorCode {

	// 过滤器错误
	// Bad Request!
	x0000("x0000", "Bad Request!"),
	// Request Path Not Found!
	x0001("x0001", "Request Path Not Found!"),
	// Internal Server Error!
	x0002("x0002", "Internal Server Error!"),
	// Request Token Time out, Forbidden!
	x0003("x0003", "Request Token Time out, Forbidden!"),
	// Request Token Unauthorized, Forbidden!
	x0004("x0004", "Request Token Unauthorized, Forbidden!"),
	x10001("x10001","用户名密码错误!"),
	x10002("x10002","用户已停用!"),
	x10003("x10003","登录异常!"),
	;
	private String code;
	private String desc;

	ErrorCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String code() {
		return this.code;
	}

	public String desc() {
		return this.desc;
	}
}
