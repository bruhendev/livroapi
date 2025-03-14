package com.bruno.livro.exceptions;

import lombok.Getter;

public class CampoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Getter
	private String campo;

	public CampoInvalidoException(String campo, String mensagem) {
		super(mensagem);
		this.campo = campo;
	}
}