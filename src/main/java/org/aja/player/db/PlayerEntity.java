package org.aja.player.db;

import org.aja.player.api.PlayerResponse;

public class PlayerEntity {

    private final String playerId;
    private final String playerName;
    private final Integer playerAge;
    private final Boolean genderMale;

    public PlayerEntity(String playerId, String playerName,
                        Integer playerAge, Boolean genderMale) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerAge = playerAge;
        this.genderMale = genderMale;
    }

    public PlayerResponse buildPlayerResponse() {
        return new PlayerResponse(this.playerId, this.playerName, this.playerAge, this.genderMale);
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Integer getPlayerAge() {
        return playerAge;
    }

    public Boolean getGenderMale() {
        return genderMale;
    }
}
