package java_echo_server;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class ClientTest {

    private final String testInput = "Hello";
    private final BufferedReader inputStream = new BufferedReader(new StringReader(testInput));
    private final MockWriter outputStream = new MockWriter();
    private final Client mockClientSocket = new Client(inputStream, outputStream);

    @Test
    public void clientReadsFromStream() {
        assertEquals(testInput, mockClientSocket.readData());
    }

    @Test
    public void clientWritesToStream() {
        mockClientSocket.sendData(testInput);

        assertEquals(testInput, outputStream.getSentData());
    }

}
