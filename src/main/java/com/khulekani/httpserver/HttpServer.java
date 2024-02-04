package com.khulekani.httpserver;

import com.khulekani.httpserver.config.ConfigurationManager;

/*
* Driver Class for the HTTP server
* */
public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Server Starting....");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
    }
}
