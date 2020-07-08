package kakaTest.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ServerSocketDemo {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8081);
            OutputStream outputStream = socket.getOutputStream();
            int i = 1;
            while (true){
                System.out.println(i++);
                outputStream.write("123".getBytes());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
