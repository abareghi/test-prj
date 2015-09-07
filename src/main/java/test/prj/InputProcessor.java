package test.prj;

import lombok.extern.slf4j.Slf4j;
import test.prj.api.ApiClient;
import test.prj.api.Persistence;
import test.prj.api.Record;

import java.util.Collection;

/**
 * Coordinates between remote api call and persistence layer.
 */
@Slf4j
public class InputProcessor {
    private String query;
    private ApiClient apiClient;
    private Persistence persistence;

    /**
     * Creates an instance of class with given parameters.
     *
     * @param query       the parameter to be passed to api, not null
     * @param apiClient   th instance of client to call api, not null
     * @param persistence the instance of persistence provider to save the result, not null
     */
    public InputProcessor(String query, ApiClient apiClient, Persistence persistence) {
        this.query = query;
        this.apiClient = apiClient;
        this.persistence = persistence;
    }

    /**
     * Passes the given parameter to remote api and using the persists its result.
     */
    public void process() {
        log.info("Processing input (string={})", query);

        Collection<Record> records = apiClient.call(query);
        persistence.save(records);
    }
}