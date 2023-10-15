package br.com.tcc.project.command.repositoy.model;

import br.com.tcc.project.domain.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@Entity
@Table(name = "perfil")
public class ProfileDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "usuarioId")
  private UserDocument usuario;

  private Integer profile;

  public ProfileDocument(Integer id, UserDocument usuario, Integer profile) {
    this.id = id;
    this.usuario = usuario;
    this.profile = profile;
  }

  public ProfileDocument() {
  }
}
