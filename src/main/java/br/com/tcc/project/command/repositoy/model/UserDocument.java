package br.com.tcc.project.command.repositoy.model;

import br.com.tcc.project.domain.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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

  private String name;

  @Column(unique=true)
  private String email;

  private Integer type;

  @JsonIgnore
  private String password;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<ProfileDocument> perfis;

  public UserDocument() {
  }

  public UserDocument(Integer id, String name, String email, Integer type, String password, List<ProfileDocument> perfis) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.type = type;
    this.password = password;
    this.perfis = perfis;
  }
}
