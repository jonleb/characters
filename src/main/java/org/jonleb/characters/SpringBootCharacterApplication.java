package org.jonleb.characters;


import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.executable.ExecutableValidator;

/**
 * Created by jonathan on 30/10/17.
 */

@SpringBootApplication
@Log4j2
public class SpringBootCharacterApplication {
    private static ExecutableValidator executableValidator;
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootCharacterApplication.class, args);
    }
}
