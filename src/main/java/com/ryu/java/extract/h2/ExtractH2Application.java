package com.ryu.java.extract.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ryu.java.extract.h2.configurations.DatabaseConfig;
import com.ryu.java.extract.h2.dao.ConnectionPool;
import com.ryu.java.extract.h2.exceptions.DatabaseException;
import com.ryu.java.extract.h2.services.ExtractH2Data;

@SpringBootApplication
public class ExtractH2Application implements CommandLineRunner {
    
    @Autowired
    private DatabaseConfig config;
    
    public static void main(String[] args) {
        SpringApplication.run(ExtractH2Application.class, args);
    }
    
    @Bean
    ExtractH2Data processData() throws DatabaseException {
        ConnectionPool databaseConfig = new ConnectionPool(config);
        return new ExtractH2Data(databaseConfig.getConnection());
    }
    
    @Override
    public void run(String... args) throws Exception {
        processData().readMetadata();
    }
    
}
