package org.aja.player;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.jdbi.OptionalContainerFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.aja.player.auth.User;
import org.aja.player.auth.UserAuthenticator;
import org.aja.player.auth.UserAuthorizer;
import org.aja.player.db.PlayerDAO;
import org.aja.player.modules.PlayerModule;
import org.aja.player.resource.PlayerResource;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class PlayerApplication extends Application<PlayerConfiguration> {
    public static void main(String[] args) throws Exception {
        new PlayerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<PlayerConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<PlayerConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(PlayerConfiguration config) {
                return config.database;
            }
        });

        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        addGuiceBundle(bootstrap);

    }

    @Override
    public void run(PlayerConfiguration configuration,
                    Environment environment) {

        /*DBIFactory factory = new DBIFactory();
        DBI jdbi = factory.build(environment, configuration.database, "database");
        jdbi.registerContainerFactory(new OptionalContainerFactory());
        PlayerDAO playerDAO = jdbi.onDemand(PlayerDAO.class);*/

        environment.healthChecks().register("helloWorld", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy();
            }
        });

        this.addSecurity(environment);

        //final PlayerResource resource = new PlayerResource(playerDAO);
        //environment.jersey().register(resource);
    }

    private void addSecurity(Environment environment) {
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new UserAuthenticator())
                .setAuthorizer(new UserAuthorizer())
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

    }

    private void addGuiceBundle(final Bootstrap<PlayerConfiguration> bootstrap) {
        final GuiceBundle<Configuration> guiceBundle = GuiceBundle.builder()
                .useWebInstallers()
                .modules(new PlayerModule())
                .enableAutoConfig(getClass().getPackage().getName() + ".resource")
                .build();
        bootstrap.addBundle(guiceBundle);
    }

}
