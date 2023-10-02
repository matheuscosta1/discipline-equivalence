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
  private List<String> equivalenceMenu = new ArrayList<>();
  private List<String> nonEquivalenceMenu = new ArrayList<>();
}
