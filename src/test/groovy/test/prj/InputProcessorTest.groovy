package test.prj

import spock.lang.Specification
import test.prj.api.ApiClient
import test.prj.api.Persistence

/**
 */
class InputProcessorTest extends Specification {
    private ApiClient apiClient
    private Persistence persistence

    def setup() {
        apiClient = Mock(ApiClient)
        persistence = Mock(Persistence)

    }

    def "process"() {
        given: "an instance of InputProcessor and a given string"
        def givenString = 'givenString'
        def processor = new InputProcessor(givenString, apiClient, persistence)

        when: "I call process method of given instance"
        processor.process()

        then: "proper logic is executed"
        1 * apiClient.call(givenString) >> []
        1 * persistence.save(_ as Collection)
    }
}