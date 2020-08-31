package com.github.paulosalonso.zup.adapter.cleaner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseCleaner {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private DataSource dataSource;
	private SQLStatementBuilder sqlStatementBuilder;

	public DatabaseCleaner(DataSource dataSource, SQLStatementBuilder sqlStatementBuilder) {
		this.dataSource = dataSource;
		this.sqlStatementBuilder = sqlStatementBuilder;
	}

	public void clearTables() {
		LOGGER.debug("Cleaning database");

		try (Connection connection = dataSource.getConnection()) {
			sqlStatementBuilder
					.buildSQLStatement(connection, getTablesNames(connection))
					.executeBatch();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private List<String> getTablesNames(Connection connection) throws SQLException {
		List<String> tableNames = new ArrayList<>();

		DatabaseMetaData metaData = connection.getMetaData();

		ResultSet rs = metaData.getTables(
				connection.getCatalog(), null, null, new String[] { "TABLE" });

		while (rs.next()) {
			tableNames.add(rs.getString("TABLE_NAME"));
		}

		tableNames.remove("flyway_schema_history");

		return tableNames;
	}
	
}