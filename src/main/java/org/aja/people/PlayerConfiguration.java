package org.aja.people;


import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PlayerConfiguration extends Configuration {

    @Valid
    @NotNull
    public DataSourceFactory database = new DataSourceFactory();



}
