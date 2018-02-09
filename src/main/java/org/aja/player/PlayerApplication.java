package org.aja.player;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.OptionalContainerFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.aja.player.resource.PlayerResource;
import org.skife.jdbi.v2.DBI;

public class PlayerApplication extends Application<PlayerConfiguration> {
    public static void main(String[] args) throws Exception {
        new PlayerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<PlayerConfiguration> bootstrap) {
        // nothing to do PlayerConfiguration
        bootstrap.addBundle(new MigrationsBundle<PlayerConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(PlayerConfiguration config) {
                return config.database;
            }
        });
    }

    @Override
    public void run(PlayerConfiguration configuration,
                    Environment environment) {

        DBIFactory factory = new DBIFactory();
        DBI jdbi = factory.build(environment, configuration.database, "database");
        jdbi.registerContainerFactory(new OptionalContainerFactory());




        environment.healthChecks().register("helloWorld", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy();
            }
        });

        final PlayerResource resource = new PlayerResource();
        environment.jersey().register(resource);
    }

}
