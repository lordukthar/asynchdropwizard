package org.aja.player.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerRequest extends Player {
    @JsonCreator
    public PlayerRequest(@JsonProperty("name") String name,
                         @JsonProperty("age") Integer age, @JsonProperty("male") Boolean male) {
        super(name, age, male);
    }
}
