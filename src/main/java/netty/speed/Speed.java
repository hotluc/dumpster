package netty.speed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

public class Speed {
    private String getResourcePath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        //读取UTF-8

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            return null;
        }
        return resource.getPath();
    }
    public static void BIOReadTime() throws FileNotFoundException {
        File f = new File(new Speed().getResourcePath("DouPoCangQiong.txt"));
        FileInputStream in = new FileInputStream(f);
        long begin = System.currentTimeMillis();
        try(in){
            FileChannel channel = in.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (channel.read(buffer) != -1) {
                buffer.flip();
                buffer.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("BIO time is: " + (end - begin));
    }

    public static void NIOReadTime() throws IOException {
        int BUFFER_SIZE = 1024;
        File file = new File(new Speed().getResourcePath("DouPoCangQiong.txt"));
        FileChannel fileChannel = new FileInputStream(file).getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        byte[] b = new byte[1024];
        int length = (int) file.length();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < length; i += 1024) {
            if (length - i > BUFFER_SIZE) {
                mappedByteBuffer.get(b);
            } else {
                mappedByteBuffer.get(new byte[length - i]);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("NIO time is:" + (end - begin));
    }

    public static void main(String[] args) throws IOException {
        BIOReadTime();
        NIOReadTime();

    }
}
