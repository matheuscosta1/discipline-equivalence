package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterProfessor;
import br.com.tcc.project.command.RegisterUser;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.command.repositoy.model.ProfileDocument;
import br.com.tcc.project.command.repositoy.model.UserDocument;
import br.com.tcc.project.domain.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "id", source = "source.id")
  @Mapping(target = "nome", source = "source.nome")
  @Mapping(target = "perfil", source = "source.perfil")
  @Mapping(target = "password", source = "source.password")
  UserDocument map(RegisterUser.Request source);

}
