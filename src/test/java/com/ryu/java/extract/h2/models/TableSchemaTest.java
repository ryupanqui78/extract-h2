package com.ryu.java.extract.h2.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TableSchemaTest {
    
    @Test
    void whenGetJsonFormatReturnJsonFormat() {
        TableSchema tableSchema = this.createTable();
        assertThat(tableSchema.getJsonFormat()).startsWith("{\"table\": \"table\"");
    }
    
    @Test
    void whenGetJsonFormatWithEmptyColumnReturnColumnEmpty() {
        TableSchema tableSchema = this.createTable();
        tableSchema.setColumns(new ArrayList<>());
        assertThat(tableSchema.getJsonFormat()).contains("\"columns\": []");
    }
    
    @Test
    void whenGetJsonFormatWithEmptyPrimaryKeyReturnPrimaryKeyEmpty() {
        TableSchema tableSchema = this.createTable();
        tableSchema.setPrimaryKey(new ArrayList<>());
        assertThat(tableSchema.getJsonFormat()).contains("\"primary_keys\": []");
    }
    
    @Test
    void shenGetSelectAllQueryReturnQueryStructure() {
        TableSchema tableSchema = this.createTable();
        assertEquals("SELECT column FROM table", tableSchema.getSelectAllQuery());
    }
    
    @Test
    void shenGetSelectAllQueryWithNullColumnReturnEmptyQuery() {
        TableSchema tableSchema = this.createTable();
        tableSchema.setColumns(null);
        assertEquals("", tableSchema.getSelectAllQuery());
    }
    
    @Test
    void shenGetSelectAllQueryWithEmptyColumnReturnEmptyQuery() {
        TableSchema tableSchema = this.createTable();
        tableSchema.setColumns(new ArrayList<>());
        assertEquals("", tableSchema.getSelectAllQuery());
    }
    
    private TableSchema createTable() {
        TableSchema tableSchema = new TableSchema();
        List<ColumnSchema> listColumns = new ArrayList<>();
        List<String> listPk = new ArrayList<>();
        ColumnSchema column = new ColumnSchema();
        
        column.setName("column");
        listColumns.add(column);
        listPk.add("PK");
        
        tableSchema.setTableName("table");
        tableSchema.setColumns(listColumns);
        tableSchema.setPrimaryKey(listPk);
        
        return tableSchema;
    }
}
