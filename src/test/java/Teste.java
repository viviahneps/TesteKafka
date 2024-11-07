

 import org.apache.kafka.clients.consumer.ConsumerRecord;
 import org.apache.kafka.clients.consumer.KafkaConsumer;
 import org.apache.kafka.clients.producer.KafkaProducer;
 import org.apache.kafka.clients.producer.ProducerRecord;
 import org.apache.kafka.clients.producer.RecordMetadata;
 import org.junit.jupiter.api.AfterAll;
 import org.junit.jupiter.api.BeforeAll;
 import org.junit.jupiter.api.Test;
 import org.testcontainers.containers.KafkaContainer;
 import org.testcontainers.utility.DockerImageName;
 import java.time.Duration;
 import java.util.Collections;
 import java.util.Properties;
 import static org.junit.jupiter.api.Assertions.assertEquals;

public class Teste  {

    private static KafkaContainer kafkaContainer;
    private static KafkaProducer<String, String> producer;
    private static KafkaConsumer<String, String> consumer;

    @BeforeAll
    public static void setUp() {
        // Iniciar o container Kafka
        kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));
        kafkaContainer.start();

        // Configurações do Producer
        Properties producerProps = new Properties();
        producerProps.put("bootstrap.servers", kafkaContainer.getBootstrapServers());
        producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(producerProps);

        // Configurações do Consumer
        Properties consumerProps = new Properties();
        consumerProps.put("bootstrap.servers", kafkaContainer.getBootstrapServers());
        consumerProps.put("group.id", "test-group");
        consumerProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Collections.singletonList("test-topic"));
    }

    @AfterAll
    public static void tearDown() {
        // Fechar o producer e o consumer
        producer.close();
        consumer.close();

        // Parar o container Kafka
        kafkaContainer.stop();
    }

    @Test
    public void testKafkaProducerAndConsumer() throws Exception {
        // Enviar uma mensagem para o Kafka
        String topic = "test-topic";
        String key = "test-key";
        String value = "test-message";
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        RecordMetadata metadata = producer.send(record).get();

        // Verificar se a mensagem foi enviada corretamente
        assertEquals(topic, metadata.topic());

        // Consumir a mensagem do Kafka
        ConsumerRecord<String, String> consumedRecord = consumer.poll(Duration.ofMillis(1000)).iterator().next();

        // Verificar se a mensagem consumida é a mesma que foi enviada
        assertEquals(key, consumedRecord.key());
        assertEquals(value, consumedRecord.value());
    }
}
