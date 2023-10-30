package br.com.tcc.project.command;

import br.com.tcc.project.command.impl.AbstractCommandChain;
import br.com.tcc.project.command.repositoy.mapper.DisciplineDocumentMapper;
import br.com.tcc.project.command.repositoy.model.DisciplineDocument;
import br.com.tcc.project.controller.response.EquivalenceDisciplineResponse;
import br.com.tcc.project.domain.OpenAIEquivalenceAnalysisResponse;
import br.com.tcc.project.gateway.annotation.processor.GenerateCommandFactory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.factory.Mappers;

@GenerateCommandFactory
public class OpenAIEquivalenceAnalysis
    extends AbstractCommandChain<
        OpenAIEquivalenceAnalysis.Request, OpenAIEquivalenceAnalysisResponse> {

  @Setter
  @Getter
  @Builder
  public static class Request {
    private DisciplineDocument disciplinaOrigem;
    private DisciplineDocument disciplinaDestino;
  }

  @Override
  public void execute() throws IllegalStateException {
    super.execute();


    String content = invoke(
            GetSummaryMenuInformationFromOpenAI.class,
            GetSummaryMenuInformationFromOpenAI.Request.builder()
                    .originDiscipline(getParameter().getDisciplinaOrigem())
                    .destinyDiscipline(getParameter().getDisciplinaDestino())
                    .build());

    String resemblance = "";
    String difference = "";
    String consideration;

    StringBuilder currentSection = new StringBuilder();
    String[] parts = content.split("\n");
    for (String part : parts) {
      if ((currentSection.length() == 0) && !part.isEmpty()) {
        currentSection = new StringBuilder(part);
      } else if ((currentSection.length() > 0) && !part.isEmpty()) {
        currentSection.append("\n").append(part);
      } else if (currentSection.length() > 0) {
        if (currentSection.toString().startsWith("Semelhante:")) {
          resemblance = currentSection.substring("Semelhante:\n".length()).trim();
        } else if (currentSection.toString().startsWith("Diferente:")) {
          difference = currentSection.substring("Diferente:\n".length()).trim();
        }
        currentSection = new StringBuilder();
      }
    }

    consideration = content.split("Equivalente:\n")[1];

    setResult(OpenAIEquivalenceAnalysisResponse
            .builder()
            .consideration(consideration)
            .resemblance(resemblance)
            .difference(difference)
            .build());
  }
}
