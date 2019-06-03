package java_echo_server;

public class EchoServer {
    private final SocketWrapper clientSocketWrapper;

    public EchoServer(SocketWrapper socketWrapper) {
        this.clientSocketWrapper = socketWrapper;
    }

    public void start() {
        String inputLine;

        while ((inputLine = clientSocketWrapper.readData()) != null) {
            if (inputLine.equals("Stop")) {
                clientSocketWrapper.closeClient();
                break;
            }

            String outputLine = "Echo Server: " + inputLine;
            clientSocketWrapper.sendData(outputLine);
        }
    }

}
