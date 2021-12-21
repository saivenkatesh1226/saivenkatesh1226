package com.elasticjpa.repository.entity;

import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeFromIndexedValueContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;

import java.util.Arrays;

public class NameToIntHifenBridge implements ValueBridge<String, String> {

    @Override
    public String toIndexedValue(String s, ValueBridgeToIndexedValueContext valueBridgeToIndexedValueContext) {
        if (s != null && !s.isEmpty() && s.contains("-")) {
            StringBuilder converted = new StringBuilder("");
            Arrays.stream(s.split("-")).forEach(i -> {
                if (i != null && !i.isEmpty() && !i.isBlank()) {
                    converted.append(Character.toString(Integer.parseInt(i)));
                }
            });
            return converted.toString();
        } else if (s!= null && !s.isEmpty() && !s.contains("-")) {
            return s;
        }
        return null;
    }

    @Override
    public String fromIndexedValue(String value, ValueBridgeFromIndexedValueContext context) {
        if (value != null && !value.isEmpty()) {
            StringBuilder converted = new StringBuilder("");
            Arrays.stream(value.split("-")).forEach(i -> {
                if (i != null && !i.isEmpty() && !i.isBlank()) {
                    converted.append(Character.toString(Integer.parseInt(i)));
                }
            });
            return converted.toString();
        }
        return null;
    }
}
