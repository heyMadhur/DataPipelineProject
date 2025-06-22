package com.learning.fetcher;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import jakarta.annotation.PostConstruct;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Component
public class WikimediaStreamFetcher implements SmartLifecycle {

    private final ProducerTemplate producerTemplate;
    private final CamelContext camelContext;
    private boolean isRunning;
    private BackgroundEventSource eventSource;

    public WikimediaStreamFetcher(ProducerTemplate producerTemplate, CamelContext camelContext) {
        this.producerTemplate = producerTemplate;
        this.camelContext= camelContext;
    }

    @Override
    public void start() {
        if(!isRunning) {
            Route route= camelContext.getRoute("wikimedia-route");
            System.out.println("Route= "+route.getEndpoint());
            BackgroundEventHandler eventHandler= new WikimediaEventHandler(producerTemplate);
            String url= "https://stream.wikimedia.org/v2/stream/recentchange";

            EventSource.Builder baseBuilder= new EventSource.Builder(URI.create(url));
            BackgroundEventSource.Builder builder= new BackgroundEventSource.Builder(eventHandler, baseBuilder);
            eventSource= builder.build();

            eventSource.start();
            isRunning=true;
            System.out.println("Wikimedia stream started after Camel context initialization");
        }
    }

    @Override
    public void stop() {
        if(eventSource!=null) {
            eventSource.close();
        }
        isRunning=false;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
