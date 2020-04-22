package com.danit.configuration;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyDataSource implements ConnectionPool {

    private static final long DEFAULT_TIMEOUT = 1000;

    private final BlockingQueue<DrainConnection> connections;

    public MyDataSource(Config configuration) throws SQLException {
        this.connections = new ArrayBlockingQueue<>(configuration.getConnectionSize());
        for (int i = 0; i < configuration.getConnectionSize(); i++) {
            this.connections.add(new DrainConnection(configuration.getJdbcUrl(), 5));
        }
    }

    @Override
    public DrainConnection getConnection() throws InterruptedException {
        return getConnection(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    @Override
    public DrainConnection getConnection(long timeout, TimeUnit timeUnit) throws InterruptedException {
        return connections.poll(timeout, timeUnit);
    }

    @Override
    public void releaseConnection(DrainConnection connection) {
        this.connections.add(connection);
    }

    @Override
    public boolean isAvaliableConnection() {
        return connections.size() > 0;
    }

    public static Config createConfig(String jdbcUrl) {
        return new Config(jdbcUrl);
    }
}

@Data
class Config {

    private static final int DEFAULT_CONNECTIONS = 2;
    private final String jdbcUrl;
    private int connectionSize = DEFAULT_CONNECTIONS;
}


class DrainConnection {
    private static final int DEFAULT_CYCLES_OF_USE = 5;
    private Connection connection;
    private final String jdbcUrl;
    private int cyclesOfUse;
    private int used = 0;

    public DrainConnection(String jdbcUrl, int cyclesOfUse) throws SQLException {
        this.connection = DriverManager.getConnection(jdbcUrl);
        this.jdbcUrl = jdbcUrl;
        this.cyclesOfUse = cyclesOfUse;
    }

    public DrainConnection(String jdbcUrl) throws SQLException {
        this.connection = DriverManager.getConnection(jdbcUrl);
        this.jdbcUrl = jdbcUrl;
        this.cyclesOfUse = DEFAULT_CYCLES_OF_USE;
    }

    public Connection getConnection() throws SQLException {
        if (used > cyclesOfUse) {
            used = 0;
            connection = DriverManager.getConnection(jdbcUrl);
        }
        used++;
        return connection;
    }

    public void releaseConnection(Connection connection) {
        this.connection = connection;
    }


}