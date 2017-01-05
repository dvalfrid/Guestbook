package net.valfridsson.gastolibro.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings({"WeakerAccess", "EqualsWhichDoesntCheckParameterClass"})
public class GuestBook {

    @JsonProperty("id")
    public final long id;
    @JsonProperty("name")
    public final String name;
    @JsonProperty("viewAble")
    public final boolean viewAble;

    public GuestBook(long id, String name, boolean viewAble) {
        this.id = id;
        this.name = name;
        this.viewAble = viewAble;
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
