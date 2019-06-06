package java_echo_server;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    @Test
    public void serverEchosClientInput() {
        String testInput = "echo echo echo!";
        String returnedInput = "Echo Server: " + testInput;

        BufferedReader inputStream = new BufferedReader(new StringReader(testInput));
        MockWriter outputStream = new MockWriter();

        Client mockClientSocket = new Client(inputStream, outputStream);
        EchoServer echoServer = new EchoServer(mockClientSocket);

        echoServer.start();

        assertEquals(returnedInput, outputStream.getSentData());
    }
}
