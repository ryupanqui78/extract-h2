package com.ryu.java.extract.h2.models;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class TableSchema {
    
    private String tableName;
    private List<ColumnSchema> columns;
    private List<String> primaryKey;
    
    public String getJsonFormat() {
        StringBuilder result = new StringBuilder(500);
        
        result.append('{');
        result.append("\"table\": \"").append(this.getTableName()).append('"').append(',');
        result.append("\"columns\": [")
                .append(columns.stream().map(ColumnSchema::getJsonFormat).collect(Collectors.joining(","))).append(']')
                .append(',');
        result.append("\"primary_keys\": [").append(primaryKey.stream()
                .map(pk -> String.format("{\"column\": \"%s\"}", pk)).collect(Collectors.joining(","))).append(']');
        result.append('}');
        return result.toString();
    }
    
    public String getSelectAllQuery() {
        StringBuilder result = new StringBuilder(500);
        if (columns != null && !columns.isEmpty()) {
            result.append("SELECT ");
            result.append(columns.stream().map(ColumnSchema::getName).collect(Collectors.joining(",")));
            result.append(" FROM ");
            result.append(this.tableName);
        }
        return result.toString();
    }
}
