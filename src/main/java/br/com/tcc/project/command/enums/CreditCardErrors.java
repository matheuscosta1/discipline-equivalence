package br.com.tcc.project.command.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CreditCardErrors {
  PCC0001("Cartões não encontrados para esse documento.", 1000),
  PCC0002("Cartão não foi encontrado {0}.", 1000),
  PCC0003("Erro na criptografia do cartão.", 1000),
  PCC0004("Erro na criptografia do código de verificação.", 1000),
  PCC0005("Bandeira do cartão não aceita pelo sistema.", 1000),
  PCC0006("Pessoa não encontrada para o cartão {0}.", 1000),
  PCC0007("Pessoa não encontrada pelo identificador.", 1000),
  PCC0008("Falha ao carregar o provedor de chaves.", 1000),
  PCC0009("Falha ao carregar chaves de criptografia para cartão de crédito.", 1000),
  PCC0010("Falha ao criar Encrypt Cipher de cartão de crédito.", 1000),
  PCC0011("Falha ao criar Decrypt Cipher de cartão de crédito.", 1000),
  PCC0012("Falha ao criar Encrypt Cipher para CVV.", 1000),
  PCC0013("Falha ao criar Decrypt Cipher para CVV.", 1000),
  PCC0014("Falha ao salvar dados no Redis DB", 1000),
  PCC0015("Falha ao recuperar Zero Dollar", 1000);

  private final String message;
  private final int group;

  public String message() {
    return message;
  }

  public int group() {
    return group;
  }
}
