package test.prj.impl

import test.prj.api.Record

/**
 */
class TestUtils {

    private static Record givenRecord(int id, String name) {
        return new Record(id, "key", name, "fulleName", "airportCode", "type", "country", new Record.GeoPosition('1', '2'),
                2344, true, "cas", false, null)
    }
}