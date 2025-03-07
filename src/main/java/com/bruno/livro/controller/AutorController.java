package com.bruno.livro.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bruno.livro.controller.dto.AutorDTO;
import com.bruno.livro.controller.dto.ErroResposta;
import com.bruno.livro.controller.mappers.AutorMapper;
import com.bruno.livro.exceptions.OperacaoNaoPermitidaException;
import com.bruno.livro.exceptions.RegistroDuplicadoException;
import com.bruno.livro.model.Autor;
import com.bruno.livro.service.AutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("autores")
public class AutorController implements GenericController {

	@Autowired
	private AutorService service;

	@Autowired
	private AutorMapper mapper;

	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody @Valid AutorDTO dto) {
		try {
			Autor autor = mapper.toEntity(dto);
			service.salvar(autor);
			URI location = gerarHeaderLocation(autor.getId());
			return ResponseEntity.created(location).build();
		} catch (RegistroDuplicadoException e) {
			var erroDto = ErroResposta.conflito(e.getMessage());
			return ResponseEntity.status(erroDto.status()).body(erroDto);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<Object> atualizar(@PathVariable String id, @RequestBody AutorDTO dto) {
		try {
			var idAutor = UUID.fromString(id);
			var autorOptional = service.obterPorId(idAutor);

			if (autorOptional.isEmpty()) {
				return ResponseEntity.notFound().build();
			}

			var autor = autorOptional.get();
			autor.setNome(dto.nome());
			autor.setNacionalidade(dto.nacionalidade());
			autor.setDataNascimento(dto.dataNascimento());

			service.atualizar(autor);

			return ResponseEntity.noContent().build();

		} catch (RegistroDuplicadoException e) {
			var erroDto = ErroResposta.conflito(e.getMessage());
			return ResponseEntity.status(erroDto.status()).body(erroDto);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable String id) {
		var idAutor = UUID.fromString(id);

		return service.obterPorId(idAutor).map(autor -> {
			AutorDTO dto = mapper.toDto(autor);
			return ResponseEntity.ok(dto);
		}).orElseGet(() -> ResponseEntity.notFound().build());

	}

	@GetMapping()
	public ResponseEntity<List<AutorDTO>> pesquisar(@RequestParam(required = false) String nome,
			@RequestParam(required = false) String nacionalidade) {
		var resultado = service.pesquisaByExample(nome, nacionalidade);
		var lista = resultado.stream().map(mapper::toDto).toList();
		return ResponseEntity.ok(lista);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> deletar(@PathVariable String id) {
		try {
			var idAutor = UUID.fromString(id);
			var autorOptional = service.obterPorId(idAutor);
			if (autorOptional.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			service.deletar(autorOptional.get());
			return ResponseEntity.noContent().build();
		} catch (OperacaoNaoPermitidaException e) {
			var erroResposta = ErroResposta.respostaPadrao(e.getMessage());
			return ResponseEntity.status(erroResposta.status()).body(erroResposta);
		}
	}
}
