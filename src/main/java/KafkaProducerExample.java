import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class KafkaProducerExample {
    public static void main(String[] args) {
        // Set Kafka broker properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); // Change to your Kafka broker address
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Create a KafkaProducer instance
        Producer<String, String> producer = new KafkaProducer<>(props);

        // Define the topic to which you want to send messages
        String topic = "my_topic"; // Change to your topic name

        // Define the message you want to send
        String message = "Hello, Kafka 123!";

        // Create a ProducerRecord with the topic and message
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);

        // Send the message asynchronously
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    System.out.println("Message sent successfully to topic " + metadata.topic() +
                            ", partition " + metadata.partition() +
                            ", offset " + metadata.offset());
                }
            }
        });

        // Close the producer
        producer.close();
    }
}
