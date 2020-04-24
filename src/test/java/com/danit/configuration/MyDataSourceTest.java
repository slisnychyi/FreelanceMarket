package com.danit.configuration;


import com.danit.controller.IncomeController;
import com.danit.repository.IncomeRepository;
import com.danit.service.IncomeService;
import org.junit.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class MyDataSourceTest {
    @Test
    public void getConnectionIsNotNull() throws SQLException, InterruptedException {
        //given
        Config config = new Config("jdbc:postgresql://drona.db.elephantsql.com/ysvaollf?user=ysvaollf&password=11P4GEbyIuhA1iusMhWV7L4pm2MmWBmz");
        MyDataSource myDataSource = new MyDataSource(config);
        //when
        DrainConnection drainConnection = myDataSource.getConnection();
        //than
        assertThat(drainConnection).isNotNull();
    }

    @Test
    public void checkUpdateConnection() throws SQLException, InterruptedException {
        //given
        IncomeController incomeController = new IncomeController(new IncomeService(new IncomeRepository()));
        incomeController.sendIncomes(25);

    }
}