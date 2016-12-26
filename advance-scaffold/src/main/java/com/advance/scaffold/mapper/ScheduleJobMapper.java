package com.advance.scaffold.mapper;

import com.advance.scaffold.BaseDao;
import com.advance.scaffold.model.ScheduleJobEntity;

import java.util.Map;


/**
 * 定时任务
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:29:57
 */
public interface ScheduleJobMapper extends BaseDao<ScheduleJobEntity> {
	
	/**
	 * 批量更新状态
	 */
	int updateBatch(Map<String, Object> map);
}
