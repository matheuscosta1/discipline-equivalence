package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.RegisterDiscipline;
import br.com.tcc.project.command.discipline.mapper.DisciplineMapper;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.DisciplineRepository;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(RegisterDiscipline.class)
public class RegisterDisciplineReceiver
    extends AbstractReceiver<RegisterDiscipline.Request, DisciplineDocument> {

  @Autowired @Setter private DisciplineMapper registerDisciplineMapper;

  @Autowired @Setter private DisciplineRepository disciplineRepository;

  @Override
  protected DisciplineDocument doExecute(RegisterDiscipline.Request parameter) {
    return disciplineRepository.save(registerDisciplineMapper.map(parameter));
  }
}
