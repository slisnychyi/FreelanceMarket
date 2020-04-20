package com.danit.configuration;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public interface ConnectionPool {

    Connection getConnection();

    Connection getConnection(long timeout, TimeUnit timeUnit);

    void releaseConnection(Connection connection);

    boolean isAvaliableConnection();

}
