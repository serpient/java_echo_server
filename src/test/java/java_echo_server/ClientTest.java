package java_echo_server;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class ClientTest {

    private final String testInput = "Hello";
    private final MockClientSocket mockClientSocket = new MockClientSocket(testInput);

    @Test
    public void clientReadsFromStream() {
        assertEquals(testInput, mockClientSocket.readData());
    }

    @Test
    public void clientWritesToStream() {
        mockClientSocket.sendData(testInput);

        assertEquals(testInput, mockClientSocket.getSentData());
    }

}
