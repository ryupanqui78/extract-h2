package com.ryu.java.extract.h2.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UtilityTest {
    
    @Test
    void whenConvertMapToJsonGivenMapNullReturnEmptyJson() {
        assertEquals("{}", Utility.convertMapToJson(null));
    }
    
    @Test
    void whenConvertMapToJsonGivenMapEmptyReturnEmptyJson() {
        assertEquals("{}", Utility.convertMapToJson(new HashMap<>()));
    }
    
    @Test
    void whenConvertMapToJsonGivenMapReturnJsonWithKeyAndValue() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        assertEquals("{\"key\": \"value\"}", Utility.convertMapToJson(map));
    }
    
    @Test
    void whenConvertMapToJsonGivenMapWithNullReturnJsonWithKeyAndValueNull() {
        Map<String, String> map = new HashMap<>();
        map.put("key", null);
        assertEquals("{\"key\": null}", Utility.convertMapToJson(map));
    }
    
    @Test
    void whenConvertMapToJsonGivenMapWithMultipleReturnJsonWithComma() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertEquals("{\"key1\": \"value1\",\"key2\": \"value2\"}", Utility.convertMapToJson(map));
    }
}
