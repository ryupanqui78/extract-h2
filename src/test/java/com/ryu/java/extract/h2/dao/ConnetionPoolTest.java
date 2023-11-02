package com.ryu.java.extract.h2.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ryu.java.extract.h2.configurations.DatabaseConfig;
import com.ryu.java.extract.h2.exceptions.DatabaseException;

@ExtendWith(MockitoExtension.class)
class ConnetionPoolTest {
    
    @Test
    void whenCreateConnectionGivenConfigurationNullReturnDatabaseException() {
        Assertions.assertThrows(DatabaseException.class, () -> new ConnectionPool(null));
    }
    
    @Test
    void whenCreateConnectionGivenMissingClassNameReturnDatabaseException() {
        DatabaseConfig config = new DatabaseConfig();
        Assertions.assertThrows(DatabaseException.class, () -> new ConnectionPool(config));
    }
    
    @Test
    void whenCreateConnectionGivingMissingInformationReturnDatabaseException() {
        DatabaseConfig config = new DatabaseConfig();
        config.setDriverClassName("org.h2.Driver");
        Assertions.assertThrows(DatabaseException.class, () -> new ConnectionPool(config));
    }
    
    @Test
    void whenCreateConnectionReturnConnection() throws DatabaseException {
        DatabaseConfig config = new DatabaseConfig();
        config.setDriverClassName("org.h2.Driver");
        config.setUrl("jdbc:h2:mem:testdb");
        assertNotNull(new ConnectionPool(config));
    }
}
