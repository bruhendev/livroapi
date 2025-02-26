package com.bruno.livro.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

	public void atualizar(Autor autor) {
		if (autor.getId() == null) {
			throw new IllegalArgumentException("Necessario um id válido");
		}
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

	public void deletar(Autor autor) {
		repository.delete(autor);
	}
}
