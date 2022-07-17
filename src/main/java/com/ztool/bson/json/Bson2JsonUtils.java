package com.ztool.bson.json;

import com.ztool.bson.json.converter.DateTimeConverter;
import com.ztool.bson.json.converter.Decimal128Converter;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;

public class Bson2JsonUtils {
    public static String toJson(Document document){
        JsonWriterSettings jsonWriterSettings = JsonWriterSettings.builder()
                .decimal128Converter(new Decimal128Converter())
                .dateTimeConverter(new DateTimeConverter()).build();

        return document.toJson(jsonWriterSettings);
    }
}
