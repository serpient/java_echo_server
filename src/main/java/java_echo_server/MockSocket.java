package java_echo_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MockSocket implements SocketWrapper {
    public BufferedReader inputStream;
    public PrintWriter outputStream;
    public String sentData;

    public MockSocket(BufferedReader input, PrintWriter output) {
        inputStream = input;
        outputStream = output;
    }

    public String readData() {
        try {
            return inputStream.readLine();
        } catch (IOException e) {
            System.err.println(e);
            return System.err.toString();
        }
    }

    public void sendData(String data) {
        sentData = data;
        outputStream.println(data);
    }

    public String getSentData() {
        return sentData;
    }
}
