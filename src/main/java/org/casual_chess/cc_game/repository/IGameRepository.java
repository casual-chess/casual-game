package org.casual_chess.cc_game.repository;

import org.casual_chess.cc_game.entity.GameEntity;
import org.casual_chess.cc_game.model.GameStatus;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface IGameRepository extends ReactiveCrudRepository<GameEntity, UUID> {
    @Query("SELECT * FROM games WHERE game_status = 'ongoing'")
    Flux<GameEntity> findOngoingGames();
}
