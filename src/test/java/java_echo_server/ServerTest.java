package java_echo_server;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    @Test
    public void serverEchosClientInput() {
        int port = 1234;
        String testInput = "echo echo echo!";
        String returnedInput = "Echo Server: " + testInput;

        BufferedReader inputStream = new BufferedReader(new StringReader(testInput));
        PrintWriter outputStream = new PrintWriter(new StringWriter());

        SocketWrapper socketWrapper = new MockSocket(inputStream, outputStream);
        EchoServer echoServer = new EchoServer(socketWrapper);

        echoServer.start(port);

        assertEquals(returnedInput, socketWrapper.getSentData());
    }
}
