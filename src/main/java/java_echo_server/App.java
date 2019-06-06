package java_echo_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;

    public static void main(String[] args) {
        int portNumber = setPortNumber(args);
        Client client = createClientSocketWrapper(portNumber);

        EchoServer echoServer = new EchoServer(client);

        do {
            echoServer.start();
        } while (echoServer.isRunning());
        stopServer();
    }

    private static int setPortNumber(String[] terminal_args) {
        return terminal_args.length > 0 ? Integer.parseInt(terminal_args[0]) : 1234;
    }

    private static Client createClientSocketWrapper(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();

            Boolean autoFlushWriter = true;
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), autoFlushWriter);
            WriterWrapper outputStream = new PrintWriterWrapper(printWriter);
            BufferedReader inputStream = new BufferedReader((new InputStreamReader(clientSocket.getInputStream())));

            return new Client(inputStream, outputStream);

        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }

    private static void stopServer() {
        try {
            System.out.println("The server is shutting down.");
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("The server could not be shut down.");
        }
    }
}
