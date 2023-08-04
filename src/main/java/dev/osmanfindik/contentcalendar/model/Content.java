package dev.osmanfindik.contentcalendar.model;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record Content(
		Integer id,
		@NotBlank
		String title,
		String description,
		Status status,
		Type contentType,
		LocalDateTime dateCreated,
		LocalDateTime dateUpdate,
		String url
) {
}
