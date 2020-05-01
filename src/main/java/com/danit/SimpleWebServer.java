package com.danit;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class SimpleWebServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(80);

        System.out.println("WAIT for tcp connection.");
        Socket accept = serverSocket.accept();
        System.out.println("Connection established.");

        InputStream inputStream = accept.getInputStream();
        OutputStream outputStream = accept.getOutputStream();

        String value = String.join("\n", IOUtils.readLines(inputStream, Charset.defaultCharset()));

        System.out.println(value);

        inputStream.close();
        accept.close();
        outputStream.close();

    }

}
