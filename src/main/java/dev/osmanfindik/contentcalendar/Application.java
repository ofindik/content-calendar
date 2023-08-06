package dev.osmanfindik.contentcalendar;

import dev.osmanfindik.contentcalendar.config.ContentCalendarProperties;
import dev.osmanfindik.contentcalendar.model.Content;
import dev.osmanfindik.contentcalendar.model.Status;
import dev.osmanfindik.contentcalendar.model.Type;
import dev.osmanfindik.contentcalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@EnableConfigurationProperties(ContentCalendarProperties.class)
@SpringBootApplication
public class Application {

	public static void main (String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run (Application.class, args);
		RestTemplate restTemplate = (RestTemplate) context.getBean ("restTemplate");
		System.out.println (restTemplate);
	}

	@Bean
	CommandLineRunner commandLineRunner (ContentRepository contentRepository) {
		return args -> {
			// insert some data to database
			Content content = new Content (
					null,
					"My Eleventh Blog Post",
					"My Eleventh Blog Post",
					Status.IDEA,
					Type.ARTICLE,
					LocalDateTime.now (),
					null,
					"");
			contentRepository.save (content);
		};
	}
}
