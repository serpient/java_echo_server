package java_echo_server;

public class EchoServer {
    private final Client clientSocket;
    private boolean serverState;

    public EchoServer(Client clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void start() {
        String inputLine;
        serverState = true;
        while ((inputLine = clientSocket.readData()) != null) {
            if (inputLine.equals("Stop")) {
                serverState = false;
                break;
            }

            String outputLine = "Echo Server: " + inputLine;
            clientSocket.sendData(outputLine);
        }
    }

    public boolean isRunning() {
        return serverState;
    }
}
