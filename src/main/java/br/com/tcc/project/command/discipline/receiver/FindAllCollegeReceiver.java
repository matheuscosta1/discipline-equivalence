package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllCollege;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.CollegeRepository;
import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@CommandReceiver(FindAllCollege.class)
public class FindAllCollegeReceiver
    extends AbstractReceiver<FindAllCollege.Request, Page<CollegeDocument>> {

  @Autowired @Setter private CollegeRepository collegeRepository;

  @Override
  protected Page<CollegeDocument> doExecute(FindAllCollege.Request parameter) {

    PageRequest pageRequest =
        PageRequest.of(
            parameter.getPagina(),
            parameter.getPaginas(),
            Sort.Direction.valueOf(parameter.getDirection()),
            parameter.getOrderBy());
    if (parameter.getNome().isBlank()) {
      return collegeRepository.findAll(pageRequest);
    }
    return collegeRepository.findByNomeContaining(parameter.getNome(), pageRequest);
  }
}
