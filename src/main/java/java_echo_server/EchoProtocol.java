package java_echo_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoProtocol implements Runnable {
    private final ServerSocket server;

    public EchoProtocol(ServerSocket server) {
        this.server = server;
    }

    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }

    public void run() {
        long patience = 1000 * 60 * 10;

        threadMessage("Starting client thread");
        long startTime = System.currentTimeMillis();
        ThreadGroup clientGroup = new ThreadGroup("CLIENT_GROUP");

        while (true) {
            threadMessage("Waiting for connection...");
            Socket clientSocket;
            try {
                clientSocket = server.accept();
                ClientSocket clientSocketWrapper = new ClientSocket(clientSocket);
                EchoSession echoSession = new EchoSession(clientSocketWrapper);

                String threadName = "CLIENT_" + clientGroup.activeCount();
                Thread t = new Thread(clientGroup, echoSession, threadName);
                t.start();

                threadMessage("Started Client Thread = " + threadName);

                try {
                    while (t.isAlive()) {
                        threadMessage("Still waiting...");
                        t.join(100000);
                        if (((System.currentTimeMillis() - startTime) > patience)
                                && t.isAlive()) {
                            threadMessage("Taking too long! Closing Session.");
                            t.interrupt();
                            t.join();
                        }
                    }
                    threadMessage("Closed session.");
                } catch (InterruptedException e) {
                    threadMessage(e.toString());
                }

            } catch (IOException e) {
                threadMessage(e.toString());
            }
        }

    }
}
