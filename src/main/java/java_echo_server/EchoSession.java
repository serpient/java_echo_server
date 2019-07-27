package java_echo_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoSession implements Runnable {
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public EchoSession(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void run() {
        try {
            String inputLine;
            while (!Thread.interrupted() && (inputLine = bufferedReader.readLine()) != null) {
                String outputLine = "Echo Server: " + inputLine;
                System.err.println(outputLine);
                printWriter.println(outputLine);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}


//public class EchoSession implements Runnable {
//    private final SocketWrapper client;
//    private Boolean isRunning;
//
//    public EchoSession(SocketWrapper client) {
//        this.client = client;
//        this.isRunning = true;
//    }
//
//    public void run() {
//        while (!Thread.interrupted() && isRunning) {
//            String outputLine = "Echo Server: " + client.readData();
//            System.err.println(outputLine);
//            client.sendData(outputLine);
//            close();
//        }
//    }
//
//    public void close() {
//        client.close();
//        isRunning = false;
//        System.out.println("Disconnecting client");
//    }
//}
