package main.com.hazelcastDemo.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.NearCacheConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import main.com.hazelcastDemo.domain.Car;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HazelcastCacheClient {
    private final HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(creatClientConfig());

    private static final String CARS = "cars";

    public byte[] put(Car car, byte[] out) {
        IMap<String, byte[]> map = hazelcastInstance.getMap(car.getCarBrand().name());
        return map.put(car.getRegNr(), out);
    }

//    public Car get(String key) {
//        IMap<String, Car> map = hazelcastInstance.getMap(CARS);
//        return map.get(key);
//    }

    private ClientConfig creatClientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.addNearCacheConfig(createNearCacheConfig());
        return clientConfig;
    }

    private NearCacheConfig createNearCacheConfig() {
        NearCacheConfig nearCacheConfig = new NearCacheConfig();
//        nearCacheConfig.setName(CARS);
        nearCacheConfig.setTimeToLiveSeconds(360);
        nearCacheConfig.setMaxIdleSeconds(60);
        return nearCacheConfig;
    }

}
