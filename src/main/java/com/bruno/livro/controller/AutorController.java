package com.bruno.livro.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bruno.livro.controller.dto.AutorDTO;
import com.bruno.livro.model.Autor;
import com.bruno.livro.service.AutorService;

@RestController
@RequestMapping("autores")
public class AutorController {

	@Autowired
	private AutorService service;

	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor) {
		Autor autorEntity = autor.mapearParaAutor();
		service.salvar(autorEntity);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autorEntity.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
