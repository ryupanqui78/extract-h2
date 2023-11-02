package com.ryu.java.extract.h2.exceptions;

public class DatabaseException extends Exception {
    
    private static final long serialVersionUID = -7965905526629784652L;
    
    public DatabaseException(String message) {
        super(message);
    }
    
    public DatabaseException(String message, Exception e) {
        super(message, e);
    }
    
}
