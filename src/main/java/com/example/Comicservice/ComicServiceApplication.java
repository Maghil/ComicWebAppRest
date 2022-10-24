package com.example.Comicservice;

import com.example.Comicservice.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example.Comicservice"})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ComicServiceApplication {

    @Autowired
    private ComicService comicService;

    public static void main(String[] args) {
        SpringApplication.run(ComicServiceApplication.class, args);
    }

/*	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> comicService.getComic();
	}*/

}
