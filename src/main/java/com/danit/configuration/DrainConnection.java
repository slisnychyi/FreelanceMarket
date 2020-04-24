package com.danit.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DrainConnection {
    private Connection connection;
    private final String jdbcUrl;
    private final int cyclesOfUse;
    private int used = 0;

    public DrainConnection(String jdbcUrl, int cyclesOfUse) throws SQLException {
        this.connection = DriverManager.getConnection(jdbcUrl);
        this.jdbcUrl = jdbcUrl;
        this.cyclesOfUse = cyclesOfUse;
    }

    public void resetConnection() throws SQLException {
        System.out.println("reset");
        connection.close();
        connection = DriverManager.getConnection(jdbcUrl);
        used = 0;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if (used > cyclesOfUse) {
            resetConnection();
        }
        used++;
        System.out.println(used);
        return connection.prepareStatement(sql);
    }
}
