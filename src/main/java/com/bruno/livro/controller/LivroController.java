package com.bruno.livro.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.livro.controller.dto.CadastroLivroDTO;
import com.bruno.livro.controller.dto.ResultadoPesquisaLivroDTO;
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

	@GetMapping("{id}")
	public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable String id) {
		return service.obterPorId(UUID.fromString(id)).map(livro -> {
			var dto = mapper.toDTO(livro);
			return ResponseEntity.ok(dto);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> deletar(@PathVariable String id) {
		return service.obterPorId(UUID.fromString(id)).map(livro -> {
			service.deletar(livro);
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
