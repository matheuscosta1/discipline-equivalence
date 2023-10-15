package br.com.tcc.project.command.repositoy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

  private Integer perfil;

  public ProfileDocument(Integer id, UserDocument usuario, Integer perfil) {
    this.id = id;
    this.usuario = usuario;
    this.perfil = perfil;
  }

  public ProfileDocument() {
  }
}
