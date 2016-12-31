package com.advance.scaffold.quartz;

import com.advance.scaffold.model.SysScheduleJob;
import com.advance.scaffold.model.SysScheduleJobLog;
import com.advance.scaffold.service.SysScheduleJobLogService;
import com.app.common.SystemClock;
import com.app.common.TypeConvert;
import com.app.common.spring.ApplicationUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>
 * 定时任务
 * </p>
 *
 * @author Caratacus
 * @since 2016-12-29
 */
public class ScheduleJob extends QuartzJobBean {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private ExecutorService executorService = Executors.newSingleThreadExecutor();

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		SysScheduleJob scheduleJob = (SysScheduleJob) context.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);
		// 获取spring bean
		SysScheduleJobLogService sysScheduleJobLogService = ApplicationUtils.getBean("SysScheduleJobLogService");
		// 数据库保存执行记录
		SysScheduleJobLog scheduleJobLog = new SysScheduleJobLog();
		scheduleJobLog.setJobId(scheduleJob.getId());
		scheduleJobLog.setBeanName(scheduleJob.getBeanName());
		scheduleJobLog.setMethodName(scheduleJob.getMethodName());
		scheduleJobLog.setParams(scheduleJob.getParams());
		scheduleJobLog.setCreateTime(new Date());
		// 任务开始时间
		long startTime = SystemClock.now();
		try {
			// 执行任务
			logger.info("任务准备执行，任务ID：" + scheduleJob.getId());
			ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(),
					scheduleJob.getParams());
			Future<?> future = executorService.submit(task);
			future.get();
			// 任务执行总时长
			long times = SystemClock.now() - startTime;
			scheduleJobLog.setTimes(TypeConvert.toInt(times));
			// 任务状态 0：成功 1：失败
			scheduleJobLog.setStatus(0);
			logger.info("任务执行完毕，任务ID：" + scheduleJob.getId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getId(), e);
			// 任务执行总时长
			long times = SystemClock.now() - startTime;
			scheduleJobLog.setTimes(TypeConvert.toInt(times));
			// 任务状态 0：成功 1：失败
			scheduleJobLog.setStatus(1);
			scheduleJobLog.setError(e.toString());
		}
		sysScheduleJobLogService.insert(scheduleJobLog);
	}
}
