package net.valfridsson.gastolibro.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

/**
 * <pre>
 *   {
 *     id: Int,
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

@SuppressWarnings("WeakerAccess")
public class Entry {
    @JsonProperty("id")
    public final long id;
    @JsonProperty("title")
    public final String title;
    @JsonProperty("text")
    public final String text;
    @JsonProperty("time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public final LocalDateTime time;
    @JsonProperty("name")
    public final String name;
    @JsonProperty("email")
    public final String email;
    @JsonProperty("country")
    public final String country;
    @JsonProperty("city")
    public final String city;

    private Entry(Builder builder) {
        this.id = builder.id;
        this.title = builder.headline;
        this.text = builder.text;
        this.time = builder.createTime;
        this.name = builder.name;
        this.email = builder.email;
        this.country = builder.country;
        this.city = builder.city;
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object o) {
       return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private String name;
        private String headline;
        private String email;
        private String city;
        private String country;
        private String text;
        private LocalDateTime createTime;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder headline(String headline) {
            this.headline = headline;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder message(String msg) {
            this.text = msg;
            return this;
        }

        public Builder time(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        public Entry build() {
            return new Entry(this);
        }
    }
}
