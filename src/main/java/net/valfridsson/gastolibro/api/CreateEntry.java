package net.valfridsson.gastolibro.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

public class CreateEntry {
    @NotEmpty(message = "'name' must not me empty")
    public final String name;
    @NotEmpty(message = "'headline' must not be empty")
    public final String headline;
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "'email' is not valid")
    public final String email;
    @NotEmpty(message = "'city' must not be empty")
    public final String city;
    @NotEmpty(message = "'country' must not be empty")
    public final String country;
    @NotEmpty(message = "'message' must not be empty")
    public final String message;

    @JsonCreator
    public CreateEntry(@JsonProperty("name") String name,
                       @JsonProperty("headline") String headline,
                       @JsonProperty("email") String email,
                       @JsonProperty("city") String city,
                       @JsonProperty("country") String country,
                       @JsonProperty("message") String msg) {
        this.name = name;
        this.headline = headline;
        this.email = email;
        this.city = city;
        this.country = country;
        this.message = msg;
    }
}
