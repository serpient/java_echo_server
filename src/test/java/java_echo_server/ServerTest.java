package java_echo_server;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    @Test
    public void serverEchosClientInput() {
        String testInput = "echo echo echo!";
        String returnedInput = "Echo Server: " + testInput;

        BufferedReader inputStream = new BufferedReader(new StringReader(testInput));
        MockWriter outputStream = new MockWriter();
        Socket clientSocket = new Socket();

        SocketWrapper socketWrapper = new Client(inputStream, outputStream, clientSocket);
        EchoServer echoServer = new EchoServer(socketWrapper);

        echoServer.start();

        assertEquals(returnedInput, outputStream.getSentData());
    }
}
