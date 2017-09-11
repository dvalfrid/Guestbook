package net.valfridsson.gastolibro.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * <pre>
 *   {
 *     title: String,
 *     text: String,
 *     time: String, YY/MM/DD
 *     name: String,
 *     email: String,
 *     country: String,
 *     city: String
 *   }
 * </pre>
 */
public class CreateEntry {
    @NotEmpty(message = "'title' must not be empty")
    public final String title;
    @NotEmpty(message = "'text' must not be empty")
    public final String text;
    @NotEmpty(message = "'name' must not me empty")
    public final String name;
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "'email' is not valid")
    public final String email;
    @NotEmpty(message = "'city' must not be empty")
    public final String city;
    @NotEmpty(message = "'country' must not be empty")
    public final String country;

    @JsonCreator
    public CreateEntry(@JsonProperty("title") String title,
                       @JsonProperty("text") String text,
                       @JsonProperty("name") String name,
                       @JsonProperty("email") String email,
                       @JsonProperty("city") String city,
                       @JsonProperty("country") String country) {
        this.name = name;
        this.title = title;
        this.email = email;
        this.city = city;
        this.country = country;
        this.text = text;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
