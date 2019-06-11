package java_echo_server;

public class EchoSession implements Runnable {
    private final SocketWrapper client;
    private Boolean isRunning;

    public EchoSession(SocketWrapper client) {
        this.client = client;
        this.isRunning = true;
    }

    public void run() {
        while (!Thread.interrupted() && isRunning) {
            String outputLine = "Echo Server: " + client.readData();
            client.sendData(outputLine);
            close();
        }
    }

    public void close() {
        client.close();
        isRunning = false;
        System.out.println("Disconnecting client");
    }
}
