package java_echo_server;

public class App {
    public static void main(String[] args) {
        int portNumber = args.length > 0 ? Integer.parseInt(args[0]) : 1234;
        EchoServer echoServer = new EchoServer();
        echoServer.start(portNumber);
    }
}
