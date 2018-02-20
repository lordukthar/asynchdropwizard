package org.aja.player.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerResponse extends Player {

    private final String playerId;

    @JsonCreator
    public PlayerResponse(@JsonProperty("playerId") String playerId, @JsonProperty("name") String name,
                          @JsonProperty("age") Integer age, @JsonProperty("male") Boolean male) {
        super(name, age, male);
        this.playerId = playerId;
    }


    public String getPlayerId() {
        return playerId;
    }
}
