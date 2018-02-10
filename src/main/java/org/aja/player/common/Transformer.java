package org.aja.player.common;

import org.aja.player.api.PlayerRequest;
import org.aja.player.db.PlayerEntity;

public class Transformer {

    public static PlayerEntity convertPlayerRequest(Integer id, PlayerRequest request) {
        return new PlayerEntity(id, request.getName(), request.getAge(), request.isMale());
    }

}
