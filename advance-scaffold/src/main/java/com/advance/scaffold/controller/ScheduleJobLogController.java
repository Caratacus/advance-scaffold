package com.advance.scaffold.controller;

import com.advance.scaffold.model.ScheduleJobLogEntity;
import com.advance.scaffold.service.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 定时任务日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:39:52
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	public Object list(Integer page, Integer limit, Integer jobId){
		Map<String, Object> map = new HashMap<>();
		map.put("jobId", jobId);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<ScheduleJobLogEntity> jobList = scheduleJobLogService.queryList(map);
		int total = scheduleJobLogService.queryTotal(map);
		
		//PageUtils pageUtil = new PageUtils(jobList, total, limit, page);
		
		//return R.ok().put("page", pageUtil);
		return  null;
	}
	
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public Object info(@PathVariable("logId") Long logId){
		ScheduleJobLogEntity log = scheduleJobLogService.queryObject(logId);
		
		//return R.ok().put("log", log);
		return  null;

	}
}
