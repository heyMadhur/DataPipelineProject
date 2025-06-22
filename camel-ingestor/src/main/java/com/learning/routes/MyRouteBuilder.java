package com.learning.routes;

import jakarta.annotation.PostConstruct;
import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder {

    @Autowired
    private CamelContext camelContext;

    @PostConstruct
    private void buildRoutes() throws Exception {
        camelContext.addRoutes(WikimediaRoute.builder().build());
    }

}
