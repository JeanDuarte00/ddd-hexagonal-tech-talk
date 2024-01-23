package org.example.domain.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IEntrypointAPI<I, T> {
	ResponseEntity delete(@PathVariable I id);

	ResponseEntity get(@PathVariable I id);

	ResponseEntity<List<T>> getAll(@RequestParam Integer page);
}