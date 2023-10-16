package br.com.tcc.project.command.discipline.mapper;

import br.com.tcc.project.command.RegisterProfile;
import br.com.tcc.project.command.repositoy.model.ProfileDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

  ProfileDocument map(RegisterProfile.Request source);

}
