package test.prj.impl

import spock.lang.Specification

/**
 *
 */
class FilePersistenceTest extends Specification {

    public static final String FILE_NAME = "testResult.csv"

    def setup() {
        new File(FILE_NAME).deleteOnExit()
    }

    def cleanup() {
        new File(FILE_NAME).deleteOnExit()
    }

    def "save"() {
        given: "I have a list of records"
        def givenRecord = [TestUtils.givenRecord(1, "name-1"), TestUtils.givenRecord(2, "name-2")]
        def persistence = new FilePersistence(FILE_NAME)

        when: "I persist them"
        persistence.save(givenRecord)

        then: "the result is successfully persisted in csv file"
        notThrown(Exception)
        new File(FILE_NAME).exists()
    }
}