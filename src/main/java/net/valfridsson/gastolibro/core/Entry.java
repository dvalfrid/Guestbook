package net.valfridsson.gastolibro.core;

import java.time.LocalDateTime;

public class Entry {
    public final long id;
    public final String name;
    public final String ip;
    public final String headline;
    public final String email;
    public final String city;
    public final String country;
    public final String message;
    public final LocalDateTime createTime;
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
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder builder(Entry entry) {
        return newBuilder()
            .id(entry.id)
            .name(entry.name)
            .ip(entry.ip)
            .headline(entry.headline)
            .email(entry.email)
            .city(entry.city)
            .country(entry.country)
            .message(entry.message)
            .createTime(entry.createTime)
            .viewAble(entry.viewAble);
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

        public Entry build() {
            return new Entry(this);
        }
    }
}
