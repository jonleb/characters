package org.jonleb.dicetower;


import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by jonathan on 30/10/17.
 */

@SpringBootApplication(
        scanBasePackages = {
                "org.jonleb.dicetower.config",
                "org.jonleb.dicetower.web.controller",
                "org.jonleb.dicetower.services"
        }
)
@Log4j2
public class SpringBootDiceTowerApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringBootDiceTowerApplication.class, args);
    }
}
