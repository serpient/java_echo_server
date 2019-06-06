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
            if (!client.ready()) {
                try {
                    Thread.sleep(1000);
                    continue;
                } catch (InterruptedException e) {
                    close();
                    return;
                }
            }
            String inputLine = client.readData();
            if (inputLine.equals("Stop")) {
                close();
            } else {
                String outputLine = "Echo Server: " + inputLine;
                client.sendData(outputLine);
                System.out.println(outputLine);
                close();
            }
        }
        return;
    }

    public void close() {
        client.close();
        isRunning = false;
        System.out.println("Disconnecting client.");
    }
}
