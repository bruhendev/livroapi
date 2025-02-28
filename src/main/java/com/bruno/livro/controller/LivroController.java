package com.bruno.livro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.livro.service.LivroService;

@RestController
@RequestMapping("livros")
public class LivroController {
	
	@Autowired
	private LivroService service;

//	@PostMapping
//	public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
//		
//	}
}
