package java_echo_server;

public class EchoServer {
    private final Client clientSocket;

    public EchoServer(Client clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void start() {
        String inputLine;

        while ((inputLine = clientSocket.readData()) != null) {
            if (inputLine.equals("Stop")) {
                clientSocket.closeClient();
                break;
            }

            String outputLine = "Echo Server: " + inputLine;
            clientSocket.sendData(outputLine);
        }
    }

}
