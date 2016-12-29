package com.advance.scaffold.service.impl;

import com.advance.scaffold.mapper.SysScheduleJobMapper;
import com.advance.scaffold.model.SysScheduleJob;
import com.advance.scaffold.quartz.ScheduleStatus;
import com.advance.scaffold.quartz.ScheduleUtils;
import com.advance.scaffold.service.SysScheduleJobService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 * @author Caratacus
 * @since 2016-12-29
 */
@Service
public class SysScheduleJobServiceImpl extends ServiceImpl<SysScheduleJobMapper, SysScheduleJob> implements SysScheduleJobService {
	@Autowired
	private Scheduler scheduler;

	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void initScheduleJob() {
		List<SysScheduleJob> scheduleJobList = selectList(Condition.Empty());
		for (SysScheduleJob scheduleJob : scheduleJobList) {
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
			// 如果不存在，则创建
			if (cronTrigger == null) {
				ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
			} else {
				ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
			}
		}
	}

	@Override
	public void saveScheduleJob(SysScheduleJob scheduleJob) {
		scheduleJob.setCreateTime(new Date());
		scheduleJob.setStatus(ScheduleStatus.NORMAL.getValue());
		insert(scheduleJob);
		ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
	}

	@Override
	public void updateScheduleJob(SysScheduleJob scheduleJob) {
		ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
		updateById(scheduleJob);
	}

	@Override
	public void deleteBatchScheduleJob(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.deleteScheduleJob(scheduler, jobId);
		}
		// 删除数据
		deleteBatchIds(Arrays.asList(jobIds));
	}

	@Override
	public int updateBatchScheduleJob(Long[] jobIds, int status) {
		List<SysScheduleJob> scheduleJobs = selectList(Condition.instance().in("id", jobIds));
		for (SysScheduleJob scheduleJob : scheduleJobs) {
			scheduleJob.setStatus(status);
		}
		updateBatchById(scheduleJobs);
		// TODO
		return 1;
	}

	@Override
	public void run(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.run(scheduler, selectById(jobId));
		}
	}

	@Override
	public void pause(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.pauseJob(scheduler, jobId);
		}
		updateBatchScheduleJob(jobIds, ScheduleStatus.PAUSE.getValue());
	}

	@Override
	public void resume(Long[] jobIds) {
		for (Long jobId : jobIds) {
			ScheduleUtils.resumeJob(scheduler, jobId);
		}
		updateBatchScheduleJob(jobIds, ScheduleStatus.NORMAL.getValue());
	}
}
