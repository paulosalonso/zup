package com.github.paulosalonso.zup.adapter.cleaner;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface SQLStatementBuilder {
    Statement buildSQLStatement(Connection connection, List<String> tableNames) throws SQLException;
}
