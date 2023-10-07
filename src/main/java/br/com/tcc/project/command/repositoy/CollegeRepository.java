package br.com.tcc.project.command.repositoy;


import br.com.tcc.project.command.repositoy.model.CollegeDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<CollegeDocument, Integer> {

    CollegeDocument findByName(String name);

}
