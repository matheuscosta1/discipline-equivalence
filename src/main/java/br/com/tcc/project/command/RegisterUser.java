package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.command.repositoy.model.ProfileDocument;
import br.com.tcc.project.command.repositoy.model.UserDocument;
import br.com.tcc.project.domain.Profile;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@GenerateCommandFactory
public class RegisterUser extends AbstractCommand<RegisterUser.Request, UserDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer id;
    private String nome;
    private String email;
    private Integer perfil;
    private String password;
  }
}
