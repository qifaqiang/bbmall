package com.wxsoft.framework.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class JdbcUtils {

	/**
     * 回滚连接
     * @param conn
     */
    public static void rollback(Connection conn) {
        try {
            if(conn != null) conn.rollback();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     * @param conn
     */
    public static void closeConnections(Connection conn) {
        try {
            if(conn != null) conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭预编译语句
     * @param preparedStatements
     */
    public static void closePrep(PreparedStatement...preparedStatements) {
        for(PreparedStatement preparedStatement : preparedStatements) {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                }catch (Exception e) {}
            }
        }
    }
}
