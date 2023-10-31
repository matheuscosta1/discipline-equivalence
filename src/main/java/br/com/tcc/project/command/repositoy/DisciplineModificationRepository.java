package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.command.repositoy.model.DisciplineModificationDocument;
import br.com.tcc.project.command.repositoy.model.OpenAIEquivalenceAnalysisDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisciplineModificationRepository extends JpaRepository<DisciplineModificationDocument, Integer> {

    @Query(value = "SELECT * FROM modificacao_ementa_disciplina WHERE status <> :status", nativeQuery = true)
    List<DisciplineModificationDocument> findAllDisciplineModificationByStatusNotProcessed(@Param("status") String status);

}
