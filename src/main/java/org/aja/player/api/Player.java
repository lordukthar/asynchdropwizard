package org.aja.player.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

    private final String name;
    private final Integer age;
    private final Boolean male;


    @JsonCreator
    public Player(@JsonProperty("name") String name,
                  @JsonProperty("age") Integer age, @JsonProperty("male") Boolean male) {
        this.name = name;
        this.age = age;
        this.male = male;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean isMale() {
        return male;
    }
}

