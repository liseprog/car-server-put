package main.com.hazelcast.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.NearCacheConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import main.com.hazelcast.rest.Car;
import org.springframework.stereotype.Component;

@Component
public class CacheClient {
    private final HazelcastInstance client = HazelcastClient.newHazelcastClient(creatClientConfig());

    private static final String CARS = "cars";

    public Car put(String key, Car car) {
        IMap<String, Car> map = client.getMap(CARS);
        return map.putIfAbsent(key, car);
    }

    public Car get(String key) {
        IMap<String, Car> map = client.getMap(CARS);
        return map.get(key);
    }

    private ClientConfig creatClientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.addNearCacheConfig(createNearCacheConfig());
        return clientConfig;
    }

    private NearCacheConfig createNearCacheConfig() {
        NearCacheConfig nearCacheConfig = new NearCacheConfig();
        nearCacheConfig.setName(CARS);
        nearCacheConfig.setTimeToLiveSeconds(360);
        nearCacheConfig.setMaxIdleSeconds(60);
        return nearCacheConfig;
    }

}
