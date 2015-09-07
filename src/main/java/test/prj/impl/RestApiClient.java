package test.prj.impl;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.client.ClientConfig;
import test.prj.api.ApiClient;
import test.prj.api.Record;

import java.util.Collection;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * Rest implementation of api client.
 */
@Slf4j
public class RestApiClient implements ApiClient {
    private String endpoint;

    /**
     * Creates an instance with give parameter.
     *
     * @param endpoint the endpoint to remote api, not null
     */
    public RestApiClient(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Collection<Record> call(String queryString) {
        log.info("Calling rest api (endpoint= {}) with input (queryString= {})", endpoint, queryString);
        ClientConfig cfg = new ClientConfig();
        cfg.register(JacksonJsonProvider.class);
        Client client = ClientBuilder.newClient(cfg);
        WebTarget target = client.target(endpoint).path(queryString);

        return target.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<Collection<Record>>() { });
    }
}