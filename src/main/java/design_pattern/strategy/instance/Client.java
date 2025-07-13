package design_pattern.strategy.instance;

public class Client {
    public static void main(String[] args) {
        LoadBalancer loadBalancer1, loadBalancer2, loadBalancer3, loadBalancer4;
        loadBalancer1 = LoadBalancer.getLoadBalancer();
        loadBalancer2 = LoadBalancer.getLoadBalancer();
        loadBalancer3 = LoadBalancer.getLoadBalancer();
        loadBalancer4 = LoadBalancer.getLoadBalancer();
        if (loadBalancer1 == loadBalancer2 && loadBalancer2 == loadBalancer3 && loadBalancer3 == loadBalancer4) {
            System.out.println("负载均衡器具有唯一性。");
        }
        loadBalancer1.addServer("Server 1");
        loadBalancer1.addServer("Server 2");
        loadBalancer1.addServer("Server 3");
        loadBalancer1.addServer("Server 4");
        for (int i = 0; i < 10; i++) {
            String server = loadBalancer1.getServer();
            System.out.println("分发请求至服务器：" + server);
        }
    }
}
