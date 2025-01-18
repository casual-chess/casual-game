package org.casual_chess.cc_game.repository;

import org.casual_chess.cc_game.entity.GameEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IGameRepository extends ReactiveCrudRepository<GameEntity, UUID> {

}
