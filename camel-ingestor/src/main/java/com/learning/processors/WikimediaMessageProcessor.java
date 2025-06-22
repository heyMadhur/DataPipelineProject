package com.learning.processors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class WikimediaMessageProcessor implements Processor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {
        JsonNode jsonNode= objectMapper.readTree(exchange.getMessage().getBody(String.class));

        String title = jsonNode.path("title").asText();
        String user = jsonNode.path("user").asText();
        String domain = jsonNode.path("meta").path("domain").asText();
        boolean bot = jsonNode.path("bot").asBoolean();
        String comment = jsonNode.path("comment").asText();
        String serverUrl = jsonNode.path("server_url").asText();
        String fullUrl = serverUrl + "/wiki/" + title;

        ObjectNode newJson = objectMapper.createObjectNode();
        newJson.put("title", title);
        newJson.put("user", user);
        newJson.put("domain", domain);
        newJson.put("bot", bot);
        newJson.put("comment", comment);
        newJson.put("server_url", serverUrl);
        newJson.put("full_url", fullUrl);

        String newJsonString = objectMapper.writeValueAsString(newJson);
        exchange.getMessage().setBody(newJsonString);
    }
}
