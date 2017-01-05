package net.valfridsson.gastolibro;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.valfridsson.gastolibro.health.SimpleHealthCheck;
import net.valfridsson.gastolibro.resources.BookResource;
import net.valfridsson.gastolibro.resources.HelloWorldResource;
import org.skife.jdbi.v2.DBI;

public class GastolibroApplication extends Application<GastolibroConfiguration> {

    private DBI jdbi;

    public static void main(String... args) throws Exception {
        new GastolibroApplication().run(args);
    }

    public String getName() {
        return "gastolibro";
    }

    @Override
    public void initialize(Bootstrap<GastolibroConfiguration> bootstrap) {
        bootstrap.addBundle(new DBIExceptionsBundle());
        bootstrap.addBundle(new MigrationsBundle<GastolibroConfiguration>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(GastolibroConfiguration configuration) {
                return configuration.getDatabase();
            }
        });
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }

    @Override
    public void run(GastolibroConfiguration configuration, Environment environment) {
        DataSourceFactory factory = configuration.getDatabase();
        jdbi = new DBIFactory().build(environment, factory, factory.getUrl());
        environment.healthChecks().register("gastolibro", new SimpleHealthCheck());
        environment.jersey().register(new HelloWorldResource());
        environment.jersey().register(new BookResource(this));
    }
ª
    public DBI getDbi() {
        return jdbi;
    }
}
