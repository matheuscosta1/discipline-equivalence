package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterUser;
import br.com.tcc.project.command.repositoy.model.UsuarioDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "id", source = "source.id")
  @Mapping(target = "nome", source = "source.nome")
  @Mapping(target = "perfil", source = "source.perfil")
  @Mapping(target = "password", source = "source.password")
  UsuarioDocument map(RegisterUser.Request source);

}
