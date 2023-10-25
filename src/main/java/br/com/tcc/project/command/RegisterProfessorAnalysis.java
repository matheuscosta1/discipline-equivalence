package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommand;
import br.com.tcc.project.command.repositoy.model.*;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@GenerateCommandFactory
public class RegisterProfessorAnalysis
    extends AbstractCommand<RegisterProfessorAnalysis.Request, AnalisesDocument> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private Integer id;
    private CollegeDocument collegeOriginDocument;
    private CourseDocument courseOriginDocument;
    private DisciplineDocument disciplineOriginDocument;
    private CollegeDocument collegeDestinyDocument;
    private CourseDocument courseDestinyDocument;
    private DisciplineDocument disciplineDestinyDocument;
    private ProfessorDocument professorDocument;
    private UsuarioDocument adminUserDocument;
    public String maximumDate;
    public String status;
    public String studentEmail;
    public String studentName;
  }
}
