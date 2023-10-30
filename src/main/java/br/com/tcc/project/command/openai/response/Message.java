package br.com.tcc.project.command.openai.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private String role;
    private String content;
}