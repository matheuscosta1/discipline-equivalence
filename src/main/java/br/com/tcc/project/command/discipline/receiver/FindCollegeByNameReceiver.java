package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindCollegeById;
import br.com.tcc.project.command.FindCollegeByName;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.CollegeNotFoundException;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.CollegeRepository;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

@CommandReceiver(FindCollegeByName.class)
public class FindCollegeByNameReceiver
    extends AbstractReceiver<FindCollegeByName.Request, CollegeDocument> {

  @Autowired @Setter private CollegeRepository collegeRepository;

  @Override
  protected CollegeDocument doExecute(FindCollegeByName.Request parameter) {

    return collegeRepository
        .findByNome(parameter.getName());
  }
}
