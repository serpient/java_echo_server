package java_echo_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) {
        int portNumber = setPortNumber(args);
        try (
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
        ) {
            ClientSocket clientSocketWrapper = new ClientSocket(clientSocket);
            EchoServer echoServer = new EchoServer(clientSocketWrapper, new EchoServerSocket(serverSocket));
            echoServer.start();
        } catch (IOException e) {
            System.err.println("Could not create server.");
        }
    }

    private static int setPortNumber(String[] terminal_args) {
        return terminal_args.length > 0 ? Integer.parseInt(terminal_args[0]) : 1234;
    }

}
