package sk.stuba.fei.oop.springinsurances.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sk.stuba.fei.oop.springinsurances.user.service.UserService;
import sk.stuba.fei.oop.springinsurances.web.requests.UserResource;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EnableWebMvc
@Configuration
public class Configurator implements WebMvcConfigurer {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private final UserService userService;

    @Autowired
    public Configurator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //noinspection Convert2Lambda
        registry.addConverter(new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String dateTimeString) {
                return LocalDateTime.parse(dateTimeString, formatter);
            }
        });

        registry.addConverter(new Converter<LocalDateTime, String>() {
            @Override
            public String convert(LocalDateTime dateTime){
                return dateTime.format(formatter);
            }
        });

        registry.addConverter(new Converter<String, @Valid UserResource>() {
            @Override
            public UserResource convert(String s) {
                int idx = s.indexOf("=");
                String sub = s.substring(0, idx);
                Long getId = Long.parseLong(sub);
                return userService.getUsersResources().get(getId);
            }
        });
    }
}
