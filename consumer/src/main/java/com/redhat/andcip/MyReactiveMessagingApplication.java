package com.redhat.andcip;

import io.smallrye.reactive.messaging.ce.IncomingCloudEventMetadata;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class MyReactiveMessagingApplication {

    @Inject
    Logger log;

    /**
     * Consume the movie channel (in-memory) and print the messages.
     **/
    @Incoming("movies")
    public CompletionStage<Void> consume(Message<Movie> message) {

        IncomingCloudEventMetadata<Movie> metadata = message.getMetadata(IncomingCloudEventMetadata.class).orElseThrow();
        log.info("source: " + metadata.getSource());
        log.info("id: " + metadata.getId());
        log.info("Timestamp: " + metadata.getTimeStamp());
        log.info("Type: " + metadata.getType());
        log.info("DataContentType: " + metadata.getDataContentType());
        log.info("Data: " + metadata.getData());
        return message.ack();
    }
}
