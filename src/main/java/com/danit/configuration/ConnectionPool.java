package com.danit.configuration;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public interface ConnectionPool {

    DrainConnection getConnection() throws InterruptedException;

    DrainConnection getConnection(long timeout, TimeUnit timeUnit) throws InterruptedException;

    void releaseConnection(DrainConnection connection);

    boolean isAvaliableConnection();

}
