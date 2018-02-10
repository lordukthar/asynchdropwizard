package org.aja.player.db;

import org.aja.player.api.Player;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(PlayerEntityMapper.class)
public interface PlayerDAO {


    @SqlUpdate("insert into PLAYER (PLAYER_ID, PLAYER_NAME, PLAYER_GENDER_MALE, PLAYER_AGE) values " +
            "(:playerId, :playerName, :genderMale, :playerAge)")
    void insert(@BindBean PlayerEntity player);

    @SqlUpdate("update PLAYER set PLAYER_NAME=:playerName, PLAYER_GENDER_MALE = :genderMale, PLAYER_AGE = :playerAge " +
            "WHERE PLAYER_ID = :playerId")
    void update(@BindBean PlayerEntity player);

    @SqlUpdate("delete from PLAYER where PLAYER_ID = :playerId")
    void delete(@Bind("playerId") int id);

    @SqlQuery("select DISTINCT * from PLAYER where PLAYER_ID = :playerId")
    <Optional>PlayerEntity findById(@Bind("playerId") int id);


    @SqlQuery("select * from PLAYER where PLAYER_NAME = :playerName")
    <List>PlayerEntity findByName(@Bind("playerName") String playerName);

    @SqlQuery("select player_id_sequence.nextval from dual")
    Integer getNextPlayerId();

}

//org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException: Unable to execute, no named parameter matches "playerId" and no positional param for place 0 (which is 1 in the JDBC 'start at 1' scheme) has been set. [statement:"insert into PLAYER (PLAYER_ID, PLAYER_NAME, PLAYER_GENDER_MALE, PLAYER_AGE) values (:playerId, :playerName, :genderMale, :playerAge)", located:"insert into PLAYER (PLAYER_ID, PLAYER_NAME, PLAYER_GENDER_MALE, PLAYER_AGE) values (:playerId, :playerName, :genderMale, :playerAge)", rewritten:"/* PlayerDAO.insert */ insert into PLAYER (PLAYER_ID, PLAYER_NAME, PLAYER_GENDER_MALE, PLAYER_AGE) values (?, ?, ?, ?)", arguments:{ positional:{}, named:{class:class org.aja.player.db.PlayerEntity}, finder:[]}]