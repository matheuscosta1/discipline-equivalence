package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.RegisterDiscipline;
import br.com.tcc.project.command.RegisterDisciplineModification;
import br.com.tcc.project.command.discipline.mapper.DisciplineMapper;
import br.com.tcc.project.command.discipline.mapper.DisciplineModificationMapper;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.DisciplineModificationRepository;
import br.com.tcc.project.command.repositoy.DisciplineRepository;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineModificationDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(RegisterDisciplineModification.class)
public class RegisterDisciplineModificationReceiver
    extends AbstractReceiver<RegisterDisciplineModification.Request, DisciplineModificationDocument> {

  @Autowired @Setter private DisciplineModificationMapper registerDisciplineMapper;

  @Autowired @Setter private DisciplineModificationRepository disciplineRepository;

  @Override
  protected DisciplineModificationDocument doExecute(RegisterDisciplineModification.Request parameter) {
    return disciplineRepository.save(registerDisciplineMapper.map(parameter));
  }
}
