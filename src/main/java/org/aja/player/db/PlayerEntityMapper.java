package org.aja.player.db;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerEntityMapper implements ResultSetMapper<PlayerEntity> {
    @Override
    public PlayerEntity map(int i, ResultSet r, StatementContext statementContext) throws SQLException {
        return new PlayerEntity(r.getInt("PLAYER_ID"),
                r.getString("PLAYER_NAME"),
                r.getInt("PLAYER_AGE"),
                r.getBoolean("PLAYER_GENDER_MALE"));
    }
}