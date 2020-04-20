package com.danit.configuration;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class MyDataSource implements ConnectionPool{


    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public Connection getConnection(long timeout, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public void releaseConnection(Connection connection) {

    }

    @Override
    public boolean isAvaliableConnection() {
        return false;
    }
}
