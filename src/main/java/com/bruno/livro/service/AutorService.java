package com.bruno.livro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.livro.model.Autor;
import com.bruno.livro.repository.AutorRepository;

@Service
public class AutorService {

	@Autowired
	private AutorRepository repository;
	
	public Autor salvar(Autor autor) {
		return repository.save(autor);
	}
}
