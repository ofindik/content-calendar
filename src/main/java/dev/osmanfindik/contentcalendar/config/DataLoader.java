package dev.osmanfindik.contentcalendar.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.osmanfindik.contentcalendar.model.Content;
import dev.osmanfindik.contentcalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

//@Profile("!dev")
@Component
public class DataLoader implements CommandLineRunner {
	private final ContentRepository contentRepository;
	private final ObjectMapper objectMapper;

	public DataLoader (ContentRepository contentRepository, ObjectMapper objectMapper) {
		this.contentRepository = contentRepository;
		this.objectMapper = objectMapper;
	}

	@Override
	public void run (String... args) throws Exception {
		if (contentRepository.count () == 0) {
			try (InputStream inputStream = TypeReference.class.getResourceAsStream ("/data/content.json")) {
				contentRepository.saveAll (objectMapper.readValue (inputStream, new TypeReference<List<Content>> () {
				}));
			}
		}
	}
}
