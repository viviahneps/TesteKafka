#!/bin/bash

# Espera o Kafka iniciar
sleep 10

# Cria o tópico no Kafka
/opt/kafka/bin/kafka-topics.sh --create --topic meu-topico --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

# Exibe os tópicos criados
/opt/kafka/bin/kafka-topics.sh --list --bootstrap-server localhost:9092