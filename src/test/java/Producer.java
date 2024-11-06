import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class Producer {
    public static void main(String[] args) {
        // Configurações do Kafka Producer
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Criar o Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // Enviar uma mensagem
        ProducerRecord<String, String> record = new ProducerRecord<>("topico-entrada", "chave", "mensagem de teste");

        try {
            RecordMetadata metadata = producer.send(record).get();
            System.out.printf("Mensagem enviada para o tópico %s, partição %d, offset %d%n",
                    metadata.topic(), metadata.partition(), metadata.offset());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}