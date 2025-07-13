package netty.study;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.Socket;

public class ClientHandler {
    public static final int MAX_DATA_LENGTH = 1024;
    private final Socket socket;
    public ClientHandler(Socket client) {
        this.socket = client;
    }
    public void start() {
        System.out.println("新客户端接入");
        new Thread(this::doStart).start();
        BigInteger integer  = new BigInteger("1234567890");
        integer.add(new BigInteger("1234567890")).toString();
    }

    private void doStart() {
        while (true){
            try {
                InputStream inputStream = socket.getInputStream();
                while (true){
                    byte[] data = new byte[MAX_DATA_LENGTH];
                    int len;
                    while ((len = inputStream.read(data)) != -1){
                        String message = new String(data, 0, len);
                        System.out.println("客户端传来消息：" + message);
                        socket.getOutputStream().write(data);
                    }
                }
            }
            catch (IOException e) {
                System.out.println("服务端连接异常");
                e.printStackTrace();
            }
        }
    }
}
