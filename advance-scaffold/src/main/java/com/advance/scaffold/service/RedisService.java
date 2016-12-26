package com.advance.scaffold.service;

import java.util.Map;
import java.util.Set;

/**
 * 封装部分Redis通用方法
 * 
 * @author Caratacus
 * @time 2016年08月8日 02:18:34
 */
public interface RedisService {
	/**
	 * 向redis添加 相关信息
	 *
	 * @param infoMap
	 *            相关信息
	 * @param key
	 * @author Caratacus
	 * @date 2016/8/7 0014
	 * @version 1.0
	 */
	public void setRedisInfo(String key, Map<String, Object> infoMap);

	/**
	 * 获取 redis 相关信息
	 *
	 * @param key
	 * @return Map<String, Object>
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0014
	 * @version 1.0
	 */
	public Map<String, Object> getRedisInfo(String key);

	/**
	 * 向redis添加 相关信息
	 *
	 * @param infoMap
	 *            相关信息
	 * @param key
	 * @param liveTime
	 * @author Caratacus
	 * @date 2016/8/7 0014
	 * @version 1.0
	 */
	public void setRedisInfo(String key, Map<String, Object> infoMap, long liveTime);

	/**
	 * 从redis删除信息(BoundHashOperations)
	 * <p>
	 * 正则匹配删除
	 * <p/>
	 *
	 * @param keys
	 * @author Caratacus
	 * @date 2016/8/7 0016
	 * @version 1.0
	 */
	public void clearInfo(String keys);

	/**
	 * 从redis删除信息(RedisConnection)
	 *
	 * @param keys
	 * @author Caratacus
	 * @date 2016/8/7 0016
	 * @version 1.0
	 */
	public Long clear(String... keys);

	/**
	 * 从redis中获取所有的key
	 *
	 * @return Set<String>
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public Set<String> getAllkeys();

	/**
	 * 添加key value 并且设置存活时间(byte)
	 *
	 * @param key
	 * @param value
	 * @param liveTime
	 *            单位秒
	 * @return
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public void set(byte[] key, byte[] value, long liveTime);

	/**
	 * 添加key value 并且设置存活时间
	 *
	 * @param key
	 * @param value
	 * @param liveTime
	 *            单位秒
	 * @return
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public void set(String key, String value, long liveTime);

	/**
	 * 添加key value
	 *
	 * @param key
	 * @param value
	 * @return
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public void set(String key, String value);

	/**
	 * 添加key value (字节)(序列化)
	 *
	 * @param key
	 * @param value
	 * @return
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public void set(byte[] key, byte[] value);

	/**
	 * 添加key value 并且设置存活时间
	 *
	 * @param key
	 * @param value
	 * @param liveTime
	 *            单位秒
	 * @return
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public void set(Object key, Object value, long liveTime);

	/**
	 * 添加key value (obj)
	 *
	 * @param key
	 *            建议为String
	 * @param value
	 * @return
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public void set(Object key, Object value);

	/**
	 * 获取redis value (String)
	 *
	 * @param key
	 * @return String
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public String get(String key);

	/**
	 * 获取redis value (byte[])
	 *
	 * @param key
	 * @return String
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public byte[] get(byte[] key);

	/**
	 * 获取redis value (obj)
	 *
	 * @param key
	 * @return String
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public <T> T get(Object key, Class<T> clazz);

	/**
	 * 通过正则匹配keys
	 *
	 * @param pattern
	 * @return Set<String>
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public Set<String> setkeys(String pattern);

	/**
	 * 检查key是否已经存在
	 *
	 * @param key
	 * @return Boolean
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public Boolean exists(String key);

	/**
	 * 清空redis 所有数据
	 *
	 * @return
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public void flushRedis();

	/**
	 * 查看redis里有多少数据
	 *
	 * @return Long
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public Long redisSize();

	/**
	 * 检查是否连接成功
	 * <p>
	 * 客户端和服务器连接正常，返回[PONG]
	 * <p/>
	 * <p>
	 * 客户端和服务器连接不正常，返回[Could not get a resource from the pool]
	 * <p/>
	 *
	 * @return String
	 * @throws
	 * @author Caratacus
	 * @date 2016/8/7 0007
	 * @version 1.0
	 */
	public String ping();
}
