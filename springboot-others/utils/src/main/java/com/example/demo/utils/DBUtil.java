package com.example.demo.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DBUtil {

    private static Properties props;

    static {
        try {
            InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("application-dev.yml");
            props = new Properties();
            props.load(in);
            if (null != in) {
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws Exception {
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        String driver = props.getProperty("driver-class-name");
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        try {
            Connection conn = DBUtil.getConn();
            String sql = "SELECT * FROM salaries WHERE emp_no = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, 10001);

            ResultSet result = statement.executeQuery();
            System.out.println(result);

            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
