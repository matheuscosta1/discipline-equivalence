package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllProfessorAnalysisByProfessorId;
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

@CommandReceiver(FindAllProfessorAnalysisByProfessorId.class)
public class FindAllProfessorAnalysisByProfessorIdReceiver
    extends AbstractReceiver<FindAllProfessorAnalysisByProfessorId.Request, Page<ProfessorAnaliseResponse>> {

  @Autowired @Setter private ProfessorAnalysisRepository professorAnalysisRepository;
  private final ProfessorAnalysisDocumentMapper mapper =
      Mappers.getMapper(ProfessorAnalysisDocumentMapper.class);

  @Override
  protected Page<ProfessorAnaliseResponse> doExecute(FindAllProfessorAnalysisByProfessorId.Request parameter) {

    List<ProfessorAnaliseResponse> courseResponses;

    PageRequest pageRequest =
            PageRequest.of(
                    parameter.getPagina(),
                    parameter.getPaginas());

    Page<AnalisesDocument> analisesWithProfessorName =
            professorAnalysisRepository.findByProfessorId(
                    parameter.getProfessorId(), pageRequest);
    courseResponses = mapper.map(analisesWithProfessorName.getContent());
    return new PageImpl<>(
            courseResponses, pageRequest, analisesWithProfessorName.getTotalElements());
  }
}
