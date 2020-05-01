package com.danit.server;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import static java.nio.charset.StandardCharsets.US_ASCII;

public class WebServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(80);

        System.out.println("SERVER STARTED.");
        while (true) {
            System.out.println("WAIT FOR TCP CONNECTION...");
            Socket accept = serverSocket.accept();
            System.out.println("CONNECTED!");
            try ( OutputStream outputStream = accept.getOutputStream();
                 InputStream inputStream = accept.getInputStream()) {

                byte[] bytes = readBytes(inputStream);
                System.out.println(new String(bytes, US_ASCII));
                final String s = new Date().toString();

                outputStream.write(s.getBytes(US_ASCII));
                outputStream.flush();
            } finally {
                accept.close();
            }
        }
    }

    private static byte[] readBytes(InputStream inputStream) throws IOException {
        return IOUtils.toByteArray(inputStream);


//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        byte[] buffer = new byte[8048];
//        int length = 0;
//        while (true) {
//            int count = inputStream.read(buffer);
//            if(count < 0){
//                throw new RuntimeException("Incomming connection closed");
//            } else {
//                length += count;
////                if()
//            }
//
//        }
//
////        final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
//        while ((length = bufferedInputStream.read(buffer)) != -1) {
//            os.write(buffer, 0, length);
//        }
//        return os.toByteArray();
    }

}
