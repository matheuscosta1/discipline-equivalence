package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllEquivalence;
import br.com.tcc.project.command.FindAllProfessor;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.EquivalenceRepository;
import br.com.tcc.project.command.repositoy.ProfessorRepository;
import br.com.tcc.project.command.repositoy.mapper.EquivalenceDocumentMapper;
import br.com.tcc.project.command.repositoy.mapper.ProfessorDocumentMapper;
import br.com.tcc.project.command.repositoy.model.EquivalenceDocument;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.response.EquivalenceResponse;
import br.com.tcc.project.response.ProfessorResponse;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@CommandReceiver(FindAllEquivalence.class)
public class FindAllEquivalenceReceiver
    extends AbstractReceiver<FindAllEquivalence.Request, Page<EquivalenceResponse>> {

  @Autowired @Setter private EquivalenceRepository equivalenceRepository;
  private final EquivalenceDocumentMapper mapper = Mappers.getMapper(EquivalenceDocumentMapper.class);

  @Override
  protected Page<EquivalenceResponse> doExecute(FindAllEquivalence.Request parameter) {

    PageRequest pageRequest =
        PageRequest.of(
            parameter.getPagina(),
            parameter.getPaginas(),
            Sort.Direction.valueOf(parameter.getDirection()),
            parameter.getOrderBy());
    List<EquivalenceResponse> courseResponses;

    if (parameter.getCodigo() != null && !parameter.getCodigo().isBlank()) {
      Page<EquivalenceDocument> courseDocumentPage = equivalenceRepository.findAll(pageRequest);
      courseResponses = mapper.map(courseDocumentPage.getContent());
      List<EquivalenceResponse> equivalenceResponses = courseResponses.stream().filter(equivalenceResponse -> equivalenceResponse.getCodigoDisciplinaDestino().equals(parameter.getCodigo()) || equivalenceResponse.getCodigoDisciplinaOrigem().equals(parameter.getCodigo())).collect(Collectors.toList());
      return new PageImpl<>(equivalenceResponses, pageRequest, equivalenceResponses.size());
    }


    Page<EquivalenceDocument> courseDocumentPage = equivalenceRepository.findAll(pageRequest);
    courseResponses = mapper.map(courseDocumentPage.getContent());

    return new PageImpl<>(courseResponses, pageRequest, courseDocumentPage.getTotalElements());
  }
}
