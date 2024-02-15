package com.khulekani.http;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class HttpParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);

    public HttpRequest parseHttpRequest(InputStream inputStream){
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);

        HttpRequest request = new HttpRequest();
        parseRequestLine(reader,request);
        parseHeaders(reader, request);
        parseBody(reader, request);

        return request;
    }
    private void parseRequestLine(InputStreamReader reader , HttpRequest request) {

    }


    private void parseHeaders(InputStreamReader reader, HttpRequest request) {
        
    }
    private void parseBody(InputStreamReader reader, HttpRequest request) {
    }



}
