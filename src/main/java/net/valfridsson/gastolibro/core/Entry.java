package net.valfridsson.gastolibro.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

@SuppressWarnings("WeakerAccess")
public class Entry {
    @JsonProperty("id")
    public final long id;
    @JsonProperty("name")
    public final String name;
    @JsonProperty("ip")
    public final String ip;
    @JsonProperty("header")
    public final String headline;
    @JsonProperty("email")
    public final String email;
    @JsonProperty("city")
    public final String city;
    @JsonProperty("country")
    public final String country;
    @JsonProperty("message")
    public final String message;
    @JsonProperty("date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public final LocalDateTime createTime;
    @JsonProperty("comment")
    public final String comment;
    @JsonIgnore
    public final boolean viewAble;

    private Entry(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.ip = builder.ip;
        this.headline = builder.headline;
        this.email = builder.email;
        this.city = builder.city;
        this.country = builder.country;
        this.message = builder.message;
        this.createTime = builder.createTime;
        this.viewAble = builder.viewAble;
        this.comment = builder.comment;
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
        private String ip;
        private String headline;
        private String email;
        private String city;
        private String country;
        private String message;
        private LocalDateTime createTime;
        private String comment;
        private boolean viewAble;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder ip(String ip) {
            this.ip = ip;
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
            this.message = msg;
            return this;
        }

        public Builder createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder viewAble(boolean viewAble) {
            this.viewAble = viewAble;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Entry build() {
            return new Entry(this);
        }
    }
}
