package br.com.tcc.project.command.discipline.receiver;

import br.com.tcc.project.command.ValidateDisciplineMenuIsUnderMinimumParametersToGiveEquivalence;
import br.com.tcc.project.command.impl.AbstractReceiver;
import br.com.tcc.project.domain.MenuEquivalence;
import br.com.tcc.project.gateway.annotation.CommandReceiver;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CommandReceiver(ValidateDisciplineMenuIsUnderMinimumParametersToGiveEquivalence.class)
public class ValidateDisciplineMenuIsUnderMinimumParametersToGiveEquivalenceReceiver
    extends AbstractReceiver<
        ValidateDisciplineMenuIsUnderMinimumParametersToGiveEquivalence.Request, MenuEquivalence> {

  @Override
  protected MenuEquivalence doExecute(
      ValidateDisciplineMenuIsUnderMinimumParametersToGiveEquivalence.Request parameter) {

    List<String> originDisciplineMenu =
        Arrays.stream(parameter.getOriginDiscipline().getEmenta().split(";"))
            .map(String::trim)
            .collect(Collectors.toList());
    List<String> destinyDisciplineMenu =
        Arrays.stream(parameter.getDestinyDiscipline().getEmenta().split(";"))
            .map(String::trim)
            .collect(Collectors.toList());
    MenuEquivalence menuEquivalence;

    if (originDisciplineMenu.size() > destinyDisciplineMenu.size()) {
      menuEquivalence =
          calculateEqualMenuBasedOnGreaterMenuSize(originDisciplineMenu, destinyDisciplineMenu);
    } else {
      menuEquivalence =
          calculateEqualMenuBasedOnGreaterMenuSize(destinyDisciplineMenu, originDisciplineMenu);
    }

    return menuEquivalence;
  }

  private static MenuEquivalence calculateEqualMenuBasedOnGreaterMenuSize(
      List<String> greaterMenu, List<String> secondMenu) {
    MenuEquivalence menuEquivalence = new MenuEquivalence();
    for (String disciplineMenu : greaterMenu) {
      boolean isEquivalentMenuItem = secondMenu.stream().anyMatch(s -> s.equals(disciplineMenu));
      if (isEquivalentMenuItem) {
        menuEquivalence.getEquivalenceMenu().add(disciplineMenu);
      } else {
        menuEquivalence.getNonEquivalenceMenu().add(disciplineMenu);
      }
    }
    return menuEquivalence;
  }
}
