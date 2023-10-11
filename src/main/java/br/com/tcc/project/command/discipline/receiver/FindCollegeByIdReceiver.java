package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindCollegeById;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.CollegeNotFoundException;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.CollegeRepository;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import java.text.MessageFormat;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(FindCollegeById.class)
public class FindCollegeByIdReceiver
    extends AbstractReceiver<FindCollegeById.Request, CollegeDocument> {

  @Autowired @Setter private CollegeRepository collegeRepository;

  @Override
  protected CollegeDocument doExecute(FindCollegeById.Request parameter) {

    return collegeRepository
        .findById(parameter.getFaculdadeId())
        .orElseThrow(
            () ->
                new CollegeNotFoundException(
                    MessageFormat.format(
                        DisciplineEquivalenceErrors.DEE0002.message(), parameter.getFaculdadeId()),
                    DisciplineEquivalenceErrors.DEE0002.name(),
                    DisciplineEquivalenceErrors.DEE0002.group()));
  }
}
