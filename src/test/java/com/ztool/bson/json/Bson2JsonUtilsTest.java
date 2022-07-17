package com.ztool.bson.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.BsonDateTime;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Bson2JsonUtilsTest {
    @Test
    public void testToJson() throws JsonProcessingException {
        String moneyValueOri = "1.211";
        Instant instant = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();
        String createTimeOri = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault()).format(instant);

        Document document = new Document();
        document.append("money", new BigDecimal(moneyValueOri));
        document.append("createTime", new BsonDateTime(instant.toEpochMilli()));
        String jsonStr = Bson2JsonUtils.toJson(document);
        System.out.println(jsonStr);

        Map<String, Object> resultMap =  new ObjectMapper().readValue(jsonStr, Map.class);
        Assert.assertNotEquals(moneyValueOri, resultMap.get("money"));
        Assert.assertEquals(createTimeOri, resultMap.get("createTime"));
    }
}
