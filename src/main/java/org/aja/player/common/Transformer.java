package org.aja.player.common;

import org.aja.player.api.PlayerRequest;
import org.aja.player.api.PlayerResponse;
import org.aja.player.db.PlayerEntity;

public class Transformer {

    public static PlayerEntity convertPlayerRequest(String playerId, PlayerRequest request) {
        return new PlayerEntity(playerId, request.getName(), request.getAge(), request.isMale());
    }

    public static PlayerResponse convertPlayerEntity(PlayerEntity player) {
        return new PlayerResponse(player.getPlayerId(), player.getPlayerName(),
                player.getPlayerAge(), player.getGenderMale());
    }
}
