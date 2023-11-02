package com.ryu.java.extract.h2.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ryu.java.extract.h2.models.ColumnSchema;
import com.ryu.java.extract.h2.models.TableSchema;
import com.ryu.java.extract.h2.utilities.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtractH2Data {
    private final Connection connection;
    
    public ExtractH2Data(Connection conn) {
        connection = conn;
    }
    
    public void readMetadata() throws SQLException, IOException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        try (ResultSet resultSet = databaseMetaData.getTables(null, null, null, new String[] { "TABLE" })) {
            while (resultSet.next()) {
                TableSchema table = this.getTableInformation(resultSet.getString("TABLE_NAME"), databaseMetaData);
                this.dumpDataInformation(table);
            }
        }
    }
    
    private TableSchema getTableInformation(String tableName, DatabaseMetaData databaseMetaData) throws SQLException {
        TableSchema table = new TableSchema();
        table.setTableName(tableName);
        // Print the names of existing tables
        try (ResultSet columns = databaseMetaData.getColumns(null, null, tableName, null)) {
            List<ColumnSchema> listColumns = new ArrayList<>();
            while (columns.next()) {
                ColumnSchema column = new ColumnSchema();
                column.setName(columns.getString("COLUMN_NAME"));
                column.setSize(columns.getString("COLUMN_SIZE"));
                column.setDataType(columns.getString("DATA_TYPE"));
                column.setTypeName(columns.getString("TYPE_NAME"));
                column.setIsNullable(columns.getString("IS_NULLABLE"));
                column.setIsAutoincrement(columns.getString("IS_AUTOINCREMENT"));
                listColumns.add(column);
            }
            table.setColumns(listColumns);
        }
        try (ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(null, null, table.getTableName())) {
            List<String> pk = new ArrayList<>();
            while (primaryKeys.next()) {
                pk.add(primaryKeys.getString("COLUMN_NAME"));
            }
            table.setPrimaryKey(pk);
        }
        log.info(table.getJsonFormat());
        return table;
    }
    
    private void dumpDataInformation(TableSchema table) throws SQLException, IOException {
        String query = table.getSelectAllQuery();
        List<String> rows = new ArrayList<>();
        log.info(query);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, String> row = new HashMap<>();
                    for (ColumnSchema column : table.getColumns()) {
                        row.put(column.getName(), rs.getString(column.getName()));
                    }
                    rows.add(Utility.convertMapToJson(row));
                }
            }
        }
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(String.format("%s.json", table.getTableName())))) {
            writer.write(String.format("{ \"schema\": %s,", table.getJsonFormat()));
            writer.write(
                    String.format("\"data\": [%s]}", rows.stream().map(row -> row).collect(Collectors.joining(","))));
            writer.flush();
        }
    }
}
