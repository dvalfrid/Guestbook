package net.valfridsson.gastolibro.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings({"WeakerAccess", "EqualsWhichDoesntCheckParameterClass"})
public class Book {

    @JsonIgnore
    public final long id;
    @JsonProperty("title")
    public final String name;
    @JsonIgnore
    public final boolean viewAble;
    @JsonProperty("description")
    public final String description;

    public Book(long id, String name, boolean viewAble, String description) {
        this.id = id;
        this.name = name;
        this.viewAble = viewAble;
        this.description = description;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
