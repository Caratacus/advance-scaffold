package com.advance.scaffold.model;

import com.advance.scaffold.core.model.IdWorkBaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
/**
 * <p>
 * 定时任务日志
 * </p>
 *
 * @author Caratacus
 * @since 2016-12-29
 */
@TableName("sys_schedule_job_log")
public class SysScheduleJobLog extends IdWorkBaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 任务id
	 */
	@TableField(value="job_id")
	private Long jobId;
	/**
	 * spring bean名称
	 */
	@TableField(value="bean_name")
	private String beanName;
	/**
	 * 方法名
	 */
	@TableField(value="method_name")
	private String methodName;
	/**
	 * 参数
	 */
	private String params;
	/**
	 * 任务状态    0：成功    1：失败
	 */
	private Integer status;
	/**
	 * 失败信息
	 */
	private String error;
	/**
	 * 耗时(单位：毫秒)
	 */
	private Integer times;
	/**
	 * 创建时间
	 */
	@TableField(value="create_time")
	private java.util.Date createTime;

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

}
