package dev.osmanfindik.contentcalendar.repository;

import dev.osmanfindik.contentcalendar.model.Content;
import org.springframework.data.repository.ListCrudRepository;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {
}
