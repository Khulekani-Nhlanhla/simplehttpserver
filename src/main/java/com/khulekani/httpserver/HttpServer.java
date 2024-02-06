package com.khulekani.httpserver;

import com.khulekani.httpserver.config.Configuration;
import com.khulekani.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
* Driver Class for the HTTP server
* */
public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Server Starting....");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using Port: " + conf.getPort());
        System.out.println("Using WebRoot: " + conf.getWebroot());

        /*Used to listen to a specific port*/
        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String html = "<html><head><title>Simple Java Http Server</title></head><body><h1>This page was served using my Simple java server</h1></body></html>";

            final String CRLF = "\n\r"; //13. 10

            String response = "HTTP/1.1 200 OK " + CRLF+ // Status Line : Http version Response_code Response Message
                                "Content-Length: " + html.getBytes().length + CRLF + //Header
                                    CRLF+
                                    html+
                                    CRLF + CRLF;
            outputStream.write(response.getBytes());
            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
