# language: pt
# encoding UTF-8

@Componente
Funcionalidade: Escrever dados de uma pessoa na fila Kafka

  @pessoa
  Cenario: Realizar escrita e consumo de mensagem no Kafka

    Dado produzo mensagem com dados da pessoa no "test-topic"
    Quando consumo a mensagem  no "test-topic"
    Então dados da pessoa foram gravados com sucesso


