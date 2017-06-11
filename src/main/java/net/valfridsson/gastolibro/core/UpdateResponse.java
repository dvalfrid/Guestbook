package net.valfridsson.gastolibro.core;

import com.fasterxml.jackson.annotation.JsonProperty;


import com.google.common.collect.ImmutableList;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import net.valfridsson.gastolibro.core.Entry;

@SuppressWarnings("WeakerAccess")
public class UpdateResponse {
  @JsonProperty("entries")
  public final ImmutableList<Entry> entries;


  private UpdateResponse(Builder builder) {
    this.entries = builder.entries;
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
    ImmutableList<Entry> entries;

    public Builder entries(ImmutableList<Entry> entries) {
      this.entries = entries;
      return this;
    }

    public UpdateResponse build() {
      return new UpdateResponse(this);
    }
  
  }
}

