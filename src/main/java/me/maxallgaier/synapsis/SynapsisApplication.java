package me.maxallgaier.synapsis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SynapsisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SynapsisApplication.class, args);
    }
}
