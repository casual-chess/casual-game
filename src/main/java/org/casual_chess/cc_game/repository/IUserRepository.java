package org.casual_chess.cc_game.repository;

import org.casual_chess.cc_game.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IUserRepository extends ReactiveCrudRepository<UserEntity, Long> {

    Mono<UserEntity> findByUsername(String username);
}
