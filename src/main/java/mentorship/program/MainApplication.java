package mentorship.program;

/**
 * Created by Oleksandr_Tertyshnyi on 9/21/2016.
 */

import mentorship.program.aspect.LoggingAspect;
import mentorship.program.config.WebMvcConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.io.IOException;


@SpringBootApplication
@Configuration
@ComponentScan(basePackages="mentorship.program")
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableJpaRepositories("mentorship.program.repository")
@Import(value = {WebMvcConfig.class})
public class MainApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainApplication.class);
    }


}