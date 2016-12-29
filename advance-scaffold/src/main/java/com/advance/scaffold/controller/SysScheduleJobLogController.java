package com.advance.scaffold.controller;

import com.advance.scaffold.core.controller.ConsoleController;
import com.advance.scaffold.model.SysScheduleJobLog;
import com.advance.scaffold.service.SysScheduleJobLogService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 定时任务日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:39:52
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class SysScheduleJobLogController extends ConsoleController{
	@Autowired
	private SysScheduleJobLogService sysScheduleJobLogService;
	
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	public Object list(@RequestParam("jobId") Integer jobId){
		//查询列表数据
		Page<SysScheduleJobLog> page = sysScheduleJobLogService.selectPage(getPage(), Condition.instance().eq("id", jobId));
		return  null;
	}
	
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public Object info(@PathVariable("logId") Long logId){
		SysScheduleJobLog log = sysScheduleJobLogService.selectById(logId);
		return  null;

	}
}
