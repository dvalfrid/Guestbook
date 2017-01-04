package net.valfridsson.gastolibro;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;

@SuppressWarnings("WeakerAccess")
public class GastolibroConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();


    public DataSourceFactory getDatabase() {
        String database_url = System.getenv("DATABASE_URL");
        if(database_url != null) {
            try {
                URI dbUri = new URI(database_url);
                String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];
                String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
                database.setUrl(dbUrl);
                database.setUser(username);
                database.setPassword(password);
            } catch (URISyntaxException e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        }
        return database;
    }
}
