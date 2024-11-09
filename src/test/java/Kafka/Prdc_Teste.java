package Kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Prdc_Teste {

    // Criação do logger
    private static final Logger logger = LoggerFactory.getLogger(Prdc_Teste.class);


   // public boolean produzindoTopico(JSONObject objt, String topico) {
   public static void main(String[] args) {
        Mensagem msg = new Mensagem();
        // Configurações do Kafka Producer
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");// Endereço do broker Kafka
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Criação do Kafka Producer
        Producer<String, String> producer = new KafkaProducer<>(props);

        // Enviar mensagem para o tópico
       String topic = "test-topic";
        String key = "chave-mensagem";
        String value = msg.geraMensagem().toString();

        try {
            logger.info("Enviando mensagem para o tópico: {}", topic);
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

            // Envia a mensagem e adiciona um callback para logar o resultado
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    logger.info("Mensagem enviada com sucesso. Tópico: {}, Partição: {}, Offset: {}",
                            metadata.topic(), metadata.partition(), metadata.offset());
                } else {
                    logger.error("Erro ao enviar mensagem", exception);

                }
            });
        } catch (Exception e) {
            logger.error("Erro durante o envio da mensagem", e);
            //return false;
        } finally {
            producer.close();
            logger.info("Kafka Producer fechado.");
            //return true;
        }
    }
}