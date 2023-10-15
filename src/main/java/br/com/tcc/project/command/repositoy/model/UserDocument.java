package br.com.tcc.project.command.repositoy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Setter
@Getter
@Builder
@Entity
@Table(name = "usuario")
public class UserDocument implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nome;

  @Column(unique=true)
  private String email;

  private Integer perfil;

  @JsonIgnore
  private String password;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<ProfileDocument> perfis;

  public UserDocument() {
  }

  public UserDocument(Integer id, String nome, String email, Integer perfil, String password, List<ProfileDocument> perfis) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.perfil = perfil;
    this.password = password;
    this.perfis = perfis;
  }
}
