package org.casual_chess.cc_game.repository;

import org.casual_chess.cc_game.entity.GameEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface IGameRepository extends ReactiveCrudRepository<GameEntity, UUID> {
    @Query("SELECT * FROM games WHERE game_status = 'ongoing'")
    Flux<GameEntity> findOngoingGames();
}
