package com.redhat.andcip;

import io.smallrye.reactive.messaging.ce.IncomingCloudEventMetadata;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class MyReactiveMessagingApplication {

    @Inject
    Logger log;
    /**
     * Consume the uppercase channel (in-memory) and print the messages.
     **/
    @Incoming("movies")
    public CompletionStage<Void> consume(Message<Movie> message) {

        Optional<IncomingCloudEventMetadata> metadata = message.getMetadata(IncomingCloudEventMetadata.class);
        log.info(metadata.get().getSource());
        log.info(metadata.get().getId());
        log.info(metadata.get().getTimeStamp());
        log.info(message.getPayload());
        return message.ack();
    }
}
