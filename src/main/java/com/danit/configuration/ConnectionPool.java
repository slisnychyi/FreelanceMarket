package com.danit.configuration;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public interface ConnectionPool {

    Connection getConnection() throws InterruptedException;

    Connection getConnection(long timeout, TimeUnit timeUnit) throws InterruptedException;

    void releaseConnection(Connection connection);

    boolean isAvaliableConnection();

}
