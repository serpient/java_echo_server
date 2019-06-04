package java_echo_server;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServerSocket implements ServerSocketWrapper {
    private ServerSocket serverSocket;

    public EchoServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void close() {
        try {
            System.out.println("Closing server socket");
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Could not close server socket");
        }
    }
}
