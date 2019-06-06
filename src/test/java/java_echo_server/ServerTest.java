package java_echo_server;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    String testInput = "echo echo echo!";
    String returnedInput = "Echo Server: " + testInput;
    MockClientSocket mockClientSocket = new MockClientSocket(testInput);
    EchoSession echoSession = new EchoSession(mockClientSocket);

    @Test
    public void serverEchosClientInput() {
        echoSession.run();
        assertEquals(returnedInput, mockClientSocket.getSentData());
        echoSession.close();
    }
}
