package org.aja.people.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

    private final Integer id;
    private final String name;
    private final Integer age;
    private final Boolean male;


    @JsonCreator
    public Player(@JsonProperty("id") Integer id, @JsonProperty("name") String name,
                  @JsonProperty("age") Integer age, @JsonProperty("male") Boolean male) {
        this.id = id;
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

    public Boolean getMale() {
        return male;
    }

    public Integer getId() {
        return id;
    }
}
