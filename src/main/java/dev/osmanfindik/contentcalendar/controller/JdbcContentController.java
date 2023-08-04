package dev.osmanfindik.contentcalendar.controller;

import dev.osmanfindik.contentcalendar.model.Content;
import dev.osmanfindik.contentcalendar.repository.ContentJdbcTemplateRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content/jdbc")
@CrossOrigin
public class JdbcContentController {
	private final ContentJdbcTemplateRepository repository;

	public JdbcContentController (ContentJdbcTemplateRepository repository) {
		this.repository = repository;
	}

	@GetMapping("")
	public List<Content> findAll () {
		return repository.getAllContent ();
	}

	@GetMapping("/{id}")
	public Content findAll (@PathVariable Integer id) {
		return repository.getContent (id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	public void define (@Valid @RequestBody Content content) {
		repository.defineContent (content.title (), content.description (), content.status ().toString (), content.contentType ().toString (), content.url ());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{id}")
	public void update (@PathVariable Integer id, @RequestBody Content content) {
		if (repository.getContent (id) != null) {
			throw new ResponseStatusException (HttpStatus.NOT_FOUND, "Content not found");
		}
		repository.updateContent (content.id (), content.title (), content.description (), content.status ().toString (), content.contentType ().toString (), content.url ());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Integer id) {
		repository.deleteContent (id);
	}
}
