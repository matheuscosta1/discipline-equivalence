package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.GetSummaryMenuInformationFromOpenAI;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.openai.request.ChatRequest;
import br.com.tcc.project.command.openai.response.ChatResponse;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@CommandReceiver(GetSummaryMenuInformationFromOpenAI.class)
public class GetSummaryInformationFromOpenAIReceiver
    extends AbstractReceiver<
        GetSummaryMenuInformationFromOpenAI.Request, String> {
  @Qualifier("openaiRestTemplate")
  @Autowired
  private RestTemplate restTemplate;

  @Value("${openai.model}")
  private String model;

  @Value("${openai.api.url}")
  private String apiUrl;
  @Override
  protected String doExecute(
          GetSummaryMenuInformationFromOpenAI.Request parameter) {
    String message = "Seja as seguintes ementas: \n Primeira ementa:\n";

    message = message.concat("'"+parameter.getOriginDiscipline().getEmenta()+"'");
    message = message.concat("\n E a segunda ementa: \n");
    message = message.concat("'"+parameter.getDestinyDiscipline().getEmenta()+"'");
    message = message.concat("\n. A carga horária da primeira disciplina é: " + parameter.getOriginDiscipline().getCargaHoraria() + "horas. E a carga horária da segunda disciplina é: " + parameter.getDestinyDiscipline().getCargaHoraria() + "horas. Faça uma análise dessas duas ementas e diga o que é semelhante e o que é diferente nessas duas ementas. Dê um resultado em um template básico que conterá na resposta apenas 'Semelhante' e 'Diferente'. Por fim em um campo chamado 'Equivalente' diga as diferenças entre as duas ementas e seu parecer final, dizendo se são equivalentes ou não. Dê detalhes sobre a carga horária também.");

    ChatRequest requestChatGpt = new ChatRequest(model, message);

    ChatResponse response = restTemplate.postForObject(apiUrl, requestChatGpt, ChatResponse.class);

    assert response != null;

    return response.getChoices().get(0).getMessage().getContent();
  }

}
