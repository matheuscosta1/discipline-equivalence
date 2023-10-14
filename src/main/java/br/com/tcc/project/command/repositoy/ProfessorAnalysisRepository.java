package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfessorAnalysisRepository extends JpaRepository<AnalisesDocument, Integer> {

  @Query(
      value =
          "SELECT a.* FROM analises a INNER JOIN professor p ON a.professor_id = p.id WHERE p.nome = :professorNome",
      nativeQuery = true)
  Page<AnalisesDocument> findAnalisesWithProfessorName(
      @Param("professorNome") String professorNome, Pageable pageable);
}