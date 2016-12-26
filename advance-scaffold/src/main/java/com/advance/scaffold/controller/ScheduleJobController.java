package com.advance.scaffold.controller;

import com.advance.scaffold.model.ScheduleJobEntity;
import com.advance.scaffold.service.ScheduleJobService;
import com.app.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 定时任务
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月28日 下午2:16:40
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;
	
	/**
	 * 定时任务列表
	 */
	@RequestMapping("/list")
	public Object list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<ScheduleJobEntity> jobList = scheduleJobService.queryList(map);
		int total = scheduleJobService.queryTotal(map);
		
		//PageUtils pageUtil = new PageUtils(jobList, total, limit, page);
		
		//return R.ok().put("page", pageUtil);
		return null;
	}
	
	/**
	 * 定时任务信息
	 */
	@RequestMapping("/info/{jobId}")
	public Object info(@PathVariable("jobId") Long jobId){
		ScheduleJobEntity schedule = scheduleJobService.queryObject(jobId);
		
		//return R.ok().put("schedule", schedule);
		return null;

	}
	
	/**
	 * 保存定时任务
	 */
	@RequestMapping("/save")
	public Object save(@RequestBody ScheduleJobEntity scheduleJob){
		//数据校验
		verifyForm(scheduleJob);
		
		scheduleJobService.save(scheduleJob);
		
		//return R.ok();
		return null;

	}
	
	/**
	 * 修改定时任务
	 */
	@RequestMapping("/update")
	public Object update(@RequestBody ScheduleJobEntity scheduleJob){
		//数据校验
		verifyForm(scheduleJob);
				
		scheduleJobService.update(scheduleJob);
		return null;

		//return R.ok();
	}
	
	/**
	 * 删除定时任务
	 */
	@RequestMapping("/delete")
	public Object delete(@RequestBody Long[] jobIds){
		scheduleJobService.deleteBatch(jobIds);
		
		//return R.ok();
		return null;

	}
	
	/**
	 * 立即执行任务
	 */
	@RequestMapping("/run")
	public Object run(@RequestBody Long[] jobIds){
		scheduleJobService.run(jobIds);
		
		//return R.ok();
		return null;

	}
	
	/**
	 * 暂停定时任务
	 */
	@RequestMapping("/pause")
	public Object pause(@RequestBody Long[] jobIds){
		scheduleJobService.pause(jobIds);
		
		//return R.ok();
		return null;

	}
	
	/**
	 * 恢复定时任务
	 */
	@RequestMapping("/resume")
	public Object resume(@RequestBody Long[] jobIds){
		scheduleJobService.resume(jobIds);
		
		//return R.ok();
		return null;
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(ScheduleJobEntity scheduleJob){
		if(StringUtils.isBlank(scheduleJob.getBeanName())){
			throw new RuntimeException("bean名称不能为空");
		}
		
		if(StringUtils.isBlank(scheduleJob.getMethodName())){
			throw new RuntimeException("方法名称不能为空");
		}
		
		if(StringUtils.isBlank(scheduleJob.getCronExpression())){
			throw new RuntimeException("cron表达式不能为空");
		}
	}
}
