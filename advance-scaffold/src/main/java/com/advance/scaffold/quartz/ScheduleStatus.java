package com.advance.scaffold.quartz;

/**
 * <p>
 * 定时任务状态
 * </p>
 *
 * @author Caratacus
 * @since 2016-12-29
 */
public enum ScheduleStatus {
	/**
	 * 正常
	 */
	NORMAL(0),
	/**
	 * 暂停
	 */
	PAUSE(1);

	private int value;

	private ScheduleStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}