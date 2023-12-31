package dev.osmanfindik.contentcalendar.controller;

import dev.osmanfindik.contentcalendar.model.Content;
import dev.osmanfindik.contentcalendar.model.Status;
import dev.osmanfindik.contentcalendar.repository.ContentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {
	private final ContentRepository repository;

	public ContentController (ContentRepository repository) {
		this.repository = repository;
	}

	@GetMapping("")
	public List<Content> findAll () {
		return repository.findAll ();
	}

	@GetMapping("/{id}")
	public Content findAll (@PathVariable Integer id) {
		return repository.findById (id).orElseThrow (() -> new ResponseStatusException (HttpStatus.NOT_FOUND, "Content not found"));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	public void define (@Valid @RequestBody Content content) {
		repository.save (content);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{id}")
	public void update (@PathVariable Integer id, @RequestBody Content content) {
		if (!repository.existsById (id)) {
			throw new ResponseStatusException (HttpStatus.NOT_FOUND, "Content not found");
		}
		repository.save (content);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Integer id) {
		repository.deleteById (id);
	}

	@GetMapping("/filter/{keyword}")
	public List<Content> findByTitle (@PathVariable String keyword) {
		return repository.findAllByTitleContains (keyword);
	}

	@GetMapping("/filter/status/{status}")
	public List<Content> findByStatus (@PathVariable Status status) {
		return repository.listByStatus (status);
	}
}
