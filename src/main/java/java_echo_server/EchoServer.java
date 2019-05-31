package java_echo_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public SocketWrapper clientSocketWrapper;
    public Socket clientSocket;

    public EchoServer(SocketWrapper socketWrapper) {
        this.clientSocketWrapper = socketWrapper;
    }

    public EchoServer() {
        this.clientSocketWrapper = null;
    }

    public void start(int port) {
        String inputLine;

        if (clientSocketWrapper == null) {
            createClientSocketWrapper(port);
        }

        while ((inputLine = clientSocketWrapper.readData()) != null) {
            if (inputLine.equals("Stop")) {
                try {
                    clientSocket.close();
                    System.out.println("The server is shut down");
                } catch (IOException e) {
                    System.err.println("The server could not be shut down");
                }
                break;
            }

            String outputLine = "Echo Server: " + inputLine;
            clientSocketWrapper.sendData(outputLine); // send back to client input stream
        }
    }

    private void createClientSocketWrapper(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();

            PrintWriter outputStream = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader inputStream = new BufferedReader((new InputStreamReader(clientSocket.getInputStream())));

            clientSocketWrapper = new Client(inputStream, outputStream);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
