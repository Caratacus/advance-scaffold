package com.advance.scaffold.model;

import com.advance.scaffold.core.model.IdWorkBaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author Caratacus
 * @since 2016-12-29
 */
@TableName("sys_schedule_job")
public class SysScheduleJob extends IdWorkBaseModel {

    private static final long serialVersionUID = 1L;

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
	 * cron表达式
	 */
	@TableField(value="cron_expression")
	private String cronExpression;
	/**
	 * 任务状态
	 */
	private Integer status;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	@TableField(value="create_time")
	private java.util.Date createTime;

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

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

}
