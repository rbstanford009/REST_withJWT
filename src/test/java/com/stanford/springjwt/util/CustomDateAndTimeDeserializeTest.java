package com.stanford.springjwt.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class CustomDateAndTimeDeserializeTest {
    String testData = "[\n" +
            "   {\n" +
            "    \"description\":\" CEO\",\n" +
            "    \"org_name\":\"Wayne Enterprises\",\n" +
            "        \"updated\" : \"2017/11/20\",\n" +
            "    \"root\":\"0\"\n" +
            "    },\n" +
            "    {\n" +
            "    \"description\":\"Human Resources\",\n" +
            "    \"org_name\":\"HR\",\n" +
            "      \"updated\" : \"2017-11-20\",\n" +
            "    \"root\":\"1\"\n" +
            "    },\n" +
            "    {\n" +
            "    \"description\":\"Development\",\n" +
            "    \"org_name\":\"Development\",\n" +
            "      \"updated\" : \"2017-11-20\",\n" +
            "    \"root\":\"1\"\n" +
            "    },\n" +
            "        {\n" +
            "    \"description\":\"Management\",\n" +
            "    \"org_name\":\"Management\",\n" +
            "      \"updated\" : \"2017-11-20 01:01:01\",\n" +
            "    \"root\":\"1\"\n" +
            "    }\n" +
            "\n" +
            "]";

    @Test
    public void testDateOne() {
        ObjectMapper mapper = new ObjectMapper();



        CustomDateAndTimeDeserialize testOne = new CustomDateAndTimeDeserialize();
//        try {
//            JsonParser paramJsonParser = new JsonFactory().createParser(testData.getBytes());
//
//            DeserializationContext paramDeserializationContext = spy(mapper.getDeserializationContext());
//         //   doReturn(true).when(paramDeserializationContext).isEnabled(any(MapperFeature.class));
//            Date date = testOne.deserialize(paramJsonParser,paramDeserializationContext);
//            String expectedString = "woof2";
//            assertEquals(expectedString, "woof2");
//            System.out.println("WOOF => 2");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }



}