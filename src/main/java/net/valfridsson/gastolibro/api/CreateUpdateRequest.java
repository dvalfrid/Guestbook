package net.valfridsson.gastolibro.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateUpdateRequest {
  @NotEmpty(message= "'id' must not be empty")
  public final long id;
  @NotEmpty(message ="'x' must not be null")
  public final int x;
  @NotEmpty(message ="'date' must not be empty")
  public final LocalDateTime date;

  @JsonCreator
  public CreateUpdateRequest(@JsonProperty("id") long id,
                     @JsonProperty("x") int x,
                     @JsonProperty("date") LocalDateTime date) {
    this.id = id;
    this.x = x;
    this.date = date;

  }
}

