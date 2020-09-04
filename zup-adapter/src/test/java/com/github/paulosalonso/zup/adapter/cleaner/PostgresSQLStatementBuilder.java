package com.github.paulosalonso.zup.adapter.cleaner;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class PostgresSQLStatementBuilder implements SQLStatementBuilder {

    @Override
    public Statement buildSQLStatement(Connection connection, List<String> tableNames) throws SQLException {
        Statement statement = connection.createStatement();

        for (String table : tableNames) {
            statement.addBatch(String.format("TRUNCATE TABLE %s CASCADE", table));
        }

        return statement;
    }
}
