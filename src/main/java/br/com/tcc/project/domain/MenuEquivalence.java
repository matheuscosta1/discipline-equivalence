package br.com.tcc.project.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class MenuEquivalence {
  private List<String> ementaEquivalente = new ArrayList<>();
  private List<String> ementaNaoEquivalente = new ArrayList<>();
}
