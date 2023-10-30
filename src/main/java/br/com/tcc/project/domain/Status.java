package br.com.tcc.project.domain;

import lombok.*;

@AllArgsConstructor
public enum Status {
  EQUIVALENCE,
  NON_EQUIVALENCE,
  PENDING,
  ANALYZED,
  PROCESSED,
  MENU_CHANGE
}
