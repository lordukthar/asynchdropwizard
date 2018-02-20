package org.aja.player.modules;

import org.aja.player.PlayerConfiguration;
import org.aja.player.db.PlayerDAO;
import ru.vyarus.dropwizard.guice.module.support.DropwizardAwareModule;

public class PlayerModule extends DropwizardAwareModule<PlayerConfiguration> {

    @Override
    protected void configure() {
        bind(PlayerDAO.class).toInstance(configuration().getJdbiPlayerDao(environment()));
    }
}
