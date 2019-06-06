package java_echo_server;

import java.io.BufferedReader;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;

public class ClientSocket implements SocketWrapper {
    private final Socket clientSocket;
    private BufferedReader inputStream;
    private WriterWrapper outputStream;

    public ClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            Boolean autoFlushWriter = true;
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), autoFlushWriter);
            this.outputStream = new PrintWriterWrapper(printWriter);
            this.inputStream = new BufferedReader((new InputStreamReader(clientSocket.getInputStream())));
        } catch (IOException e) {
            System.err.println("Could not create input and output stream.");
        }
    }

    public String readData() {
        try {
            return inputStream.readLine();
        } catch (IOException e) {
            System.err.println(e.toString());
            return e.toString();
        }
    }

    public void sendData(String data) {
        outputStream.send(data);
    }

    public void close() {
        try {
            inputStream.close();
            outputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
