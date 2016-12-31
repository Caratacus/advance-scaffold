package com.advance.scaffold.controller;

import com.advance.scaffold.core.controller.ConsoleController;
import com.advance.scaffold.model.SysScheduleJob;
import com.advance.scaffold.service.SysScheduleJobService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
public class SysScheduleJobController extends ConsoleController {
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
		sysScheduleJobService.insertJob(scheduleJob);
		return null;

	}

	/**
	 * 修改定时任务
	 */
	@RequestMapping("/update")
	public Object update(@RequestBody SysScheduleJob scheduleJob) {
		sysScheduleJobService.updateJob(scheduleJob);
		return null;
	}

	/**
	 * 删除定时任务
	 */
	@RequestMapping("/delete")
	public Object delete(@RequestBody List<Long> jobIds) {
		sysScheduleJobService.deleteBatchJob(jobIds);
		return null;
	}

	/**
	 * 立即执行任务
	 */
	@RequestMapping("/run")
	public Object run(@RequestBody List<Long> jobIds) {
		sysScheduleJobService.runJob(jobIds);
		return null;

	}

	/**
	 * 暂停定时任务
	 */
	@RequestMapping("/pause")
	public Object pause(@RequestBody List<Long> jobIds) {
		sysScheduleJobService.pauseJob(jobIds);
		return null;

	}

	/**
	 * 恢复定时任务
	 */
	@RequestMapping("/resume")
	public Object resume(@RequestBody List<Long> jobIds) {
		sysScheduleJobService.resumeJob(jobIds);
		return null;
	}
}
