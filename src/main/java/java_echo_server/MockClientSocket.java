package java_echo_server;

import java.io.*;

public class MockClientSocket implements SocketWrapper {
    private final String input;
    private MockWriter mockWriter;

    public MockClientSocket(String input) {
        this.input = input;
        this.mockWriter = new MockWriter();
    }

    public BufferedReader getInput() {
        return new BufferedReader(new StringReader(input));
    }

    public WriterWrapper getOutput() {
        return mockWriter;
    }

    public String readData() {
        try {
            return getInput().readLine();
        } catch (IOException e) {
            return System.err.toString();
        }
    }

    public void sendData(String data) {
        mockWriter.send(data);
    }

    public String getSentData() {
        return mockWriter.getSentData();
    }

    public void close() {
        return;
    }

    public Boolean ready() { return true; }
}
