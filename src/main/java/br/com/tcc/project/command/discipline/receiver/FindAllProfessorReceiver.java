package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllProfessor;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.ProfessorRepository;
import br.com.tcc.project.command.repositoy.mapper.ProfessorDocumentMapper;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.response.ProfessorResponse;
import java.util.List;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@CommandReceiver(FindAllProfessor.class)
public class FindAllProfessorReceiver
    extends AbstractReceiver<FindAllProfessor.Request, Page<ProfessorResponse>> {

  @Autowired @Setter private ProfessorRepository professorRepository;
  private final ProfessorDocumentMapper mapper = Mappers.getMapper(ProfessorDocumentMapper.class);

  @Override
  protected Page<ProfessorResponse> doExecute(FindAllProfessor.Request parameter) {

    PageRequest pageRequest =
        PageRequest.of(
            parameter.getPagina(),
            parameter.getPaginas(),
            Sort.Direction.valueOf(parameter.getDirection()),
            parameter.getOrderBy());
    List<ProfessorResponse> courseResponses;

    if (parameter.getNome() != null && !parameter.getNome().isBlank()) {
      Page<ProfessorDocument> professorDocuments =
          professorRepository.findByNomeContaining(parameter.getNome(), pageRequest);

      courseResponses = mapper.map(professorDocuments.getContent());
      return new PageImpl<>(courseResponses, pageRequest, professorDocuments.getTotalElements());
    }

    if (parameter.getDisciplinaId() != null) {
      Page<ProfessorDocument> professorDocuments =
          professorRepository.findByDisciplinaId(
              Integer.valueOf(parameter.getDisciplinaId()), pageRequest);
      courseResponses = mapper.map(professorDocuments.getContent());
      return new PageImpl<>(courseResponses, pageRequest, professorDocuments.getTotalElements());
    }

    Page<ProfessorDocument> courseDocumentPage = professorRepository.findAll(pageRequest);
    courseResponses = mapper.map(courseDocumentPage.getContent());

    return new PageImpl<>(courseResponses, pageRequest, courseDocumentPage.getTotalElements());
  }
}
