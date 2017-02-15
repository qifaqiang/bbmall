package com.wxsoft.framework.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient {

	private static Jedis jedis;// 非切片额客户端连接
	private static JedisPool jedisPool;// 非切片连接池
	private static ShardedJedis shardedJedis;// 切片额客户端连接
	private static ShardedJedisPool shardedJedisPool;// 切片连接池
	private static String url = "";
	private static String auth = "";

	static {
		initialPool();
		initialShardedPool();
		shardedJedis = shardedJedisPool.getResource();
		jedis = jedisPool.getResource();

	}

	/**
	 * 初始化非切片池
	 */
	private static void initialPool() {

		Properties prop = new Properties();
		InputStream in;
		try {
			String os = System.getProperty("os.name");
			if (os.equals("Linux")) {// linux
				in = new BufferedInputStream(
						new FileInputStream(
								"/mnt/tomcat6/webapps/tddpay/WEB-INF/classes/config/dbconnect.properties"));
			} else {// windows
				in = new BufferedInputStream(
						new FileInputStream(
								System.getProperty("user.dir").replace("bin",
										"webapps")
										+ "/tddpay/WEB-INF/classes/config/dbconnect.properties"));
			}

			prop.load(in);
			url = prop.getProperty("jRedis.url").trim();
			auth = prop.getProperty("jRedis.auth").trim();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000l);
		config.setTestOnBorrow(false);
		jedisPool = new JedisPool(config, url, 6379, 1000, auth);
	}

	/**
	 * 初始化切片池
	 */
	private static void initialShardedPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000l);
		config.setTestOnBorrow(false);
		// slave链接
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo(url, 6379, "master"));

		// 构造池
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}

	public void show() {
		StringOperate();
		jedisPool.returnResource(jedis);
		shardedJedisPool.returnResource(shardedJedis);
	}

	public static void main(String[] args) {
		setKey("11", "111");
	}

	public String getOrderCode() {
		String tempSetting = jedis.get("tddpay_order_code");
		return tempSetting;
	}

	public void SetOrderCode(String code) {
		jedis.set("tddpay_order_code", code);
	}

//	public static TddSetting getSetting() {
//		TddSetting user = null;
//		Long timeNow = System.currentTimeMillis();
//		if (timeNow - BaseConfig.timeStamp > 600000) {
//			String tempSetting = jedis.get("setting");
//			try {
//				user = (TddSetting) JsonLibUtils.json2pojo(tempSetting,
//						TddSetting.class);
//				BaseConfig.tddSetting = user;
//				BaseConfig.timeStamp = timeNow;
//			} catch (Exception e1) {
//
//			}
//
//		} else {
//			user = BaseConfig.tddSetting;
//		}
//		if (null == user || user.equals("")) {
//			BaseConfig.refreshCacheTDDSETTING();
//			return BaseConfig.getTddSetting();
//		}
//		return user;
//
//	}

	public static String getKey(String key) {
		return jedis.get(key);
	}

	public static String setKey(String key, String value) {
		String result = jedis.set(key, value);
		jedis.expire(key, 15);
		return result;

	}

	public static String delKey(String key) {
		jedis.del(key);
		return key;
	}

	//
	// public TddMember getTddMember(String cookie) {
	// TddMember tm = new TddMember();
	// String userName = jedis.get("user_name" + cookie);
	// String userId = jedis.get("user_id" + cookie);
	// String userType = jedis.get("user_type" + cookie);
	// if (null != userName && !userName.equals("")) {
	// tm.setUserName(userName.replaceAll("\"", ""));
	// tm.setUserId(Long.parseLong(userId.replaceAll("\"", "")));
	// tm.setType(Integer.parseInt(userType.replaceAll("\"", "")));
	// } else {
	// return null;
	// }
	// return tm;
	// }

	private void StringOperate() {
		System.out
				.println("======================String_1==========================");

		System.out.println("=============增=============");
		jedis.set("key001", "value001");
		jedis.set("key002", "value002");
		jedis.set("key003", "value003");
		System.out.println("已新增的3个键值对如下：");
		System.out.println(jedis.get("key001"));
		System.out.println(jedis.get("key002"));
		System.out.println(jedis.get("key003"));

		System.out.println("=============删=============");
		System.out.println("删除key003键值对：" + jedis.del("key003"));
		System.out.println("获取key003键对应的值：" + jedis.get("key003"));

		System.out.println("=============改=============");
		// 1、直接覆盖原来的数据
		System.out.println("直接覆盖key001原来的数据："
				+ jedis.set("key001", "value001-update"));
		System.out.println("获取key001对应的新值：" + jedis.get("key001"));
		// 2、直接覆盖原来的数据
		System.out.println("在key002原来值后面追加："
				+ jedis.append("key002", "+appendString"));
		System.out.println("获取key002对应的新值" + jedis.get("key002"));

		System.out.println("=============增，删，查（多个）=============");
		/**
		 * mset,mget同时新增，修改，查询多个键值对 等价于： jedis.set("name","ssss");
		 * jedis.set("jarorwar","xxxx");
		 */
		System.out.println("一次性新增key201,key202,key203,key204及其对应值："
				+ jedis.mset("key201", "value201", "key202", "value202",
						"key203", "value203", "key204", "value204"));
		System.out.println("一次性获取key201,key202,key203,key204各自对应的值："
				+ jedis.mget("key201", "key202", "key203", "key204"));
		System.out.println("一次性删除key201,key202："
				+ jedis.del(new String[] { "key201", "key202" }));
		System.out.println("一次性获取key201,key202,key203,key204各自对应的值："
				+ jedis.mget("key201", "key202", "key203", "key204"));
		System.out.println();

	}

}