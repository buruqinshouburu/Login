package com.itheima.Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DruidJDBCUtils {
    private static DataSource ds;
    static {
        InputStream stream = DruidJDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties prop=new Properties();
        try {
            prop.load(stream);
            ds = DruidDataSourceFactory.createDataSource(prop);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDataSource(){
        return ds;
    }
    public static void close(Connection con, Statement stat, ResultSet rs){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stat!=null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
