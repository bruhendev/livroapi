package com.bruno.livro.controller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruno.livro.controller.dto.CadastroLivroDTO;
import com.bruno.livro.model.Livro;
import com.bruno.livro.repository.AutorRepository;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

	@Autowired
	AutorRepository autorRepository;

	@Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )")
	public abstract Livro toEntity(CadastroLivroDTO dto);
}
