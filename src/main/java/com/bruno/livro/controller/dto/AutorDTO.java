package com.bruno.livro.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.bruno.livro.model.Autor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record AutorDTO(UUID id,
		@NotBlank(message = "Campo obrigatório") @Size(max = 100, min = 2, message = "Campo fora do tamanho padrão") String nome,
		@NotNull(message = "Campo obrigatório") @Past(message = "Não pode ser uma data futura") LocalDate dataNascimento,
		@NotBlank(message = "Campo obrigatório") @Size(max = 50, min = 2, message = "Campo fora do tamanho padrão") String nacionalidade) {

	public Autor mapearParaAutor() {
		Autor autor = new Autor();
		autor.setNome(this.nome);
		autor.setDataNascimento(this.dataNascimento);
		autor.setNacionalidade(this.nacionalidade);
		return autor;
	}
}
