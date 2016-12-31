package net.valfridsson.guestbook;

import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.valfridsson.guestbook.health.SimpleHealthCheck;
import net.valfridsson.guestbook.resources.HelloWorldResource;
import org.skife.jdbi.v2.DBI;

public class GuestBookApplication extends Application<GuestBookConfiguration> {

    private DBI jdbi;

    public static void main(String... args) throws Exception {
        new GuestBookApplication().run(args);
    }

    @Override
    public String getName() {
        return "guest-book";
    }

    @Override
    public void initialize(Bootstrap<GuestBookConfiguration> bootstrap) {
        bootstrap.addBundle(new DBIExceptionsBundle());
        bootstrap.addBundle(new MigrationsBundle<GuestBookConfiguration>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(GuestBookConfiguration configuration) {
                return configuration.database;
            }
        });
    }

    @Override
    public void run(GuestBookConfiguration configuration, Environment environment) {
        jdbi = new DBIFactory().build(environment, configuration.database, getName());
        environment.healthChecks().register("simple", new SimpleHealthCheck());
        environment.jersey().register(new HelloWorldResource());
    }

    public DBI getDbi() {
        return jdbi;
    }
}
