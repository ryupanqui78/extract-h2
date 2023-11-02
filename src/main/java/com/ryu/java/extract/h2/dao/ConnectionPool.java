package com.ryu.java.extract.h2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.util.StringUtils;

import com.ryu.java.extract.h2.configurations.DatabaseConfig;
import com.ryu.java.extract.h2.exceptions.DatabaseException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionPool {
    
    private Connection connection;
    
    public ConnectionPool(DatabaseConfig config) throws DatabaseException {
        if (config == null) {
            throw new DatabaseException("Configuration is empty or null");
        }
        
        if (!StringUtils.hasText(config.getDriverClassName())) {
            throw new DatabaseException("Database drive class is empty or null");
        }
        
        try {
            Class.forName(config.getDriverClassName());
            connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            log.error(e.getMessage());
            throw new DatabaseException("Error getting the connection with the database", e);
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
}
