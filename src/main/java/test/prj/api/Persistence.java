package test.prj.api;

import java.util.Collection;

/**
 * Provides an abstraction of persistence.
 */
public interface Persistence {
    /**
     * Saves list of given records
     *
     * @param records the list of records to be persisted, not null.
     */
    void save(Collection<Record> records);
}