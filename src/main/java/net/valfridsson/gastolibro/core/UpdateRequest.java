package net.valfridsson.gastolibro.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

@SuppressWarnings("WeakerAccess")
public class UpdateRequest {
  @JsonProperty("id")
  public final long id;
  @JsonProperty("x")
  public final int x;
  @JsonProperty("date")
  public final LocalDateTime date;


  private UpdateRequest(Builder builder) {
    this.id = builder.id;
    this.x = builder.x;
    this.date = builder.date;
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
    private int x;
    private LocalDateTime date;

    public Builder id(long id) {
      this.id = id;
      return this;
    }

    public Builder x(int x) {
      this.x = x;
      return this;
    }

    public Builder date(LocalDateTime date) {
      this.date = date;
      return this;
    }

    public UpdateRequest build() {
      return new UpdateRequest(this);
    }
  
  }
}

