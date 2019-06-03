package java_echo_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) {
        int portNumber = setPortNumber(args);
        SocketWrapper clientSocketWrapper = createClientSocketWrapper(portNumber);

        EchoServer echoServer = new EchoServer(clientSocketWrapper);

        echoServer.start();
    }

    private static SocketWrapper createClientSocketWrapper(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();

            Boolean autoFlushPrintWriter = true;
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), autoFlushPrintWriter);

            WriterWrapper outputStream = new PrintWriterWrapper(printWriter);
            BufferedReader inputStream = new BufferedReader((new InputStreamReader(clientSocket.getInputStream())));

            return new Client(inputStream, outputStream, clientSocket);
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }

    private static int setPortNumber(String[] terminal_args) {
        return terminal_args.length > 0 ? Integer.parseInt(terminal_args[0]) : 1234;
    }
}
