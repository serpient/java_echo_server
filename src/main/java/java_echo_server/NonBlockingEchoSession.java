package java_echo_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NonBlockingEchoSession implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private boolean isRunning;

    public NonBlockingEchoSession(Socket socket) {
        try {
            this.socket = socket;
            InputStream inputStream = socket.getInputStream();
            this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
            this.isRunning = true;
            System.err.println("creating session");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void run() {
        while (!Thread.interrupted() && isRunning) {
            try {
                if (!bufferedReader.ready()) {
                    try {
                        Thread.sleep(1000);
                        continue;
                    } catch (InterruptedException e) {
                        close();
                        return;
                    }
                }
                String inputLine = bufferedReader.readLine();
                String outputLine = "Echo Server: " + inputLine;
                printWriter.println(outputLine);
                System.err.println(outputLine);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    private void close() {
        try {
            isRunning = false;
            socket.close();
            System.out.println("Disconnecting client");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}