package com.bruno.livro.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.livro.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
