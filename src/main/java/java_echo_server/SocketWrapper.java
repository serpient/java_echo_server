package java_echo_server;

import java.io.BufferedReader;

public interface SocketWrapper {
    public WriterWrapper getOutput();
    public BufferedReader getInput();
    public String readData();
    public void sendData(String data);
    public void close();
    public Boolean ready();
}
