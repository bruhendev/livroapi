package com.bruno.livro.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OperacaoNaoPermitidaException(String message) {
		super(message);
	}
}
