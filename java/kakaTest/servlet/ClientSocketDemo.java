package kakaTest.servlet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientSocketDemo {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            Socket socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
