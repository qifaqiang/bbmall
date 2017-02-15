package com.wxsoft.framework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.wxsoft.framework.bean.SqlExecuteBean;
import com.wxsoft.framework.util.JdbcUtils;

/**
 * @类功能说明：初始化IBATIS句柄
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @公司名称：wxlt
 * @作者：kasiait
 * @邮箱：kasiaits@gmail.com
 * @创建时间：2013-2-5 上午10:06:58
 */
//public class BaseDao extends SqlMapClientDaoSupport {
public class BaseDao {
	/**
	 * IBATIS对象

	@Resource(name = "sqlMapClient")
	private SqlMapClient sqlMapClient;
    */
	/**
	 * 获取JdbcTemplate对象
	 * 对于项目使用了Hibernate二级缓存的时候，
	 * 尽量使用jdbcTemplate进行查询类的操作
	 * 避免破坏了Hibernate的二级缓存体系
    */
	@Resource(name = "jdbcTemplate")
	protected JdbcTemplate jdbcTemplate;
	
	/*
	@PostConstruct
	public void initSqlMapClient() {
		super.setSqlMapClient(sqlMapClient);
	}
	 */
	/**
	 * 获取JDBC对象
	 * @return
    */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
	 
    /**
     * 设置注入方式
     * @param jdbcTemplate
    */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
         
    
    /***************************************************/
    /**
     * 提取分页列表对象
     * @param listKey
     * @param userPage 是否SQL自己分页
     * @return
     
    @SuppressWarnings("unchecked")
	protected JSONObject queryPageObject(String listKey , String countKey , Map<String , Object>map , boolean userPage) {
    	List list = queryPageList(listKey , map , userPage);
    	Integer count = (Integer)this.getSqlMapClientTemplate().queryForObject(countKey , map);
    	
    	return JSONUtil.getPageJSON(list, count);
    }
    */
    /**
     * 提取分页列表对象
     * @param listKey
     * @return
     
    protected JSONObject queryPageObject(String listKey , String countKey , Map<String , Object>map) {
    	return queryPageObject(listKey , countKey , map , false);
    }
    */
    /**
     * 获取分页列表数据
     * @param listKey ibatis配置文件中的 编号句柄
     * @param map 数据
     * @param userPage 是否SQL自己分页
     * @return
     
    @SuppressWarnings("unchecked")
	protected List queryPageList(String listSqlKey , Map<String , Object>map , boolean userPage) {
    	String start = (String)map.remove("start");
        String limit = (String)map.remove("limit");
        int startV = 0 , pageSize = 20;
    	if(userPage) {//用户自己分页
    		if(StringUtils.isNotBlank(start)) {
    			map.put("startRow", start);
    			startV = Integer.valueOf(start);
            }else {
            	map.put("startRow", startV);
            }
    		if(StringUtils.isNotBlank(limit)) {
    			map.put("endRow", startV + Integer.valueOf(limit));
    		}else {
    			map.put("endRow", startV + limit);
    		}
    		return this.getSqlMapClientTemplate().queryForList(listSqlKey , map);
    	}else {//使用通用分页
    		if(StringUtils.isNotBlank(start)) {
                startV = Integer.valueOf(start);
            }
            if(StringUtils.isNotBlank(limit)) {
                pageSize = Integer.valueOf(limit);
            }
            return this.getSqlMapClientTemplate().queryForList(listSqlKey, map, startV, pageSize);
    	}
    }
    */
    
    /**
     * 获取分页列表数据
     * @param listKey
     * @param map
     * @return
     
    @SuppressWarnings("unchecked")
	protected List queryPageList(String listKey , Map<String , Object>map) {
    	return queryPageList(listKey , map , false);
    }
    
    @SuppressWarnings("unchecked")
    public List selectListItatis(String itbatisName , Object obj) {
    	//表前缀
    	String TABLE_START = "";
    	if(obj instanceof Map) {
    		Map map = (Map)obj;
    		map.put("tableStart", TABLE_START);
    		String start = (String)map.get("start");
    		String limit = (String)map.get("limit");
    		if(StringUtils.isNotEmpty(start)) {
    			map.put("startRow", start);
    			if(StringUtils.isNotEmpty(limit)) {
    				map.put("endRow", String.valueOf(Integer.valueOf(start) + Integer.valueOf(limit)));
    			}
    		}
    	}
    	return this.getSqlMapClientTemplate().queryForList(itbatisName , obj);
    }
*/
    /**
     * 根据手动sql查询分页数据
     * @param <T>
     * @param sql
     * @param pageNo
     * @return

    public <T> PageBean<T>  queryListSql(String sql, Integer pageNo) {
    	return null;
    }
         */
    
