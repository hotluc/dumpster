package hash;

import java.util.*;

/**
 * 节点类
 */
public class Node {
    private static final int VIRTUAL_NODE_NO_PER_NODE = 200;
    private final String ip;
    private final List<Integer> virtualNodeHashList = new ArrayList<>(VIRTUAL_NODE_NO_PER_NODE);
    private final Map<Object, Object> cacheMap = new HashMap<>();
    public Node(String ip) {
        Objects.requireNonNull(ip);
        this.ip = ip;
        initVirtualNodes();
    }
    public void initVirtualNodes(){
        String virtualNodeKey;
        for (int i = 1; i <= VIRTUAL_NODE_NO_PER_NODE; i++) {
            virtualNodeKey = ip + "#" + i;
            virtualNodeHashList.add(HashUtils.hashcode(virtualNodeKey));
        }
    }
    public void addCacheItem(Object key, Object value) {
        cacheMap.put(key, value);
    }
    public Object getCacheItem(Object key) {
        return cacheMap.get(key);
    }
    public void removeCacheItem(Object key) {
        cacheMap.remove(key);
    }


    public List<Integer> getVirtualNodeHashes() {
        return virtualNodeHashList;
    }

    public String getIp() {
        return ip;
    }
}
