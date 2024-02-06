package com.khulekani.httpserver;

import com.khulekani.httpserver.config.Configuration;
import com.khulekani.httpserver.config.ConfigurationManager;
import com.khulekani.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
* Driver Class for the HTTP server
* */
public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    public static void main(String[] args) {

        LOGGER.info("Server Starting....");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        LOGGER.info("Using Port: " + conf.getPort());
        LOGGER.info("Using WebRoot: " + conf.getWebroot());


        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(),conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }


    }
}
