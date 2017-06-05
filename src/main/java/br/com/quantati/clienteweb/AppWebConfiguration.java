package br.com.quantati.clienteweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by carlos on 31/05/2017.
 */
@EnableCaching
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(AppWebConfiguration.class, args);
    }

}
