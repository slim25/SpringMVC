package mentorship.program;

/**
 * Created by Oleksandr_Tertyshnyi on 9/21/2016.
 */

import mentorship.program.config.WebMvcConfig;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;


@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories("mentorship.program.repository")
@Import(value = {WebMvcConfig.class})
public class MainApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MainApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @PostConstruct
    public void startDBManager() {
        //hsqldb
        System.setProperty("java.awt.headless", "false");
        DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:file:mentorshipProgram", "--user", "sa", "--password", "" });

    }


}