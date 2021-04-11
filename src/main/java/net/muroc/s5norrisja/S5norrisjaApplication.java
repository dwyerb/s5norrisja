package net.muroc.s5norrisja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class S5norrisjaApplication {

    public static void main(String[] args) {
        SpringApplication.run(S5norrisjaApplication.class, args);
    }

}
