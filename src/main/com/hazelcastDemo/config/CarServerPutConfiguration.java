package main.com.hazelcastDemo.config;

import main.com.hazelcastDemo.domain.Car;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@Configuration
public class CarServerPutConfiguration {
    @Bean
    public Marshaller marshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

    @Bean
    public Unmarshaller unmarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Car.class);
        return context.createUnmarshaller();
    }
}
