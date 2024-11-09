package Kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

    public class Cnsm_Teste {

        // Criação do logger
        private static final Logger logger = LoggerFactory.getLogger(Cnsm_Teste.class);
        public static void main(String[] args) {

            String dados = null;

            // Configurações do Kafka Consumer
            Properties props = new Properties();
            props.put("bootstrap.servers", "localhost:9092"); // Endereço do broker Kafka
            props.put("group.id", "meu-grupo-consumidor"); // ID do grupo de consumidores
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("auto.offset.reset", "earliest"); // Para começar a ler do início do tópico

            // Criação do Kafka Consumer
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

            // Inscrever-se no tópico
            String topic = "test-topic";
            consumer.subscribe(Collections.singletonList(topic));

            // Consumir mensagens
            try {
                while (true) {
                    // Poll para buscar mensagens
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                    for (ConsumerRecord<String, String> record : records) {
                        // Logando as mensagens consumidas
                        logger.info("Mensagem consumida - Offset: {}, Chave: {}, Valor: {}",
                                record.offset(), record.key(), record.value());
                         dados  = record.value();
                         System.out.println("Dados: "+dados);
                    }
                }
            } catch (Exception e) {
                logger.error("Erro ao consumir mensagens do Kafka", e);
                //return dados;
            } finally {
                consumer.close();
                logger.info("Kafka Consumer fechado.");
                //return dados ;
            }
        }
    }

