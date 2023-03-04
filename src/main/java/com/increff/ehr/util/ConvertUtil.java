package com.increff.ehr.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertUtil {
    public static <T,R> R objectMapper(T object, Class<R> destinClass){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        R newObject = mapper.convertValue(object, destinClass);
        return newObject;
    }

}
