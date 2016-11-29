package com.jamasoftware.services.restclient.httpconnection;

import com.jamasoftware.services.restclient.exception.RestClientException;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestHttpClient implements HttpClient {
    private JSONObject responses;
    private JSONObject payloads;
    public TestHttpClient() {
        JSONParser parser = new JSONParser();
        try {
            responses = (JSONObject)parser.parse(new FileReader("testData" + File.separator + "testApiResponses.json"));
            payloads = (JSONObject)parser.parse(new FileReader("testData" + File.separator + "testApiPayloads.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Response get(String url, String username, String password) throws RestClientException {
        String key = "GET " + url;
        JSONObject responseObject = (JSONObject)responses.get(key);
        String response = ((JSONObject)responseObject.get("response")).toJSONString();
        Integer statusCode = (int)(long)responseObject.get("statusCode");

        System.out.println(key);
        return new Response(statusCode, response);
    }

    @Override
    public Response delete(String url, String username, String password) throws RestClientException {
        return null;
    }

    @Override
    public Response post(String url, String username, String password, String payload) throws RestClientException {
        String key = "POST " + url;
        JSONObject payloadObject = (JSONObject)payloads.get(key);

        String expectedPayload = ((JSONObject)payloadObject.get("payload")).toJSONString();
        String response = ((JSONObject)payloadObject.get("response")).toJSONString();
        Integer statusCode = (int)(long)payloadObject.get("statusCode");

        if(expectedPayload == null) {
            throw new RestClientException("Payloads object returned null for POST " + url);
        }
        try {
            JSONAssert.assertEquals(expectedPayload, payload, true);
        } catch (JSONException e) {
            throw new RestClientException(e);
        }

        return new Response(statusCode, response);
    }

    @Override
    public Response put(String url, String username, String password, String payload) throws RestClientException {
        return null;
    }

    @Override
    public Response putFile(String url, String username, String password, File file) throws RestClientException {
        return null;
    }

    @Override
    public FileResponse getFile(String url, String username, String password) throws RestClientException {
        return null;
    }
}
