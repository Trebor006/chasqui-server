package com.mibu.chasquiserver.service;

import org.jboss.resteasy.specimpl.BuiltResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Component
public class ElasticService {
    private static final String ELASTIC_URI = "ELASTIC_SEARCH_URI";

    @Autowired
    private Environment env;

    public String callPost(String indexURI, String query) {
        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target(this.getElasticURI() + indexURI);
        BuiltResponse response = (BuiltResponse) target.request().accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(query));
        int status = response.getStatus();

        return response.readEntity(String.class);
    }

    public String callPut(String indexURI, String query) {
        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target(this.getElasticURI() + indexURI);
        BuiltResponse response = (BuiltResponse) target.request().accept(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(query));
        int status = response.getStatus();

        return response.readEntity(String.class);
    }

    private String getElasticURI() {
        return env.getProperty(ELASTIC_URI);
    }
}
