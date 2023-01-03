package com.rest.test;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RestService extends BaseURI {

    private Response response;
    private BaseURI baseURI;
    private static JSONObject jsonObject;

    public RestService(){
    }

    static{

        JSONParser jsonParser = new JSONParser();
        FileReader fileReader= null;
        try {
            fileReader = new FileReader(".\\src\\test\\resources\\JsonFolder\\createObject.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Object object= null;
        try {
            object = jsonParser.parse(fileReader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
         jsonObject=(JSONObject)object;

    }

    /** get method for json objects **/
    public Response getObjects(){
        return BaseURI.config().when().get("/api/users").thenReturn();
    }

    /** post method for json objects **/
    public Response postObject() throws IOException, ParseException {
        JSONParser jsonParser =new JSONParser();
        FileReader fileReader=new FileReader(".\\src\\test\\resources\\JsonFolder\\postObject.json");
        Object obj=(Object)jsonParser.parse(fileReader);
        JSONObject jsonObject=(JSONObject)obj;
        return BaseURI.config().when().body(jsonObject).post("/api/users").thenReturn();
    }

    /** register method **/
    public Response registerObject(){
        return BaseURI.config().when().body(jsonObject).post("/api/register").thenReturn();
    }

}
