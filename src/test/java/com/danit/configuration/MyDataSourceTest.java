package com.danit.configuration;


import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class MyDataSourceTest {
    @Test
    public void getConnectionIsNotNull() throws SQLException, InterruptedException {
        //given
        Config config = new Config("jdbc:postgresql://drona.db.elephantsql.com/ysvaollf?user=ysvaollf&password=11P4GEbyIuhA1iusMhWV7L4pm2MmWBmz");
        MyDataSource myDataSource = new MyDataSource(config);
        DrainConnection drainConnection = myDataSource.getConnection();
        //when
        Connection connection = drainConnection.getConnection();
        //than
        assertThat(connection).isNotNull();
    }

    @Test
    public void getConnectionAndReleaseConnection () throws SQLException, InterruptedException {
        //given
        Config config = new Config("jdbc:postgresql://drona.db.elephantsql.com/ysvaollf?user=ysvaollf&password=11P4GEbyIuhA1iusMhWV7L4pm2MmWBmz");
        MyDataSource myDataSource = new MyDataSource(config);
        DrainConnection drainConnection = myDataSource.getConnection();
        //when

        //than
    }
}