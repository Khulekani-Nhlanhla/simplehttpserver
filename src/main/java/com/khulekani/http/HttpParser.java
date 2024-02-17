package com.khulekani.http;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class HttpParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);

    private static final int SP = 0x20; // 32 - Space
    private static final int CR = 0x0D; // 13 - Character Return
    private static final int LF = 0x0A; // 10 - Line Feed
    public HttpRequest parseHttpRequest(InputStream inputStream){
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);

        HttpRequest request = new HttpRequest();
        try {
            parseRequestLine(reader,request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        parseHeaders(reader, request);
        parseBody(reader, request);

        return request;
    }
    private void parseRequestLine(InputStreamReader reader , HttpRequest request) throws IOException {
        StringBuilder processingDataBuffer = new StringBuilder();

        boolean methodParsed = false;
        boolean requestTargetParsed = false;
        int _byte;
        while ( (_byte = reader.read()) >=0) {
            if (_byte == CR) {
                _byte = reader.read();
                if (_byte == LF) {
                    LOGGER.debug("Request Line to Process: {}", processingDataBuffer.toString());

                    return;
                }
            }
            // Used to locate the SP characters in the requests
            if (_byte == SP){
                // Process previous data
                if (!methodParsed){
                    LOGGER.debug("Request Line Method to Process: {}", processingDataBuffer.toString());
                    request.setMethod(processingDataBuffer.toString());
                    methodParsed = true;
                }else if(!requestTargetParsed){
                    LOGGER.debug("Request Line REQ Target to Process: {}", processingDataBuffer.toString());
                    requestTargetParsed= true;
                }

                processingDataBuffer.delete(0, processingDataBuffer.length());
            }else {
                processingDataBuffer.append((char) _byte);
            }

        }

    }


    private void parseHeaders(InputStreamReader reader, HttpRequest request) {
        
    }
    private void parseBody(InputStreamReader reader, HttpRequest request) {
    }



}
