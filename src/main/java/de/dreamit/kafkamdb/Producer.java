package de.dreamit.kafkamdb;

import fish.payara.cloud.connectors.kafka.api.KafkaConnection;
import fish.payara.cloud.connectors.kafka.api.KafkaConnectionFactory;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.resource.ConnectionFactoryDefinition;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author llorenzen
 * @since 25.11.17
 */
@Startup
@Singleton
@ConnectionFactoryDefinition(
        name = "java:app/kafka/factory",
        interfaceName = "fish.payara.cloud.connectors.kafka.api.KafkaConnectionFactory",
        resourceAdapter = "kafka-rar"
)
public class Producer {

    private static final Logger logger = Logger.getLogger(Producer.class.getName());

    @Resource(lookup = "java:app/kafka/factory")
    private KafkaConnectionFactory factory;

    @PostConstruct
    public void init() {
        try (KafkaConnection connection = factory.createConnection()) {
            connection.send(new ProducerRecord<>("test", "hello", "world"), this::onCompletion);
            connection.send(new ProducerRecord<>("test", "hello2", "world2"), this::onCompletion);
            connection.send(new ProducerRecord<>("test", "hello3", "world3"), this::onCompletion);
            connection.send(new ProducerRecord<>("test", "hello4", "world4"), this::onCompletion);
            connection.send(new ProducerRecord<>("test", "hello5", "world5"), this::onCompletion);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Connection to kafka failed", ex);
        }
    }

    private void onCompletion(RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            logger.log(Level.SEVERE, "Failed to send message", exception);
        } else {
            logger.log(Level.INFO, "message sent: {0}", metadata);
        }
    }

    public void send(String message) {
        try (KafkaConnection connection = factory.createConnection()) {
            connection.send(new ProducerRecord<>("test", message), this::onCompletion);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Connection to kafka failed", ex);
        }
    }
}
