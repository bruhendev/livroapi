package com.bruno.livro.controller.dto;

import java.time.LocalDate;

import com.bruno.livro.model.Autor;

public record AutorDTO(String nome, LocalDate dataNascimento, String nacionalidade) {

	public Autor mapearParaAutor() {
		Autor autor = new Autor();
		autor.setNome(this.nome);
		autor.setDataNascimento(this.dataNascimento);
		autor.setNacionalidade(this.nacionalidade);
		return autor;
	}
}
