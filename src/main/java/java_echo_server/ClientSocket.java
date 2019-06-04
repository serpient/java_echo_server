package java_echo_server;

import java.io.*;
import java.net.Socket;

public class ClientSocket implements SocketWrapper {
    private final Socket clientSocket;
    private BufferedReader inputStream;
    private WriterWrapper outputStream;

    public ClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            Boolean autoFlushWriter = true;
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), autoFlushWriter);
            outputStream = new PrintWriterWrapper(printWriter);
            inputStream = new BufferedReader((new InputStreamReader(clientSocket.getInputStream())));
        } catch (IOException e) {
            System.err.println("Could not create input and output stream.");
        }
    }

    public BufferedReader getInput() {
        return inputStream;
    }

    public WriterWrapper getOutput() {
        return outputStream;
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

    public void close() {
        try {
            System.out.println("closing client socket");
            inputStream.close();
            outputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Could not close client socket.");
        }
    }
}
