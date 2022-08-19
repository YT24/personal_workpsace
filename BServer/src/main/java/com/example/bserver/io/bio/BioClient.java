package com.example.bserver.io.bio;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioClient {
    public static void main(String[] args) {
        Socket client = null;
        try {
            client = new Socket("127.0.0.1",8888);
            client.getOutputStream().write("helloo server,I am robot !!!".getBytes());
            client.getOutputStream().flush();

            System.out.println("waiting for msg back ......");

            byte[] b = new byte[1024];
            int length = client.getInputStream().read(b);
            System.out.println(new String(b,0,length));
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
