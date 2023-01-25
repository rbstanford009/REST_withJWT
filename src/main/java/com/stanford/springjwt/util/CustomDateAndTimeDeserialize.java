package com.stanford.springjwt.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CustomDateAndTimeDeserialize extends JsonDeserializer<Date> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");   // 2017-11-20 00:00:01
          //      "yyyy-MM-dd HH:mm:ss");   // 2017-11-20 00:00:01

    @Override
    public Date deserialize(JsonParser paramJsonParser,
                            DeserializationContext paramDeserializationContext)
            throws IOException, JsonProcessingException {
        String str = paramJsonParser.getText().trim();
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return paramDeserializationContext.parseDate(str);
    }
}
