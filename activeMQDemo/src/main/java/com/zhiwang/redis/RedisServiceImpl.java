package com.zhiwang.redis;

import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * 封装redis 缓存服务器服务接口
 * 
 */
@Component
public class RedisServiceImpl implements RedisService {
	
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static String redisCode = "utf-8";

    /**
     * @param key
     */
	public long del(final String... keys) {
        return (long)redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (int i = 0; i < keys.length; i++) {
                    result = connection.del(keys[i].getBytes());
                }
                return result;
            }
        });
    }

    /**
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

    /**
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key, String value, long liveTime) {
        this.set(key.getBytes(), value.getBytes(), liveTime);
    }

    /**
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        this.set(key, value, 0L);
    }

    /**
     * @param key
     * @param value
     */
    public void set(byte[] key, byte[] value) {
        this.set(key, value, 0L);
    }
    
    
    public void exipre(final String key, final long liveTime) {
        redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                if (liveTime > 0) {
                    connection.expire(key.getBytes(), liveTime);
                }
                return 1L;
            }
        });
    }

    /**
     * @param key
     * @return
     */
    public String get(final String key) {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                	if(key == null){
                		return null;
                	}
                	byte[] value = connection.get(key.getBytes());
                    return value == null ? null : new String(connection.get(key.getBytes()), redisCode);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
	
	/**
	 * 设置一个对象 可以是string 
	 * @param key
	 * @param obj
	 * @param clazz 
	 * @param seconds 超时时间 0 代表永不过期
	 */
	public <T> void set(final String key,final T obj,final long seconds){
		ValueOperations<String, T> valueOper = (ValueOperations<String, T>) redisTemplate.opsForValue(); 
		valueOper.set(key, obj);
		redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
	}
	
	/**
	 * 设置一个对象 可以是string 
	 * @param key
	 * @param obj
	 * @param clazz 
	 */
	public <T> T get(final String key,Class<T> clazz){
		ValueOperations<String, T> valueOper = (ValueOperations<String, T>) redisTemplate.opsForValue(); 
		return valueOper.get(key);
	}
	
	/**
	 * 设置一个对象 可以是string 
	 */
	public <T> void set(final String key,final T obj){
		ValueOperations<String, T> valueOper = (ValueOperations<String, T>) redisTemplate.opsForValue(); 
		valueOper.set(key, obj);
	}
 

    /**
     * @param pattern
     * @return
     */
    public Set keys(String pattern) {
        return redisTemplate.keys(pattern);

    }

    /**
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return (boolean)redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }

    /**
     * @return
     */
    public String flushDB() {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    /**
     * @return
     */
    public long dbSize() {
        return (long)redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /**
     * @return
     */
    public String ping() {
        return (String)redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {

                return connection.ping();
            }
        });
    }

    private RedisServiceImpl() {

    }
    



}
