package com.boman.common.util.wechat.wechat.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLDBCon {
    private static Connection conn = null;

    public static Connection getCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String user = "zhaoshang";
            String pwd = "zhaoshang@2018";
            String url = "jdbc:mysql://127.0.0.1:3306/zhaoshang";
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
