package de.dreamit.kafkamdb;

import fish.payara.cloud.connectors.kafka.api.KafkaListener;
import fish.payara.cloud.connectors.kafka.api.OnRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;

/**
 * @author llorenzen
 * @since 25.11.17
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "bootstrapServersConfig", propertyValue = "${kafka.servers}"),
        @ActivationConfigProperty(propertyName = "clientId", propertyValue = "DreamIT"),
        @ActivationConfigProperty(propertyName = "groupIdConfig", propertyValue = "testGroup"),
        @ActivationConfigProperty(propertyName = "topics", propertyValue = "test,test2"),
        @ActivationConfigProperty(propertyName = "autoCommitInterval", propertyValue = "100"),
        @ActivationConfigProperty(propertyName = "pollInterval", propertyValue = "3000"),
        @ActivationConfigProperty(propertyName = "keyDeserializer", propertyValue = "org.apache.kafka.common.serialization.StringDeserializer"),
        @ActivationConfigProperty(propertyName = "valueDeserializer", propertyValue = "org.apache.kafka.common.serialization.StringDeserializer")
})
public class Receiver implements KafkaListener {

    @OnRecord(topics="test")
    public void receiveMessage(ConsumerRecord record) {
        // Handle record
        System.out.println("Record = " + record.toString());
    }
}
