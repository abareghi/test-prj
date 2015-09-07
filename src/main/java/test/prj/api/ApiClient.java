package test.prj.api;

import java.util.Collection;

/**
 * Represents an abstraction of available api.
 */
public interface ApiClient {
    /**
     * Calls remote api with given parameter.
     *
     * @param queryString given parameter to be passed to api, not null.
     * @return a list of Records returned from server, not null
     */
    Collection<Record> call(String queryString);
}