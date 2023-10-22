package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.RegisterEquivalence;
import br.com.tcc.project.command.discipline.mapper.EquivalenceMapper;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.CollegeNotFoundException;
import br.com.tcc.project.command.exception.DuplicateEntryException;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.EquivalenceRepository;
import br.com.tcc.project.command.repositoy.model.EquivalenceDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLIntegrityConstraintViolationException;

@CommandReceiver(RegisterEquivalence.class)
public class RegisterEquivalenceReceiver
    extends AbstractReceiver<RegisterEquivalence.Request, EquivalenceDocument> {

  @Autowired @Setter private EquivalenceMapper equivalenceMapper;

  @Autowired @Setter private EquivalenceRepository equivalenceRepository;

  @Override
  protected EquivalenceDocument doExecute(RegisterEquivalence.Request parameter) {
    try {
      return equivalenceRepository.save(equivalenceMapper.map(parameter));
    } catch (DataIntegrityViolationException ex) {
      throw new DuplicateEntryException(DisciplineEquivalenceErrors.DEE0006.message(),
              DisciplineEquivalenceErrors.DEE0006.name(),
              DisciplineEquivalenceErrors.DEE0006.group());
    }
  }
}
