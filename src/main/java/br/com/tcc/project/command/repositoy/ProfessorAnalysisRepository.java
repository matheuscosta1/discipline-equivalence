package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.AnalisesDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorAnalysisRepository extends JpaRepository<AnalisesDocument, Integer> {

  @Query(
      value =
          "SELECT a.* FROM analises a INNER JOIN professor p ON a.professor_id = p.id WHERE p.nome = :professorNome",
      nativeQuery = true)
  Page<AnalisesDocument> findAnalisesWithProfessorName(
          @Param("professorNome") String professorNome, Pageable pageable);


  Page<AnalisesDocument> findByProfessorId(Integer professorId, Pageable pageable);
  AnalisesDocument findByFaculdadeOrigemIdAndFaculdadeDestinoIdAndDisciplinaOrigemIdAndDisciplinaDestinoId(Integer faculdadeOrigemId, Integer faculdadeDestinoId, Integer disciplinaOrigemId, Integer disciplinaDestinoId);

}
