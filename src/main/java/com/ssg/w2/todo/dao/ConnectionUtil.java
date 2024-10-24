package com.ssg.w2.todo.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public enum ConnectionUtil {

    //Connection 클래스는 하나의 객체를 만들어서 사용하는 방식이다.
    // HikariConfig를 이용해서 하나의  HikariDataSource를 구성한다.
    // 구성된 HikariDataSource는 getConnection() 을 통해서 사용된다.
    // 외부에서는 ConnectionUtil.INSTANCE.getConnection() ㅌ오해서 Connnection을 받을 수 있다.

    INSTANCE;

    private HikariDataSource ds;

    ConnectionUtil(){
        HikariConfig config = new HikariConfig();

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/ssg?serverTimezone=UTC");
        config.setUsername("webuser");
        config.setPassword("webuser1234");
        config.addDataSourceProperty("cachePrespStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");


        ds = new HikariDataSource(config);

    }

    public Connection getConnection() throws Exception{
        return ds.getConnection();
    }


}
