package java_echo_server;

public class EchoSession implements Runnable {
    private final SocketWrapper client;

    public EchoSession(SocketWrapper client) {
        this.client = client;
    }

    public void run() {
        String inputLine;
        while (!Thread.interrupted()) {
            if (!client.ready()) {
                try {
                    Thread.sleep(1000);
                    continue;
                } catch (InterruptedException e) {
                    client.close();
                    System.err.println("Disconnecting client.");
                    return;
                }
            }
            inputLine = client.readData();
            if (inputLine.equals("Stop")) {
                break;
            } else {
                String outputLine = "Echo Server: " + inputLine;
                client.sendData(outputLine);
            }
        }
    }
}

// 2 different threads can access the same server in memory
// data sharing between threads
// gotta be careful where server.start(), and server.stop() is called immediately
// then its a race condition

// to not make it a race condition
// semiphores ? signaling?
// then use another thread to stop that thread

// or busy loop until server is started
// then set up signals with variables
// once the ENUMS state clears in a test condition
// then call server STOP

// or another way is to be more explicit
// can the test framework

