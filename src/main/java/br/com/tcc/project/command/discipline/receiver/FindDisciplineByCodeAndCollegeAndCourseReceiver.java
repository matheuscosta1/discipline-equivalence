package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindDisciplineByCodeAndCollegeAndCourse;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.DisciplineRepository;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(FindDisciplineByCodeAndCollegeAndCourse.class)
public class FindDisciplineByCodeAndCollegeAndCourseReceiver
    extends AbstractReceiver<FindDisciplineByCodeAndCollegeAndCourse.Request, DisciplineDocument> {

  @Autowired @Setter private DisciplineRepository disciplineRepository;

  @Override
  protected DisciplineDocument doExecute(FindDisciplineByCodeAndCollegeAndCourse.Request parameter) {
    return disciplineRepository.findByCodigoOrigemAndFaculdadeIdAndCursoId(parameter.getCodigo(), parameter.getFaculdadeId(), parameter.getCursoId());
  }
}
