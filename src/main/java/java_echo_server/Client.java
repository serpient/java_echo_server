package java_echo_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private final BufferedReader inputStream;
    private final WriterWrapper outputStream;

    public Client(BufferedReader input, WriterWrapper output) {
        inputStream = input;
        outputStream = output;
    }

    public String readData() {
        try {
            return inputStream.readLine();
        } catch (IOException e) {
            System.err.println();
            return System.err.toString();
        }
    }

    public void sendData(String data) {
        outputStream.send(data);
    }
}
