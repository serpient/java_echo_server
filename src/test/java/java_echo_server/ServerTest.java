package java_echo_server;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    String testInput = "echo echo echo!";
    String returnedInput = "Echo Server: " + testInput;
    MockClientSocket mockClientSocket = new MockClientSocket(testInput);
    ServerSocketWrapper mockServerSocket = new MockServerSocket();
    EchoServer echoServer = new EchoServer(mockClientSocket, mockServerSocket);

//    @Ignore("Infinity Loop")
    @Test
    public void serverStops() {
        echoServer.start();
        echoServer.stop();
        assertEquals(echoServer.serverIsRunning(), false);
    }


    @Ignore("Infinity Loop")
    @Test
    public void serverEchosClientInput() {
        echoServer.start();
        assertEquals(returnedInput, mockClientSocket.getSentData());
        echoServer.stop();
    }
}
