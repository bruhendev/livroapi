package com.bruno.livro.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bruno.livro.model.Autor;
import com.bruno.livro.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor<Livro>{

	boolean existsByAutor(Autor autor);
	
	public List<Livro> buscaPorFiltro();
}
