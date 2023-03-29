package main.com.hazelcast.rest;

import main.com.hazelcast.client.CacheClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cars")
public class Controller {
    private final CacheClient cacheClient;

    public Controller(CacheClient cacheClient) {
        this.cacheClient = cacheClient;
    }

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping(value = "/{number}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Car put(@RequestBody Car car, @PathVariable String number) {
        return cacheClient.put(number, car);
    }

    @GetMapping(value = "/{number}")
    public Car get(@PathVariable String number) {
        return cacheClient.get(number);
    }

}
