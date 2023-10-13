package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.DeleteById;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.*;
import br.com.tcc.project.command.repositoy.model.*;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(DeleteById.class)
public class DeleteByIdReceiver
    extends AbstractReceiver<DeleteById.Request, Void> {

  @Autowired CollegeRepository collegeRepository;
  @Autowired CourseRepository courseRepository;
  @Autowired DisciplineRepository disciplineRepository;
  @Autowired ProfessorRepository professorRepository;
  @Autowired ProfessorAnalysisRepository professorAnalysisRepository;

  @Override
  protected Void doExecute(DeleteById.Request request) {
    if (request.getGenericClass().equals(CollegeDocument.class)) {
      collegeRepository.deleteById(request.getId());
    } else if (request.getGenericClass().equals(CourseDocument.class)) {
      courseRepository.deleteById(request.getId());
    } else if (request.getGenericClass().equals(DisciplineDocument.class)) {
      disciplineRepository.deleteById(request.getId());
    } else if (request.getGenericClass().equals(ProfessorDocument.class)) {
      professorRepository.deleteById(request.getId());
    } else if (request.getGenericClass().equals(AnalisesDocument.class)) {
      professorAnalysisRepository.deleteById(request.getId());
    } else {
      throw new IllegalArgumentException("Entity type not supported.");
    }
    return null;
  }
}
