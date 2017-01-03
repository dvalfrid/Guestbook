package net.valfridsson.gastolibro;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@SuppressWarnings("WeakerAccess")
public class GastolibroConfiguration extends Configuration {

    @Valid
    @NotNull
    public DataSourceFactory database = new DataSourceFactory();
}
