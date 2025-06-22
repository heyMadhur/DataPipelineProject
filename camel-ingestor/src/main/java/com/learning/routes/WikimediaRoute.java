package com.learning.routes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.learning.processors.WikimediaMessageProcessor;
import lombok.Builder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Builder
public class WikimediaRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        // Global error handler
        onException(Exception.class)
                .log("Camel Stream Error: ${exception.message}")
                .handled(true);

        // Active route definition
        from("direct:stream-data")
                .routeId("wikimedia-route")
                .log("New Message from WikiMedia")
                .log("Raw message: ${body}")
                .process(new WikimediaMessageProcessor())
                .log("Processed Message: ${body}")
                .to("kafka:wikimedia_changes_recently?brokers=localhost:9092")
                .end();
    }
}