    /**
     * appendSQL例子
     * @param sql
     * @param param
     * @param appendSql
     * @param params
     * @param isLike
   
    protected void appendSql(StringBuilder sql , Object param , String appendSql , List<Object>params , boolean isLike) {
        if(param == null) return;
        if(param instanceof String) {
        	String value = (String)param;
            if(StringUtils.isEmpty(value)) return;
        }
        if(isLike) param = "%" + param + "%";
        sql.append(appendSql);
        params.add(param);
    }
      */
    /**
     * 多次使用同一个参数
     * @param sql
     * @param param
     * @param appendSql
     * @param params
     * @param num
     
    protected void appendSql(StringBuilder sql , Object param , String appendSql , List<Object>params , int num) {
        if(param == null) return;
        if(param instanceof String) {
            if(StringUtils.isEmpty(String.valueOf(param))) return;
        }
        sql.append(appendSql);
        for(int i = 0 ; i < num ; i++) {
            params.add(param);
        }
    }
    */
    /**
     * 填充page的查询条件
     * @param map
     * @param params
     
    protected void appendPageParam(Map<String, String> map, List<Object> params) {
		params.add(Integer.valueOf(map.get("start")) + Integer.valueOf(map.get("limit")));
    	params.add(map.get("start"));
	}
*/
    /**
     * appendSQL例子
     * @param sql
     * @param param
     * @param appendSql
     * @param params
    
    protected void appendSql(StringBuilder sql , Object param , String appendSql , List<Object>params) {
        appendSql(sql , param , appendSql , params , false);
    }
     */
    /**
     * 多个批处理的SQL语句
     * @param sqlExecuteDOs
     */
    protected void executeBatchSql(SqlExecuteBean ... sqlExecuteDOs) {
        if(sqlExecuteDOs == null || sqlExecuteDOs.length == 0) {
            return;
        }
        Connection connection = null;
        PreparedStatement []preparedStatements = new PreparedStatement[sqlExecuteDOs.length];
        try {
            connection = jdbcTemplate.getDataSource().getConnection();
            connection.setAutoCommit(false);
            //预编译
            int index = 0;
            for(SqlExecuteBean sqlExecuteDO : sqlExecuteDOs) {
                preparedStatements[index] = connection.prepareStatement(sqlExecuteDO.getSql());
                List<Object[]> list = sqlExecuteDO.getParams();
                for(Object[] oneParams : list) {//设置一个参数
                    PreparedStatement now = preparedStatements[index];
                    int i = 1;
                    for(Object param : oneParams) {
                    	if(param instanceof Integer) {
                    		now.setInt(i++, (Integer)param);
                    	}else if(param instanceof Long) {
                    		now.setLong(i++, (Long)param);
                    	}else if(param instanceof byte[]) {
                    		now.setBytes(i++ , (byte[])param);
                    	}else  {
                    		now.setString(i++ , String.valueOf(param));
                    	}
                    }
                    now.addBatch();
                }
                index++;
            }
            for(PreparedStatement prep : preparedStatements) {
                prep.executeBatch();
            }
            connection.commit();
            connection.setAutoCommit(true);
        }catch (Exception e) {
            JdbcUtils.rollback(connection);
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.closePrep(preparedStatements);
            JdbcUtils.closeConnections(connection);
        }
    }
    
    /**
     * 获取sqlmap文件中的sql字符串
     * 
     * @param id
     * @return

    public String getSQL(String id) {
		String sql = null;
		SqlMapClientImpl sqlmap = (SqlMapClientImpl) this.getSqlMapClient();
		MappedStatement stmt = sqlmap.getMappedStatement(id);
		StaticSql staticSql = (StaticSql) stmt.getSql();
		sql = staticSql.getSql(null, null);
		return sql;
	}
    */
    /**
     * 根据sql查询数据条数
     * @param sql
     * @return
      */
	// public Integer getCount(String sql)
	// {
	// /*
	// * 例如：
	// * String sql = "select count(*) from mytable";
	// * int count = getCount(sql);
	// */
	// return this.jdbcTemplate.queryForInt(sql);
	// }

    /**
     * 写日志
     * @param logTypeCode
     * @param eventType
     * @param eventObject
     * @param eventText
     * @param userId
     */
    public void logEvent(Integer logTypeCode, Integer eventType, String eventObject, String eventText, Integer userId) {
	}

}
