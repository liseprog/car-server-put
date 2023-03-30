package main.com.hazelcastDemo.rest;

import main.com.hazelcastDemo.client.HazelcastCacheClient;
import main.com.hazelcastDemo.domain.Car;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;

@RestController
public class Controller {
    private final HazelcastCacheClient hazelcastCacheClient;
    private final Unmarshaller unmarshaller;
    private final Marshaller marshaller;

    public Controller(HazelcastCacheClient hazelcastCacheClient, Unmarshaller unmarshaller, Marshaller marshaller) {
        this.hazelcastCacheClient = hazelcastCacheClient;
        this.unmarshaller = unmarshaller;
        this.marshaller = marshaller;
    }

//    @GetMapping("/")
//    public String index() {
//        return "Greetings from Spring Boot!";
//    }
//
//    @PostMapping(value = "/{number}")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    public Car put(@RequestBody Car car, @PathVariable String number) {
//        return cacheClient.put(number, car);
//    }
//
//    @GetMapping(value = "/{number}")
//    public Car get(@PathVariable String number) {
//        return cacheClient.get(number);
//    }

    @PostMapping(value = "/car")
    public byte[] put(@RequestBody String xml) throws JAXBException {
        Car car = (Car) unmarshaller.unmarshal(new StringReader(xml));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        marshaller.marshal(car, out);
        return hazelcastCacheClient.put(car, out.toByteArray());
    }

}
