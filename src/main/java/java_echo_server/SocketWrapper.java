package java_echo_server;

public interface SocketWrapper {
    String readData();
    void sendData(String data);
    void closeClient();
}
