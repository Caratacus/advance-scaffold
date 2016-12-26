package com.advance.scaffold.service.impl;

import com.advance.scaffold.service.RedisService;
import com.app.common.ByteUtils;
import com.app.common.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 封装部分Redis通用方法
 *
 * @author Caratacus
 * @time 2016年08月8日 02:18:34
 */
@Service
public class RedisServiceImpl implements RedisService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static String redisCode = "utf-8";

	private final static String redisSuccess = "PONG";

	@Resource(name = "redisTemplate")
	private RedisOperations redisTemplate;

	@Override
	public void setRedisInfo(final String key, final Map<String, Object> infoMap) {
		setRedisInfo(key, infoMap, 0L);
	}

	@Override
	public Map<String, Object> getRedisInfo(final String key) {
		if (!redisSuccess.equalsIgnoreCase(ping())) {
			return Collections.emptyMap();
		}
		return (Map<String, Object>) redisTemplate.execute(new RedisCallback<Map<String, Object>>() {
			public Map<String, Object> doInRedis(RedisConnection connection) throws DataAccessException {
				BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(key);
				Map<String, Object> data = boundHashOperations.entries();
				return data;
			}
		});
	}

	@Override
	public void setRedisInfo(final String key, final Map<String, Object> infoMap, final long liveTime) {
		if (!redisSuccess.equalsIgnoreCase(ping())) {
			return;
		}
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(key);
				boundHashOperations.putAll(infoMap);
				if (liveTime > 0) {
					boundHashOperations.expire(liveTime, TimeUnit.SECONDS);
				}
				return null;
			}
		});
	}

	@Override
	public void clearInfo(String key) {
		if (!redisSuccess.equalsIgnoreCase(ping())) {
			return;
		}
		Set<String> keys = redisTemplate.keys(key);
		if (!keys.isEmpty()) {
			redisTemplate.delete(key);
		}
	}

	@Override
	public Long clear(final String... keys) {
		if (!redisSuccess.equalsIgnoreCase(ping())) {
			return 0L;
		}
		return (Long) redisTemplate.execute(new RedisCallback() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				long result = 0;
				for (int i = 0; i < keys.length; i++) {
					result = connection.del(keys[i].getBytes());
				}
				return result;
			}
		});
	}

	@Override
	public Set<String> getAllkeys() {
		if (!redisSuccess.equalsIgnoreCase(ping())) {
			return Collections.emptySet();
		}
		Set<String> keys = redisTemplate.keys("*");
		return keys;
	}

	@Override
	public void set(final byte[] key, final byte[] value, final long liveTime) {
		if (!redisSuccess.equalsIgnoreCase(ping())) {
			return;
		}
		redisTemplate.execute(new RedisCallback() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(key, value);
				if (liveTime > 0) {
					connection.expire(key, liveTime);
				}
				return null;
			}
		});
	}

	@Override
	public void set(final String key, final String value, long liveTime) {
		this.set(key.getBytes(), value.getBytes(), liveTime);
	}

	@Override
	public void set(final String key, final String value) {
		this.set(key, value, 0L);
	}

	@Override
	public void set(final byte[] key, byte[] value) {
		this.set(key, value, 0L);
	}

	@Override
	public void set(Object key, Object value, long liveTime) {
		this.set(ByteUtils.toByte(key), ByteUtils.toByte(value), liveTime);
	}

	@Override
	public void set(Object key, Object value) {
		this.set(key, value, 0L);
	}

	@Override
	public byte[] get(final byte[] key) {
		if (!redisSuccess.equalsIgnoreCase(ping())) {
			return new byte[] {};
		}
		return (byte[]) redisTemplate.execute(new RedisCallback<byte[]>() {
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.get(key);
			}
		});
	}

	@Override
	public <T> T get(Object key, Class<T> clazz) {
		if (!redisSuccess.equalsIgnoreCase(ping())) {
			return null;
		}
		byte[] bytes = get(ByteUtils.toByte(key));
		T t = ByteUtils.toEntity(bytes, clazz);
		return t;
	}

	@Override
	public String get(final String key) {
		if (!redisSuccess.equalsIgnoreCase(ping())) {
			return null;
		}
		byte[] bytes = get(key.getBytes());
		try {
			return new String(bytes, redisCode);
		} catch (UnsupportedEncodingException e) {
			logger.error(Common.method(), e);
		}
		return null;
	}

	@Override
	public Set<String> setkeys(final String pattern) {
		if (!redisSuccess.equalsIgnoreCase(ping())) {
			return Collections.emptySet();
		}
		return redisTemplate.keys(pattern);
	}

	@Override
	public Boolean exists(final String key) {
		if (!redisSuccess.equalsIgnoreCase(ping())) {
			return false;
		}
		return (Boolean) redisTemplate.execute(new RedisCallback() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.exists(key.getBytes());
			}
		});
	}

	@Override
	public void flushRedis() {
		if (!redisSuccess.equalsIgnoreCase(ping())) {
			return;
		}
		redisTemplate.execute(new RedisCallback() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return null;
			}
		});
	}

	@Override
	public Long redisSize() {
		if (!redisSuccess.equalsIgnoreCase(ping())) {
			return 0L;
		}
		return (Long) redisTemplate.execute(new RedisCallback() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.dbSize();
			}
		});

	}

	@Override
	public String ping() {
		String res = null;
		try {
			res = (String) redisTemplate.execute(new RedisCallback() {
				public String doInRedis(RedisConnection connection) throws DataAccessException {
					return connection.ping();
				}
			});
		} catch (Exception e) {
			res = e.getMessage();
			logger.error(Common.method(), " Could not get a resource from the pool ! Please check connection ! ");
		}
		return res;
	}
}
