package com.rest.test;

import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.*;

public class Rest_Test {

    private Response response;
    private RestService restService;

    public Rest_Test() {
        this.restService = new RestService();
    }

    @Test
    @DisplayName(" GET method & object assertion")
    public void getObject() {
        response = restService.getObjects();
        String page = response.getBody().jsonPath().getString("page");
        int pages = Integer.parseInt(page);
        assertEquals(1, pages);
        System.out.println(pages);
    }

    @Test
    @DisplayName("Post method status and Json assertion")
    public void postObject() throws IOException, ParseException {
        response = restService.postObject();
        int stat = response.getStatusCode();
        String name = response.jsonPath().get("name").toString();
        assertEquals(201, stat);
        System.out.println(name);
        assertEquals("morpheus", name);
    }

    @Test
    @DisplayName(" Post Method Test")
    public void registerObject() {
        response = restService.registerObject();
        String token = response.getBody().jsonPath().getString("token");
        assertEquals("QpwL5tke4Pnpja7X4", token);
        String id = response.getBody().jsonPath().getString("id");
        int Id = Integer.parseInt(id);
        assertEquals(4, Id);
    }

    @Test
    @DisplayName(" Get System properties")
    public void getProperties() {
        /** Checking the properties of my computer **/
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("os.name"));
        assertEquals("C:\\REST-Framework\\RestAssured", System.getProperty("user.dir"));
        assertEquals("amd64", System.getProperty("os.arch"));
        assertEquals("1.8.0_311", System.getProperty("java.version")); /** C:\Program Files\Java\jdk1.8.0_311\bin\java.exe **11.0.13** this version is used to run the project**/
        assertEquals("Windows 10", System.getProperty("os.name"));
    }

    @Test
    @DisplayName("get JSON array key & values and assertion")
    public void getArray() {
        response = restService.getArrayObjects();
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
        List<Map<Object,Object>> arrayList=new ArrayList<>();
        arrayList = response.getBody().jsonPath().get("data");  /** to rewrite the logic here **/
        Object id=arrayList.get(0).get("id");
        assertEquals(1,id);
        Object name=arrayList.get(0).get("name");
        assertEquals("cerulean",name);
        Object year=arrayList.get(0).get("year");
        assertEquals(2000,year);
        Object colour=arrayList.get(0).get("color");
        assertEquals("#98B2D1",colour);
        Object pantone_value=arrayList.get(0).get("pantone_value");
        assertEquals("15-4020",pantone_value);
    }
}



