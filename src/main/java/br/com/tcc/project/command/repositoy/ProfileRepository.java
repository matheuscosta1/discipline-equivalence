package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.ProfileDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileDocument, Integer> {

}
