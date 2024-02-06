package com.khulekani.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread{
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    private Socket socket;
    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run(){
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String html = "<html><head><title>Simple Java Http Server</title></head><body><h1>This page was served using my Simple java server</h1></body></html>";

            final String CRLF = "\n\r"; //13. 10

            String response = "HTTP/1.1 200 OK " + CRLF + // Status Line : Http version Response_code Response Message
                    "Content-Length: " + html.getBytes().length + CRLF + //Header
                    CRLF +
                    html +
                    CRLF + CRLF;
            outputStream.write(response.getBytes());
            inputStream.close();
            outputStream.close();
            socket.close();

            LOGGER.info("Conection Processing Finished.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }
}
