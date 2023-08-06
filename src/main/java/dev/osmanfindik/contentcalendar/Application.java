package dev.osmanfindik.contentcalendar;

import dev.osmanfindik.contentcalendar.config.ContentCalendarProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

@EnableConfigurationProperties(ContentCalendarProperties.class)
@SpringBootApplication
public class Application {

	public static void main (String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run (Application.class, args);
		RestTemplate restTemplate = (RestTemplate) context.getBean ("restTemplate");
		System.out.println (restTemplate);
	}
}
