package org.aja.player.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerResponse extends Player {

    private final Integer id;

    @JsonCreator
    public PlayerResponse(@JsonProperty("id") Integer id, @JsonProperty("name") String name,
                          @JsonProperty("age") Integer age, @JsonProperty("male") Boolean male) {
        super(name, age, male);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
