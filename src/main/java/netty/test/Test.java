package netty.test;



public class Test {
    private static volatile ChannelFactory channelFactory;

    public static void main(String[] args) {
        Channel channel = null;
        channel = channelFactory.newChannel();
        System.out.println(channel);
    }

}
