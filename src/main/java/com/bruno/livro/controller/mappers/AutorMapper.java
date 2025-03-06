package com.bruno.livro.controller.mappers;

import org.mapstruct.Mapper;

import com.bruno.livro.controller.dto.AutorDTO;
import com.bruno.livro.model.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {

	Autor toEntity(AutorDTO dto);

	AutorDTO toDto(Autor autor);
}
