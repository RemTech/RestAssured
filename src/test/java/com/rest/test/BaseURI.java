package com.rest.test;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class BaseURI {
    static {
        baseURI="https://reqres.in";
    }
    public static RequestSpecification config(){
        return given().header("Content-Type","application/json").accept("text/html");
    }
}
