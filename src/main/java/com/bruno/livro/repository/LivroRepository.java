package com.bruno.livro.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruno.livro.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, UUID>{

}
