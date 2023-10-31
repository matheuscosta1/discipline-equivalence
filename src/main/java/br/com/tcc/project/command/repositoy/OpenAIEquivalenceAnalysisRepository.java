package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.OpenAIEquivalenceAnalysisDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OpenAIEquivalenceAnalysisRepository extends JpaRepository<OpenAIEquivalenceAnalysisDocument, Integer> {

    @Query(value = "SELECT * FROM analise_equivalencia_open_ai WHERE disciplina_origem_id = :disciplinaOrigemId and disciplina_destino_id = :disciplinaDestinoId and status = :status ORDER BY data_criacao DESC LIMIT 1", nativeQuery = true)
    OpenAIEquivalenceAnalysisDocument findByDisciplinaOrigemIdAndDisciplinaDestinoIdAndStatus(@Param("disciplinaOrigemId") Integer disciplinaOrigemId, @Param("disciplinaDestinoId") Integer disciplinaDestinoId, @Param("status") String status);
    @Query(value = "SELECT * FROM analise_equivalencia_open_ai WHERE status <> :status", nativeQuery = true)
    List<OpenAIEquivalenceAnalysisDocument> findAllOpenAIEquivalenceAnalysisByStatusNotProcessed(@Param("status") String status);
}
