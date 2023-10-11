package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindDisciplineById;
import br.com.tcc.project.command.enums.DisciplineEquivalenceErrors;
import br.com.tcc.project.command.exception.CollegeNotFoundException;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.DisciplineRepository;
import br.com.tcc.project.command.repositoy.mapper.DisciplineDocumentMapper;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.response.DisciplineResponse;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

@CommandReceiver(FindDisciplineById.class)
public class FindDisciplineByIdReceiver
    extends AbstractReceiver<FindDisciplineById.Request, DisciplineDocument> {

  @Autowired @Setter private DisciplineRepository disciplineRepository;

  @Override
  protected DisciplineDocument doExecute(FindDisciplineById.Request parameter) {
    DisciplineDocument disciplineDocument = disciplineRepository.findById(parameter.getId()).orElseThrow(
            () ->
                    new CollegeNotFoundException(
                            MessageFormat.format(
                                    DisciplineEquivalenceErrors.DEE0004.message(), parameter.getId()),
                            DisciplineEquivalenceErrors.DEE0004.name(),
                            DisciplineEquivalenceErrors.DEE0004.group()));
    return disciplineDocument;
  }
}
