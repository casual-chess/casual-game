package org.casual_chess.cc_game.repository;

import org.casual_chess.cc_game.entity.MoveEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Repository
public interface IMoveRepository extends ReactiveCrudRepository<MoveEntity, UUID> {

    @Query("SELECT * FROM moves WHERE game_id = $1 ORDER BY move_number ASC")
    Flux<MoveEntity> findByGameIdOrderByMoveNumber(UUID gameId);
}