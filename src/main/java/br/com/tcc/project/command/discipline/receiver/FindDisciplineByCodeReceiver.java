package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindDisciplineByCode;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.DisciplineRepository;
import br.com.tcc.project.command.repositoy.mapper.DisciplineDocumentMapper;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.response.DisciplineResponse;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@CommandReceiver(FindDisciplineByCode.class)
public class FindDisciplineByCodeReceiver
    extends AbstractReceiver<FindDisciplineByCode.Request, DisciplineResponse> {

  @Autowired @Setter private DisciplineRepository disciplineRepository;
  private final DisciplineDocumentMapper mapper = Mappers.getMapper(DisciplineDocumentMapper.class);

  @Override
  protected DisciplineResponse doExecute(FindDisciplineByCode.Request parameter) {
    return mapper.map(disciplineRepository.findByCodigoOrigem(parameter.getDisciplineCode()));
  }
}
