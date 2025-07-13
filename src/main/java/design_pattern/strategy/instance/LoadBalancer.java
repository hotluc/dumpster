package design_pattern.strategy.instance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoadBalancer {
    private static LoadBalancer instance = null;
    private List<String> serverList = null;

    private LoadBalancer() {
        serverList = new ArrayList<>();
    }
    public static LoadBalancer getLoadBalancer() {
        if (instance == null) {
            return new LoadBalancer();
        }
        return instance;
    }
    //增加服务器
    public void addServer(String server) {
        serverList.add(server);
    }
    //删除服务器
    public void removeServer(String server) {
        serverList.remove(server);
    }
    public String getServer() {
        Random random = new Random();
        int i = random.nextInt(serverList.size());
        return serverList.get(i);
    }
}
