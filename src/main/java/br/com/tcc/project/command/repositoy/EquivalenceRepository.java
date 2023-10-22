package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.EquivalenceDocument;
import br.com.tcc.project.command.repositoy.model.ProfessorDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquivalenceRepository extends JpaRepository<EquivalenceDocument, Integer> {
}
