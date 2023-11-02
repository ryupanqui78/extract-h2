package com.ryu.java.extract.h2.models;

import lombok.Data;

@Data
public class ColumnSchema {
    
    private String name;
    private String size;
    private String dataType;
    private String typeName;
    private String isNullable;
    private String isAutoincrement;
    
    public String getJsonFormat() {
        StringBuilder result = new StringBuilder(100);
        
        result.append('{');
        result.append(String.format("\"name\": \"%s\",", this.getName()));
        result.append(String.format("\"size\": \"%s\",", this.getSize()));
        result.append(String.format("\"datatype\": \"%s\",", this.getDataType()));
        result.append(String.format("\"typename\": \"%s\",", this.getTypeName()));
        result.append(String.format("\"isnull\": \"%s\",", this.getIsNullable()));
        result.append(String.format("\"isautoincrement\": \"%s\"", this.getIsAutoincrement()));
        result.append('}');
        return result.toString();
    }
    
}
