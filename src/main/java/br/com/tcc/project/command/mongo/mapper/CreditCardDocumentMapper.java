package br.com.tcc.project.command.mongo.mapper;

import br.com.tcc.project.command.mongo.model.DisciplineDocument;
import br.com.tcc.project.domain.Discipline;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditCardDocumentMapper {

  Discipline map(DisciplineDocument source);

  DisciplineDocument map(Discipline source);
}
