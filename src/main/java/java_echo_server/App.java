package java_echo_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class App {
    public static void main(String[] args) throws InterruptedException {
        long timeToWait = 1000 * 5;
        long startTime = System.currentTimeMillis();

        // Starting client thread
        try (
            ServerSocket serverSocket = new ServerSocket(5000);
            Socket socket = serverSocket.accept()
        ) {
            NonBlockingEchoSession echoSession = new NonBlockingEchoSession(socket);
            Thread thread = new Thread(echoSession);
            System.err.println("thread starting");
            thread.start();
            while (thread.isAlive()) {
                if (threadTimer(startTime, timeToWait, thread)) {
                    System.err.println("thread taking too long, killing");
                    // Thread has outlived its timer
                    // Thus, Interrupt and terminate the thread
                    thread.interrupt();
                    thread.join();
                }
            }
            System.err.println("thread is dead");
        } catch (IOException e) {
            System.err.println("Error creating client.");
        }
    }

    private static boolean threadTimer(long startTime, long timeToWait, Thread thread) {
        return ((System.currentTimeMillis() - startTime) > timeToWait) && thread.isAlive();
    }
}