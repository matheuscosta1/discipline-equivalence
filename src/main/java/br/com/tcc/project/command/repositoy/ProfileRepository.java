package br.com.tcc.project.command.repositoy;

import br.com.tcc.project.command.repositoy.model.ProfileDocument;
import br.com.tcc.project.command.repositoy.model.UserDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProfileRepository extends JpaRepository<ProfileDocument, Integer> {

}
