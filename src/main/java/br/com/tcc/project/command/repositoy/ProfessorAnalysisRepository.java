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
  @Query(
          value =
                  "SELECT * FROM analises WHERE faculdade_origem_id = :faculdadeOrigemId and faculdade_destino_id = :faculdadeDestinoId and disciplina_origem_id = :disciplinaOrigemId and disciplina_destino_id = :disciplinaDestinoId and status <> :status",
          nativeQuery = true)
  AnalisesDocument findByFaculdadeOrigemIdAndFaculdadeDestinoIdAndDisciplinaOrigemIdAndDisciplinaDestinoIdAndStatus(@Param("faculdadeOrigemId") Integer faculdadeOrigemId, @Param("faculdadeDestinoId") Integer faculdadeDestinoId, @Param("disciplinaOrigemId") Integer disciplinaOrigemId, @Param("disciplinaDestinoId") Integer disciplinaDestinoId, @Param("status") String status);
  @Query(
          value =
                  "SELECT * FROM analises WHERE (disciplina_origem_id = :disciplinaId or disciplina_destino_id = :disciplinaId) and status = :status",
          nativeQuery = true)
  List<AnalisesDocument> findAllByDisciplinaIdAndStatus(@Param("disciplinaId") Integer disciplinaId, @Param("status") String status);
}
