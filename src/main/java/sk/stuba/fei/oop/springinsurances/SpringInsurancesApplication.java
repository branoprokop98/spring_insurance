package sk.stuba.fei.oop.springinsurances;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
@Slf4j
public class SpringInsurancesApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringInsurancesApplication.class, args);
        log.info("Open in browser: http://localhost:8080/register");
    }
}
