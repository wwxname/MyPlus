package com.plus.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * @author plus me
 * @date 2018/6/19
 */
public abstract class JSONUtils {


    public static String obj2Json(Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public static void beautyPrint(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String beautyString  =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        System.err.println(beautyString);
    }

    public static Map<String, Object> json2Map(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> convertedMap = null;
        try {
            convertedMap = mapper.readValue(json, new TypeReference<Map<String, Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedMap;
    }
    public static void beautyPrint(String json) throws JsonProcessingException {
        Map<String, Object> convertedMap = json2Map(json);
        beautyPrint(convertedMap);
    }

}
