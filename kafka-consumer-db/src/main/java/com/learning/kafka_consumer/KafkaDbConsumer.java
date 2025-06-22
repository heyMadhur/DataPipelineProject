package com.learning.kafka_consumer;

import com.learning.entity.WikimediaChange;
import com.learning.repo.WikimediaChangeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDbConsumer {
    private static final Logger LOGGER= LoggerFactory.getLogger(KafkaDbConsumer.class);

    @Autowired
    private WikimediaChangeRepo repo;

    @KafkaListener(topics = "wikimedia_changes_recently", groupId = "myGroup")
    public void consume(WikimediaChange message) {
        LOGGER.info(String.format("Event Message Received -> %s", message));

        WikimediaChange data=WikimediaChange.builder()
                .title(message.getTitle())
                .comment(message.getComment())
                .domain(message.getDomain())
                .fullUrl(message.getFullUrl())
                .serverUrl(message.getServerUrl())
                .bot(message.isBot())
                .user(message.getUser())
                .build();

        repo.save(data);

    }

}
