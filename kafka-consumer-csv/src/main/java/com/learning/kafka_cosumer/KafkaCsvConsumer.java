package com.learning.kafka_cosumer;

import com.learning.dtos.WikiChangeDTO;
import com.opencsv.CSVWriter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class KafkaCsvConsumer {

    private static final String CSV_FILE = "database/csv/wikimedia_changes.csv";

    public KafkaCsvConsumer() {
        // Write header once
        try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE))) {
            System.out.println("Setting up File");
            writer.writeNext(new String[]{"Title", "User", "Domain", "Bot", "Comment", "Server URL", "Full URL"});
        } catch (IOException e) {
            throw new RuntimeException("Error initializing CSV file", e);
        }
    }

    @KafkaListener(topics = "wikimedia_changes_recently", groupId = "csv-group")
    public void consume(ConsumerRecord<String, String> record) {
        try {
            String json = record.value();
            WikiChangeDTO dto = parseJsonToDTO(json);

            try (CSVWriter writer = new CSVWriter(new FileWriter(CSV_FILE, true))) {
                writer.writeNext(new String[]{
                        dto.getTitle(),
                        dto.getUser(),
                        dto.getDomain(),
                        String.valueOf(dto.isBot()),
                        dto.getComment(),
                        dto.getServer_url(),
                        dto.getFull_url()
                });
            }
            System.out.println("Wrote data in file, data= "+dto);

        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
        }
    }

    private WikiChangeDTO parseJsonToDTO(String json) {
        // You can replace this with Jackson ObjectMapper if you're using it
        // For now, assume Camel already serialized it properly as JSON string
        // and you're using Jackson:
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().readValue(json, WikiChangeDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Invalid JSON format: " + json, e);
        }
    }
}
