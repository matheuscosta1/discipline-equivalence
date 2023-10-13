package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterCollege;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisterCollegeMapper {

  @Mapping(target = "nome", source = "name")
  @Mapping(target = "id", source = "id")
  CollegeDocument map(RegisterCollege.Request source);
}
