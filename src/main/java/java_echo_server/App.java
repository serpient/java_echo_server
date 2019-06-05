package java_echo_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
        static void threadMessage(String message) {
            String threadName = Thread.currentThread().getName();
            System.out.format("%s: %s%n", threadName, message);
        }

        public static void main(String args[])
                throws InterruptedException {

            long patience = 1000 * 60 * 5;

            threadMessage("Starting client thread");
            long startTime = System.currentTimeMillis();

            try (
                ServerSocket serverSocket = new ServerSocket(setPortNumber(args));
                Socket clientSocket = serverSocket.accept()
            ) {
                threadMessage("Creating Client...");
                ClientSocket clientSocketWrapper = new ClientSocket(clientSocket);
                EchoSession echoSession = new EchoSession(clientSocketWrapper);

                Thread t = new Thread(echoSession);
                t.start();

                threadMessage("Waiting for ClientLoop thread to finish");

                while (t.isAlive()) {
                    threadMessage("Still waiting...");
                    t.join(10000);
                    if (((System.currentTimeMillis() - startTime) > patience)
                            && t.isAlive()) {
                        threadMessage("Tired of waiting!");
                        t.interrupt();
                        t.join();
                    }
                }
            } catch (IOException e) {
                System.err.println("Error creating client.");
            }

            threadMessage("Closed Server and Sessions");
        }

        private static int setPortNumber(String[] terminal_args) {
            return terminal_args.length > 0 ? Integer.parseInt(terminal_args[0]) : 1234;
        }
    }