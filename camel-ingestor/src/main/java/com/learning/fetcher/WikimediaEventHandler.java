package com.learning.fetcher;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import org.apache.camel.ProducerTemplate;

public class WikimediaEventHandler implements BackgroundEventHandler {

    private ProducerTemplate producerTemplate;

    public WikimediaEventHandler(ProducerTemplate producerTemplate) {
        this.producerTemplate= producerTemplate;
    }

    @Override
    public void onOpen() throws Exception {
        System.out.println("Connected to Wikimedia stream");
    }

    @Override
    public void onClosed() throws Exception {
        System.out.println("Disconnected from Wikimedia stream");
    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        try {
//            System.out.println("EVENTHANDLER-> Data from URL-->"+messageEvent.getData());
            producerTemplate.sendBody("direct:stream-data", messageEvent.getData());
        } catch (Exception e) {
            System.err.println("Failed to send to Camel: " + e.getMessage());
        }

    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println("Event Handler Stream Error: " + throwable.getMessage());
    }
}
