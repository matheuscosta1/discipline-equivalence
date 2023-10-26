package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.EquivalenceDocument;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EquivalenceRepository extends JpaRepository<EquivalenceDocument, Integer> {

    @Query(value = "SELECT * FROM equivalencia WHERE analise_equivalencia_id = :analiseEquivalenciaId", nativeQuery = true)
    EquivalenceDocument findByAnalysisId(@Param("analiseEquivalenciaId") Integer analiseEquivalenciaId);
}
