package com.advance.scaffold.core.model;

import com.app.common.converter.BeanConverter;
import com.app.common.plugin.mybatisplus.primarykey.AutoPrimaryKey;
import com.fasterxml.jackson.annotation.JsonFilter;

import java.io.Serializable;

/**
 * Created by Caratacus on 2016/8/1 0001.
 */
@JsonFilter("objFilter")
public class AutoBaseModel extends AutoPrimaryKey implements Serializable {
	/**
	 * 获取自动转换后的JavaBean对象
	 *
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public <T> T getTargetObject(Class<T> clazz) {
		try {
			T t = clazz.newInstance();
			return BeanConverter.convert(t, this);
		} catch (Exception e) {
			throw new RuntimeException("Error: Unexpected exception on getTargetObject", e);
		}
	}
}
