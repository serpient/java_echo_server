package java_echo_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements SocketWrapper {
    private final BufferedReader inputStream;
    private final WriterWrapper outputStream;
    private final Socket clientSocket;

    public Client(BufferedReader input, WriterWrapper output, Socket client) {
        inputStream = input;
        outputStream = output;
        clientSocket = client;
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

    public void closeClient() {
        try {
            System.out.println("The server is shutting down.");
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("The server could not be shut down.");
        }
    }
}
