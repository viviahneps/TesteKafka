import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {
        // Configurações do Kafka Consumer
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "meu-grupo");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // Criar o Consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Inscrever-se no tópico
        consumer.subscribe(Collections.singletonList("topico-saida"));

        // Consumir mensagens
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("Recebido: chave = %s, valor = %s, partição = %d, offset = %d%n",
                        record.key(), record.value(), record.partition(), record.offset());
            }
        }
    }
}