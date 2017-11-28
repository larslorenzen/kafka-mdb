package de.dreamit.kafkamdb.rest;

import de.dreamit.kafkamdb.Producer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author llorenzen
 * @since 26.11.17
 */
@Stateless
@Path("send")
public class SendResource {

    @Inject
    private Producer producer;

    @POST
    public void sendMessage(String message) {
        producer.send(message);
    }

}
