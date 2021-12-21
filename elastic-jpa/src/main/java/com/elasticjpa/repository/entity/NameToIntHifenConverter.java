package com.elasticjpa.repository.entity;

import javax.persistence.AttributeConverter;
import java.util.Arrays;


public class NameToIntHifenConverter implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute != null && !attribute.isEmpty()) {
            StringBuilder converted = new StringBuilder("");
            attribute.chars().forEachOrdered(i -> {
                converted.append(i);
                converted.append("-");
            });
            return converted.toString();
        }
        return null;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData != null && !dbData.isEmpty()) {
            StringBuilder converted = new StringBuilder("");
            Arrays.stream(dbData.split("-")).forEach(i -> converted.append(Character.toString(Integer.parseInt(i))));
            return converted.toString();
        }
        return null;
    }
}
