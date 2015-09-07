package test.prj.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import test.prj.api.Persistence;
import test.prj.api.Record;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * File based implementation of Persistence. It uses the file name passed to it to persist given info.
 * NOTE: It will only save id, name of given record as well as latitude and longitude of its GeoLocation.
 * It will use "NULL" wherever above properties are not available.
 */
@Slf4j
public class FilePersistence implements Persistence {
    private String file;

    /**
     * Creates an instance of class with given parameter.
     *
     * @param file name of the file to save records, not null.
     */
    public FilePersistence(String file) {
        this.file = file;
    }

    @Override
    public void save(Collection<Record> records) {
        log.info("Saving {} records into file (name= {})", records.size(), file);


        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = new CsvSchema.Builder()
                .addColumn("id")
                .addColumn("name")
                .addColumn("type")
                .addColumn("latitude")
                .addColumn("longitude")
                .setNullValue("NULL")
                .setUseHeader(true)
                .build();
        try {
            mapper.writer().with(schema).with(JsonGenerator.Feature.IGNORE_UNKNOWN)
                    .writeValue(new File(file), toFileRecords(records));

        } catch (IOException e) {
            throw new RuntimeException(String.format("Error while serializing to file: %s", file), e);
        }
    }

    private List<FileRecord> toFileRecords(Collection<Record> records) {
        List<FileRecord> result = new ArrayList<FileRecord>();
        for (Record record : records) {
            result.add(new FileRecord(record));
        }

        return result;
    }

    private class FileRecord {
        @Getter
        private int id;
        @Getter
        private String name;
        @Getter
        private String type;
        @Getter
        private String latitude;
        @Getter
        private String longitude;

        private FileRecord(Record record) {
            this.id = record.getId();
            this.name = record.getName();
            this.type = record.getType();
            if (record.getGeoPosition() != null) {
                this.latitude = record.getGeoPosition().getLatitude();
                this.longitude = record.getGeoPosition().getLongitude();
            }

        }
    }
}