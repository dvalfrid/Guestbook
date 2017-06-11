package net.valfridsson.gastolibro.api;

import net.valfridsson.gastolibro.core.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jersey.repackaged.com.google.common.collect.ImmutableList;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateUpdateResponse {
  @NotEmpty(message ="'entries' must not be empty")
  public final ImmutableList<Entry> entries;

  @JsonCreator
  public CreateUpdateResponse(@JsonProperty("entries") ImmutableList<Entry> entries) {
    this.entries = entries;
  }
}
