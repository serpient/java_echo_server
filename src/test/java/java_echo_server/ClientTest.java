package java_echo_server;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class ClientTest {
    @Test
    public void clientReadsFromStream() {
        String testInput = "Hello";
        BufferedReader inputStream = new BufferedReader(new StringReader(testInput));
        PrintWriter outputStream = new PrintWriter(new StringWriter());
        MockSocket mockClientSocket = new MockSocket(inputStream, outputStream);

        assertEquals(testInput, mockClientSocket.readData());
    }

    @Test
    public void clientWritesToStream() {
        String testInput = "Writing To Stream";
        BufferedReader inputStream = new BufferedReader(new StringReader(testInput));
        PrintWriter outputStream = new PrintWriter(new StringWriter());
        MockSocket mockClientSocket = new MockSocket(inputStream, outputStream);

        mockClientSocket.sendData(testInput);
        assertEquals(testInput, mockClientSocket.getSentData());
    }

}
