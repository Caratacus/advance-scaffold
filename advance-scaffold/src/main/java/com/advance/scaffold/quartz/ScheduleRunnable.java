package com.advance.scaffold.quartz;

import com.app.common.StringUtils;
import com.app.common.spring.ApplicationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * <p>
 * 执行定时任务
 * </p>
 *
 * @author Caratacus
 * @since 2016-12-29
 */
public class ScheduleRunnable implements Runnable {

	private Object target;
	private Method method;
	private String params;

	public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
		this.target = ApplicationUtils.getBean(beanName);
		this.params = params;

		if (StringUtils.isNotBlank(params)) {
			this.method = target.getClass().getDeclaredMethod(methodName, String.class);
		} else {
			this.method = target.getClass().getDeclaredMethod(methodName);
		}
	}

	@Override
	public void run() {
		try {
			ReflectionUtils.makeAccessible(method);
			if (StringUtils.isNotBlank(params)) {
				method.invoke(target, params);
			} else {
				method.invoke(target);
			}
		} catch (Exception e) {
			throw new RuntimeException("执行定时任务失败", e);
		}
	}

}
