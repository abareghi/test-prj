package test.prj.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 *
 */
public class Record {

    @Getter
    @JsonProperty(value = "_id")
    private int id;
    @Getter
    private String key;
    @Getter
    private String name;
    @Getter
    private String fullName;
    @Getter
    @JsonProperty(value = "iata_airport_code")
    private String iataAirportCode;
    @Getter
    private String type;
    @Getter
    private String country;
    @Getter
    @JsonProperty("geo_position")
    private GeoPosition geoPosition;
    @Getter
    @JsonProperty("location_id")
    private int localtionId;
    @Getter
    private boolean inEurope;
    @Getter
    private String countryCode;
    @Getter
    private boolean coreCountry;
    @Getter
    private String distance;

    public Record(
            @JsonProperty(value = "_id") int id,
            @JsonProperty(value = "key") String key,
            @JsonProperty(value = "name") String name,
            @JsonProperty(value = "fullName") String fullName,
            @JsonProperty(value = "iata_airport_code") String iataAirportCode,
            @JsonProperty(value = "type") String type,
            @JsonProperty(value = "country") String country,
            @JsonProperty("geo_position") GeoPosition geoPosition,
            @JsonProperty("locationId") int localtionId,
            @JsonProperty(value = "inEurope") boolean inEurope,
            @JsonProperty(value = "countryCode") String countryCode,
            @JsonProperty(value = "coreCountry") boolean coreCountry,
            @JsonProperty(value = "distance") String distance) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.fullName = fullName;
        this.iataAirportCode = iataAirportCode;
        this.type = type;
        this.country = country;
        this.geoPosition = geoPosition;
        this.localtionId = localtionId;
        this.inEurope = inEurope;
        this.countryCode = countryCode;
        this.coreCountry = coreCountry;
        this.distance = distance;
    }


    public static class GeoPosition {
        @Getter
        private String latitude;
        @Getter
        private String longitude;

        @JsonCreator
        public GeoPosition(
                @JsonProperty("latitude") String latitude,
                @JsonProperty("longitude") String longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
}