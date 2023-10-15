package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterProfile;
import br.com.tcc.project.command.RegisterUser;
import br.com.tcc.project.command.repositoy.model.ProfileDocument;
import br.com.tcc.project.command.repositoy.model.UserDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

  ProfileDocument map(RegisterProfile.Request source);

}
