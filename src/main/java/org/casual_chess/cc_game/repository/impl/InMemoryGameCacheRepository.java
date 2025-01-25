package org.casual_chess.cc_game.repository.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.casual_chess.cc_game.model.GameWithMoves;
import org.casual_chess.cc_game.repository.IGameCacheRepository;
import org.casual_chess.cc_game.repository.IGameRepository;
import org.casual_chess.cc_game.repository.IMoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class InMemoryGameCacheRepository implements IGameCacheRepository {

    @Autowired
    IGameRepository gameRepository;

    @Autowired
    IMoveRepository moveRepository;

    private final Map<UUID, GameWithMoves> gameMap = new ConcurrentHashMap<>();


    @PostConstruct
    public void loadOngoingGameStatesFromDatabase() {
        log.info("Loading ongoing game states from database");

        gameMap.clear();
        gameRepository.findOngoingGames()
                .flatMap(gameEntity ->
                        moveRepository.findByGameIdOrderByMoveNumber(gameEntity.getGameId())
                            .collectList()
                            .map(moves -> {
                                GameWithMoves game = new GameWithMoves(gameEntity, moves);
                                log.info("Loading game into cache: {}", game.getGameId());
                                gameMap.put(game.getGameId(), game);
                                return game;
                            }))
                .doOnError(error -> log.error("Failed to initialize cache", error))
                .blockLast(); //* Block until the last element is processed

        log.info("Cache initialized with ongoing games.");
    }

    @Override
    public void put(UUID gameId, GameWithMoves game) {
        gameMap.put(gameId, game);

        //TODO: we should add a TTL to the cache
        // so that at some point the game is removed from cache
    }

    @Override
    public void remove(UUID gameId) {
        gameMap.remove(gameId);
    }

    @Override
    public GameWithMoves get(UUID gameId) {
        return gameMap.get(gameId);
    }

}
