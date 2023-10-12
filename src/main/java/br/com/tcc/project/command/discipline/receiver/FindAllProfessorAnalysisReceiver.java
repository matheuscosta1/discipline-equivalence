package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllProfessorAnalysis;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.ProfessorAnalysisRepository;
import br.com.tcc.project.command.repositoy.mapper.ProfessorAnalysisDocumentMapper;
import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.response.ProfessorAnaliseResponse;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@CommandReceiver(FindAllProfessorAnalysis.class)
public class FindAllProfessorAnalysisReceiver
    extends AbstractReceiver<
        FindAllProfessorAnalysis.Request, Page<ProfessorAnaliseResponse>> {

  @Autowired @Setter private ProfessorAnalysisRepository professorAnalysisRepository;
  private final ProfessorAnalysisDocumentMapper mapper = Mappers.getMapper(ProfessorAnalysisDocumentMapper.class);

  @Override
  protected Page<ProfessorAnaliseResponse> doExecute(
          FindAllProfessorAnalysis.Request parameter) {

    List<ProfessorAnaliseResponse> courseResponses;

    if (parameter.getNomeProfessor() != null && !parameter.getNomeProfessor().isBlank()) {
      PageRequest pageRequest = PageRequest.of(parameter.getPagina(), parameter.getPaginas(), Sort.Direction.valueOf(parameter.getDirection()), "p.nome");

      Page<AnalisesDocument> analisesWithProfessorName = professorAnalysisRepository.findAnalisesWithProfessorName(parameter.getNomeProfessor(), pageRequest);
      courseResponses = mapper.map(analisesWithProfessorName.getContent());
      return new PageImpl<>(courseResponses, pageRequest, analisesWithProfessorName.getTotalElements());
    }

    PageRequest pageRequest = PageRequest.of(parameter.getPagina(), parameter.getPaginas(), Sort.Direction.valueOf(parameter.getDirection()), parameter.getOrderBy());

    Page<AnalisesDocument> courseDocumentPage = professorAnalysisRepository.findAll(pageRequest);
    courseResponses = mapper.map(courseDocumentPage.getContent());

    return new PageImpl<>(courseResponses, pageRequest, courseDocumentPage.getTotalElements());
  }
}
