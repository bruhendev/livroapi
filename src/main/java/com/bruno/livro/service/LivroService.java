package com.bruno.livro.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bruno.livro.model.GeneroLivro;
import com.bruno.livro.model.Livro;
import com.bruno.livro.repository.LivroRepository;

import jakarta.persistence.Query;

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

	public void deletar(Livro livro) {
		repository.delete(livro);
	}

	public List<Livro> pesquisa(String isbn, String titulo, String nomeAutor, GeneroLivro genero,
			Integer anoPublicacao) {

		Specification<Livro> specs = null;
		
		Specification<Livro> isbnEqual = (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
		
		return repository.findAll();
	}

}
