package com.bruno.livro.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.livro.controller.dto.CadastroLivroDTO;
import com.bruno.livro.controller.mappers.LivroMapper;
import com.bruno.livro.model.Livro;
import com.bruno.livro.service.LivroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("livros")
public class LivroController implements GenericController {

	@Autowired
	private LivroService service;

	@Autowired
	private LivroMapper mapper;

	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
		Livro livro = mapper.toEntity(dto);
		service.salvar(livro);
		URI url = gerarHeaderLocation(livro.getId());
		return ResponseEntity.created(url).build();
	}
}
