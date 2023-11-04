package br.com.tcc.project.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class UserResponse {
  private Integer id;
  private String nome;
  private String email;
}
