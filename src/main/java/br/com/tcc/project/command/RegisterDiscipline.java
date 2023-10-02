package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@GenerateCommandFactory
public class RegisterDiscipline extends AbstractCommand<RegisterDiscipline.Request, Void> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer workLoad;
    private String originCode;
    private String course;
    private String menu;
    private String college;
    private String name;
    private String program;
  }
}
