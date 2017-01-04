package net.valfridsson.gastolibro.core;

import java.time.LocalDateTime;

public class Entry {
    public final String id;
    public final String name;
    public final String ip;
    public final String headline;
    public final String email;
    public final String city;
    public final String country;
    public final String msg;
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
        this.msg = builder.msg;
        this.createTime = builder.createTime;
        this.viewAble = builder.viewAble;
    }

    public class Builder {
        private String id;
        private String name;
        private String ip;
        private String headline;
        private String email;
        private String city;
        private String country;
        private String msg;
        private LocalDateTime createTime;
        private boolean viewAble;

        public Builder id(String id) {
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

        public Builder msg(String msg) {
            this.msg = msg;
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
