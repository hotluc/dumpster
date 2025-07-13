package netty.test;

public interface ChannelFactory<T extends Channel> {
    T newChannel();
}
