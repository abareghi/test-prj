package test.prj.impl

import test.prj.api.Record

import javax.ws.rs.*

/**
 *
 */
@Path("/api")
@Produces("application/json")
@Consumes("application/json")
class ApiTestResource {
    private Provider provider

    @GET
    @Path("/test/{queryString}")
    public List<Record> query(@PathParam("queryString") String queryString) {
        return provider.search(queryString)
    }
}

interface Provider {
    List<Record> search(String query)
}