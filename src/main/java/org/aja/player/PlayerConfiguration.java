package org.aja.player;


import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.aja.player.db.PlayerDAO;
import org.skife.jdbi.v2.DBI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PlayerConfiguration extends Configuration {

    @Valid
    @NotNull
    public DataSourceFactory database = new DataSourceFactory();

    public PlayerDAO getJdbiPlayerDao(Environment environment) {
        DBIFactory factory = new DBIFactory();
        DBI jdbi = factory.build(environment, this.database, "database");
        return jdbi.onDemand(PlayerDAO.class);
    }
}
