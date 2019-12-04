import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Random;

class IpPool {
    public static Map<String, Integer> ipMap = new ConcurrentHashMap<>();

    static {
        ipMap.put("172.16.30.112", 2);
        ipMap.put("172.16.30.114", 10);
        ipMap.put("192.168.1.3", 5);
        ipMap.put("192.168.1.4", 6);
        ipMap.put("192.168.1.5", 10);
        ipMap.put("192.168.1.6", 10);
        ipMap.put("192.168.1.7", 3);
        ipMap.put("192.168.1.8", 1);
        ipMap.put("192.168.1.9", 7);
        ipMap.put("192.168.1.10", 8);
    }
}
interface LoadBalance {
    String getServer(String clientIp);
}

class RandomLoadBalance implements LoadBalance {

    @Override
    public String getServer(String clientIp) {
        Set<String> servers = IpPool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();
        serverList.addAll(servers);
        int randomIndex = new Random().nextInt(serverList.size());
        String target = serverList.get(randomIndex);

        return target;
    }
}

public class LoadBalanceMain {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        loadBalance();
    }

    public static void loadBalance() {
        doGetServer(new RandomLoadBalance());
    }


    public static void doGetServer(LoadBalance loadBalance) {
        doGetServer(loadBalance, 30);
    }

    private static void doGetServer(LoadBalance loadBalance, int queryTimes) {
        for (int i = 0; i < queryTimes; i++) {
            String serverId = loadBalance.getServer(String.valueOf(i));
            System.out.println(String.format("[%s] index:%s,%s", loadBalance.getClass().getSimpleName(), i, serverId));
        }
    }
}
