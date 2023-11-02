package com.ryu.java.extract.h2.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ColumnSchemaTest {
    
    @Test
    void whenGetJsonFormatReturnJsonFormat() {
        ColumnSchema columnSchema = new ColumnSchema();
        columnSchema.setName("ID");
        columnSchema.setSize("10");
        columnSchema.setDataType("4");
        columnSchema.setTypeName("INTEGER");
        columnSchema.setIsNullable("NO");
        columnSchema.setIsAutoincrement("YES");
        
        assertEquals(
                "{\"name\": \"ID\",\"size\": \"10\",\"datatype\": \"4\",\"typename\": \"INTEGER\",\"isnull\": \"NO\",\"isautoincrement\": \"YES\"}",
                columnSchema.getJsonFormat());
    }
    
    @Test
    void whenGetJsonFormatGivenFieldWithNullValueReturnJsonFormatWithNullText() {
        ColumnSchema columnSchema = new ColumnSchema();
        columnSchema.setName("ID");
        columnSchema.setSize("10");
        columnSchema.setDataType("4");
        columnSchema.setTypeName("INTEGER");
        columnSchema.setIsAutoincrement("YES");
        
        assertEquals(
                "{\"name\": \"ID\",\"size\": \"10\",\"datatype\": \"4\",\"typename\": \"INTEGER\",\"isnull\": \"null\",\"isautoincrement\": \"YES\"}",
                columnSchema.getJsonFormat());
    }
    
}
