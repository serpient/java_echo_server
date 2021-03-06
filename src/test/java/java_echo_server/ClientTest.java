package java_echo_server;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class ClientTest {

    private final String testInput = "Hello";
    Socket mockClient = new Socket();
    BufferedReader input = new BufferedReader(new StringReader(testInput));
    MockWriter output = new MockWriter();
    private final ClientSocket clientSocket = new ClientSocket(mockClient, input, output);

    @Test
    public void clientReadsFromStream() {
        assertEquals(testInput, clientSocket.readData());
    }

    @Test
    public void clientWritesToStream() {
        clientSocket.sendData(testInput);

        assertEquals(testInput, output.getSentData());
    }

}
