package com.ryu.java.extract.h2.utilities;

import java.util.Map;
import java.util.Map.Entry;

public class Utility {
    
    public static String convertMapToJson(Map<String, String> origin) {
        StringBuilder result = new StringBuilder(500);
        
        result.append('{');
        if (origin != null && !origin.isEmpty()) {
            int count = 0;
            for (Entry<String, String> entry : origin.entrySet()) {
                if (count > 0) {
                    result.append(',');
                }
                result.append(String.format("\"%s\": ", entry.getKey()));
                if (entry.getValue() != null) {
                    result.append(String.format("\"%s\"", entry.getValue()));
                } else {
                    result.append("null");
                }
                count++;
            }
        }
        result.append('}');
        
        return result.toString();
    }
    
    private Utility() {
    }
}
