package java_echo_server;

public class MockServerSocket implements ServerSocketWrapper{
    public void close() {
        return;
    }
}
