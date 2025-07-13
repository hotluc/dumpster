package netty.study;

public class ServerBoot {
    private static final int port = 8000;

    public static void main(String[] args) {
        Server server = new Server(port);
        server.start();
    }
}
