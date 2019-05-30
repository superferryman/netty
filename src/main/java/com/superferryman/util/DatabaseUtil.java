package com.superferryman.util;

import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author superferryman
 * @Date 2019/5/6 22:15
 *
 * 数据库操作工具类
 * 用于封装数据库操作
 *
 */
public class DatabaseUtil {
    /**
     * 异常信息常量
     * */
    private static final String ERROR_READ_PROPERTIES = "读取数据库配置文件失败";
    private static final String ERROR_LOAD_DB_DRIVERS = "加载数据库驱动失败";
    private static final String ERROR_GET_DB_CONNECTION = "获取数据库连接失败";
    private static final String ERROR_RELEASE_RESOURCE = "释放资源失败";
    private static final String ERROR_EXECUTE = "数据库操作失败";
    private static final String ERRER_QUERY = "数据库查询失败";
    private static final String ERROR_ATTRIBUTE_MAPPING = "属性映射失败";

    /**
     * 数据库连接配置
     * */
    private static Properties properties = null;

    /**
     * 数据库连接参数
     * */
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    /**
     * 初始化加载数据库资源
     */
    static {
        try {
            // 读取配置文件
            properties = new Properties();
            properties.load(DatabaseUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            // 获取参数
            driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");
            // 加载驱动
            Class.forName(driver);
        } catch (IOException e) {
            // 配置文件读取失败
            throw new RuntimeException(ERROR_READ_PROPERTIES, e);
        } catch (ClassNotFoundException e) {
            // 驱动加载失败
            throw new RuntimeException(ERROR_LOAD_DB_DRIVERS, e);
        }
    }

    /**
     * 获取数据库连接
     * @return 数据库连接对象
     */
    private static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            // 连接获取失败
            throw new RuntimeException(ERROR_GET_DB_CONNECTION, e);
        }
        return conn;
    }

    /**
     * 释放资源
     * @param rs 结果集
     * @param pstmt PreparedStatement 对象
     * @param conn 数据库连接对象
     */
    private static void release(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        // 关闭结果集
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // 资源释放失败
                throw new RuntimeException(ERROR_RELEASE_RESOURCE, e);
            }
        }

        // 关闭 PreparedStatement
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(ERROR_RELEASE_RESOURCE, e);
            }
        }

        // 关闭连接
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(ERROR_RELEASE_RESOURCE, e);
            }
        }
    }

    /**
     * 设置操作需要的参数
     * @param sql sql 语句
     * @param conn 数据库连接对象
     * @param pstmt PreparedStatement 对象
     * @param params sql 语句对应的参数列表
     * @return 填充参数后的 PreparedStatement 对象
     * @throws SQLException
     */
    private static PreparedStatement setPstmt(String sql, Connection conn, PreparedStatement pstmt, Object... params) throws SQLException {
        // 通过 Connection 对象获取 PreparedStatement 对象
        pstmt = conn.prepareStatement(sql);
        // 给所有参数赋值
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        return pstmt;
    }

    /**
     * 数据库查询，单表查询
     * @param sql sql 查询语句
     * @param tClass 要查询的 Java 对象
     * @param params 对象需要的参数
     * @return 要查询的 Java 对象列表
     */
    public static Object execQuery(String sql, Class tClass, Object... params) throws SQLException {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
        List<Object> list = new ArrayList<>();
        try {
            conn = getConnection();
            pstmt = setPstmt(sql, conn, pstmt, params);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // 将结果集转换为 Java 对象
                Object object = convert(rs, tClass);
                list.add(object);
            }
            return list;
        } catch (SQLException e) {
            // 查询失败
            throw new RuntimeException(ERRER_QUERY, e);
        } finally {
            release(rs, pstmt, conn);
        }
    }

    // TODO: 多表查询 本项目无这种需求

    /**
     * 数据库更新
     * @param sql sql 更新语句
     * @param params sql 语句的参数列表
     * @return 影响行数
     */
    public static int execUpdate(String sql, Object... params) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = setPstmt(sql, conn, pstmt, params);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            // 更新失败
            throw new RuntimeException(ERROR_EXECUTE, e);
        } finally {
            // 这样调用可以偷懒写少几个方法
            release(null, pstmt, conn);
        }
    }

    /**
     * 查询结果的转换，用于将结果集转换为 Java 对象
     * @param rs 查询得到的结果集
     * @param tClass 转换的目标对象类
     * @return 转换后的 Java 实体对象
     */
    private static Object convert(ResultSet rs, Class tClass) {
        try {
            if ("java.lang.Object".equals(tClass.getName())) {
                return rs.getObject(1);
            }
            // 创建 Java 对象实例
            Object object = tClass.newInstance();
            // 结果集头信息对象
            ResultSetMetaData metaData = rs.getMetaData();
            // 为 Java 对象的属性赋值
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                // 获取列名
                String name = metaData.getColumnName(i);
                // 这里需要列名和属性名一致
                BeanUtils.setProperty(object, name, rs.getObject(i));
            }
            return object;
        } catch (Exception e) {
            // 属性设置失败
            throw new RuntimeException(ERROR_ATTRIBUTE_MAPPING, e);
        }
    }
}
