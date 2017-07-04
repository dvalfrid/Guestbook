package net.valfridsson.gastolibro.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import org.apache.commons.collections.iterators.EntrySetMapIterator;
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
    @JsonProperty("entries")
    public final ImmutableList<Entry> entries;

    public Book(long id, String name, boolean viewAble, String description) {
        this(id, name, viewAble, description, ImmutableList.of());
    }

    private Book(long id, String name, boolean viewAble, String description, ImmutableList<Entry> entries) {
        this.id = id;
        this.name = name;
        this.viewAble = viewAble;
        this.description = description;
        this.entries = entries;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public Book with(ImmutableList<Entry> entries) {
        return new Book(id, name, viewAble, description, entries);
    }
}
