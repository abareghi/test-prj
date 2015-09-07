package test.prj;

import lombok.extern.slf4j.Slf4j;
import test.prj.impl.FilePersistence;
import test.prj.impl.RestApiClient;

/**
 * A command line tool which takes a string input and passes it to a destination rest api.
 * The result, if not empty is written to a CSV file.
 */
@Slf4j
public class Main {
    private static final String ENDPOINT = "http://api.goeuro.com/api/v2/position/suggest/en";
    private static final String FILE_NAME = "Results.csv";

    public static void main(String[] args) {
        // read the input
        if (args.length != 1) {
            log.error("Please provide an input parameter");
            return;
        }

        try {
            new InputProcessor(args[0], new RestApiClient(ENDPOINT), new FilePersistence(FILE_NAME)).process();

        } catch (Exception e) {
            log.error("Terminated execution due to unexpected error. Please check log file for further details", e);
        }
    }
}