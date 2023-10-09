package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.gateway.annotation.CommandReceiver;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.command.ValidateDisciplineWorkLoadIsUnderMinimumParametersToGiveEquivalence;

@CommandReceiver(ValidateDisciplineWorkLoadIsUnderMinimumParametersToGiveEquivalence.class)
public class ValidateDisciplineWorkLoadIsUnderMinimumParametersToGiveEquivalenceReceiver
    extends AbstractReceiver<
        ValidateDisciplineWorkLoadIsUnderMinimumParametersToGiveEquivalence.Request, Boolean> {

  @Override
  protected Boolean doExecute(
      ValidateDisciplineWorkLoadIsUnderMinimumParametersToGiveEquivalence.Request parameter) {

    return parameter
        .getDestinyDiscipline()
        .getCargaHoraria()
        .equals(parameter.getOriginDiscipline().getCargaHoraria());
  }
}
