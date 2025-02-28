package com.bruno.livro.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.bruno.livro.exceptions.OperacaoNaoPermitidaException;
import com.bruno.livro.model.Autor;
import com.bruno.livro.repository.AutorRepository;
import com.bruno.livro.repository.LivroRepository;
import com.bruno.livro.validator.AutorValidator;

@Service
public class AutorService {

	@Autowired
	private AutorRepository repository;

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorValidator validator;

	public Autor salvar(Autor autor) {
		validator.validar(autor);
		return repository.save(autor);
	}

	public void atualizar(Autor autor) {
		if (autor.getId() == null) {
			throw new IllegalArgumentException("Para atualizar, é necessário que o autor já esteja salvo na base.");
		}
		validator.validar(autor);
		repository.save(autor);
	}

	public Optional<Autor> obterPorId(UUID id) {
		return repository.findById(id);
	}

	public List<Autor> pesquisar(String nome, String nacionalidade) {
		if (nome != null && nacionalidade != null) {
			return repository.findByNomeAndNacionalidade(nome, nacionalidade);
		}
		if (nome != null) {
			return repository.findByNome(nome);
		}
		if (nacionalidade != null) {
			return repository.findByNacionalidade(nacionalidade);
		}

		return repository.findAll();
	}

	public List<Autor> pesquisaByExample(String nome, String nacionalidade) {
		var autor = new Autor();
		autor.setNome(nome);
		autor.setNacionalidade(nacionalidade);

		ExampleMatcher macher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);

		Example<Autor> autorExample = Example.of(autor, macher);

		return repository.findAll(autorExample);
	}

	public void deletar(Autor autor) {
		if (possuiLivro(autor)) {
			throw new OperacaoNaoPermitidaException("Não é permitido excluir um Autor que possui livros cadastrados!");
		}
		repository.delete(autor);
	}

	public boolean possuiLivro(Autor autor) {
		return livroRepository.existsByAutor(autor);
	}
}
