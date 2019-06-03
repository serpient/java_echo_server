package java_echo_server;

import java.io.PrintWriter;

public class PrintWriterWrapper implements WriterWrapper {
    private final PrintWriter printWriter;

    public PrintWriterWrapper(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }
    public void send(String data) {
       printWriter.println(data);
    }
}
