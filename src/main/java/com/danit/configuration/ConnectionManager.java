package com.danit.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {

    private final HikariDataSource dataSource;

    private static final int CONNECTION_POOL_SIZE = 10;
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/freelance_market?user=postgres&password=docker&loggerLevel=TRACE";

    public ConnectionManager() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        this.dataSource = new HikariDataSource(config);
        this.dataSource.setMaximumPoolSize(CONNECTION_POOL_SIZE);
    }

    @SneakyThrows
    public Connection getConnection() {
        return dataSource.getConnection();
    }


    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        HikariDataSource dataSource = new HikariDataSource(config);
        dataSource.setMaximumPoolSize(CONNECTION_POOL_SIZE);

        int client = 10;
        int operation = 3;

        for (int i = 0; i < client; i++) {
            new Thread(() -> {
                for (int j = 0; j < operation; j++) {
                    try(Connection connection = dataSource.getConnection()) {
                        final PreparedStatement statement = connection.prepareStatement("select * from developers");
                        statement.execute();
                        System.out.printf("--opertation %s " + " -- connection: %s \n", j, connection);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


}
