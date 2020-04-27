package com.danit.configuration;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DbUtils {

    public static <T> List<T> getList(String sql, Class<T> type, Connection connection) {
        List<T> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                result.add(createNewInstance(res, type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static <T> T createNewInstance(ResultSet res, Class<T> type) {
        T result = null;
        try {
            ResultSetMetaData metaData = res.getMetaData();
            int columns = metaData.getColumnCount();
            for (int i = 1; i < columns; i++) {
                String columnName = metaData.getColumnName(i);
                int finalI = i;
//            Optional.ofNullable(type.getField(columnName))
//                    .ifPresent(field -> {
//                        field.setAccessible(true);
//                        Object value = res.getObject(finalI, field.getType());
//                        field.set(result, value);
//                    });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }



}


