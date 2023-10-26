package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.FindAllEquivalence;
import br.com.tcc.project.command.FindEquivalenceByAnalysisId;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.repositoy.EquivalenceRepository;
import br.com.tcc.project.command.repositoy.mapper.EquivalenceDocumentMapper;
import br.com.tcc.project.command.repositoy.model.EquivalenceDocument;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.response.EquivalenceResponse;
import lombok.Setter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@CommandReceiver(FindEquivalenceByAnalysisId.class)
public class FindEquivalenceByAnalysisIdReceiver
    extends AbstractReceiver<FindEquivalenceByAnalysisId.Request, EquivalenceDocument> {

  @Autowired @Setter private EquivalenceRepository equivalenceRepository;
  @Override
  protected EquivalenceDocument doExecute(FindEquivalenceByAnalysisId.Request parameter) {

    return equivalenceRepository.findByAnalysisId(parameter.getAnalysisId());
  }
}
