package test.prj.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider
import org.apache.cxf.endpoint.Server
import org.apache.cxf.interceptor.LoggingInInterceptor
import org.apache.cxf.interceptor.LoggingOutInterceptor
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider
import spock.lang.Specification
import test.prj.api.Record

/**
 *
 */
class RestApiClientTest extends Specification {
    public static final String ENPOINT_ADDRESS = 'http://localhost:8181/'
    private Server server
    private RestApiClient client
    private Provider apiTestProvider

    def setup() {
        def resource = new ApiTestResource()
        resource.provider = apiTestProvider = Mock(Provider)
        server = startServer(resource)

        client = new RestApiClient("$ENPOINT_ADDRESS/api/test/")
    }

    def cleanup() {
        if (server.isStarted()) {
            server.destroy()
        }
    }

    def "call"() {
        given: "remote service is up and running"
        def givenRecords = [TestUtils.givenRecord(1, "name-1"), TestUtils.givenRecord(2, "name-2")]
        apiTestProvider.search('123') >> givenRecords

        when: "I call api with a valid parameter"
        List<Record> listOfRecords = client.call('123')

        then: "received result is as expected"
        listOfRecords
        listOfRecords.eachWithIndex { it, index ->
            listOfRecords.any { it.id == givenRecords[index].id && it.name == givenRecords[index].name }
        }
    }

    def "call when remote server is not available"() {
        given: "remote server is not available"
        server.destroy()

        when: "I call api with a valid parameter"
        client.call('123')

        then: "an exception is thrown"
        thrown(Exception)
    }

    private Server startServer(Object resource) {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean()
        sf.setResourceClasses(resource.class)
        sf.setResourceProvider(resource.class, new SingletonResourceProvider(resource))
        sf.setAddress(ENPOINT_ADDRESS)

        sf.setProvider(new JacksonJsonProvider(new ObjectMapper()))
        sf.getInInterceptors().add(new LoggingInInterceptor())
        sf.getOutInterceptors().add(new LoggingOutInterceptor())
        return sf.create()
    }
}