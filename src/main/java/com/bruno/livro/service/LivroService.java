package com.bruno.livro.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.livro.model.Livro;
import com.bruno.livro.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;

	public Livro salvar(Livro livro) {
		return repository.save(livro);
	}
	
	public Optional<Livro> obterPorId(UUID id) {
		 return repository.findById(id);
	}
	
	public void deletar(Livro livro){
        repository.delete(livro);
    }

}
