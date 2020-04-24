package com.danit.repository;

import com.danit.configuration.ConnectionPool;
import com.danit.configuration.DrainConnection;
import com.danit.configuration.MyDataSource;
import com.danit.model.Income;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IncomeRepository {

    private final ConnectionPool dataSource;

    public IncomeRepository() throws SQLException {
        this.dataSource = new MyDataSource(MyDataSource.createConfig("jdbc:postgresql://drona.db.elephantsql.com/ysvaollf?user=ysvaollf&password=11P4GEbyIuhA1iusMhWV7L4pm2MmWBmz"));
    }

    public void save(Income income) {
        DrainConnection drainConnection = null;
        try {
            drainConnection = dataSource.getConnection();
            PreparedStatement preparedStatement = drainConnection.prepareStatement("insert into income (developer_id, amount) values (?,?)");
            preparedStatement.setInt(1, income.getDeveloperId());
            preparedStatement.setBigDecimal(2, income.getAmount());
            preparedStatement.execute();

        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        } finally {
            dataSource.releaseConnection(drainConnection);
        }
    }

}
