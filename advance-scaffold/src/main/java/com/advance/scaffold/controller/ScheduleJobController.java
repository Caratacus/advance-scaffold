package com.advance.scaffold.controller;

import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advance.scaffold.core.controller.ConsoleController;
import com.advance.scaffold.model.SysScheduleJob;
import com.advance.scaffold.service.SysScheduleJobService;
import com.app.common.StringUtils;
import com.baomidou.mybatisplus.mapper.Condition;

/**
 * <p>
 * 定时任务ScheduleJobController
 * </p>
 *
 * @author Caratacus
 * @since 2016-12-29
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController extends ConsoleController {
	@Autowired
	private SysScheduleJobService sysScheduleJobService;

	/**
	 * 定时任务列表
	 */
	@RequestMapping("/list")
	public Object list() {
		// 查询列表数据
		Page<SysScheduleJob> page = sysScheduleJobService.selectPage(getPage(), Condition.Empty());
		return null;
	}

	/**
	 * 定时任务信息
	 */
	@RequestMapping("/info/{jobId}")
	public Object info(@PathVariable("jobId") Long jobId) {
		SysScheduleJob schedule = sysScheduleJobService.selectById(jobId);
		return null;

	}

	/**
	 * 保存定时任务
	 */
	@RequestMapping("/save")
	public Object save(@RequestBody SysScheduleJob scheduleJob) {
		// 数据校验
		verifyForm(scheduleJob);
		sysScheduleJobService.saveScheduleJob(scheduleJob);
		return null;

	}

	/**
	 * 修改定时任务
	 */
	@RequestMapping("/update")
	public Object update(@RequestBody SysScheduleJob scheduleJob) {
		// 数据校验
		verifyForm(scheduleJob);
		sysScheduleJobService.updateScheduleJob(scheduleJob);
		return null;
	}

	/**
	 * 删除定时任务
	 */
	@RequestMapping("/delete")
	public Object delete(@RequestBody Long[] jobIds) {
		sysScheduleJobService.deleteBatchScheduleJob(jobIds);
		return null;
	}

	/**
	 * 立即执行任务
	 */
	@RequestMapping("/run")
	public Object run(@RequestBody Long[] jobIds) {
		sysScheduleJobService.run(jobIds);
		return null;

	}

	/**
	 * 暂停定时任务
	 */
	@RequestMapping("/pause")
	public Object pause(@RequestBody Long[] jobIds) {
		sysScheduleJobService.pause(jobIds);
		return null;

	}

	/**
	 * 恢复定时任务
	 */
	@RequestMapping("/resume")
	public Object resume(@RequestBody Long[] jobIds) {
		sysScheduleJobService.resume(jobIds);
		return null;
	}

	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysScheduleJob scheduleJob) {
		if (StringUtils.isBlank(scheduleJob.getBeanName())) {
			throw new RuntimeException("bean名称不能为空");
		}

		if (StringUtils.isBlank(scheduleJob.getMethodName())) {
			throw new RuntimeException("方法名称不能为空");
		}

		if (StringUtils.isBlank(scheduleJob.getCronExpression())) {
			throw new RuntimeException("cron表达式不能为空");
		}
	}
}
