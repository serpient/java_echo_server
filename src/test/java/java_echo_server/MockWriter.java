package java_echo_server;

public class MockWriter implements WriterWrapper {
    private String sentData;

    public void send(String data) {
        sentData = data;
    }
    public String getSentData() {
        return sentData;
    }
}

