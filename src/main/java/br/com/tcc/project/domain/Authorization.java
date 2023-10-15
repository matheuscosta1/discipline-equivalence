package br.com.tcc.project.domain;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Authorization {
    private String accessToken;
}
