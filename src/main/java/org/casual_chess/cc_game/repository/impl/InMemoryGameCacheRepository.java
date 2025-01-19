package org.casual_chess.cc_game.repository.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.casual_chess.cc_game.entity.GameEntity;
import org.casual_chess.cc_game.entity.MoveEntity;
import org.casual_chess.cc_game.model.Game;
import org.casual_chess.cc_game.model.GameStatus;
import org.casual_chess.cc_game.model.Move;
import org.casual_chess.cc_game.repository.IGameCacheRepository;
import org.casual_chess.cc_game.repository.IGameRepository;
import org.casual_chess.cc_game.repository.IMoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
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

    private final Map<UUID, Game> gameMap = new ConcurrentHashMap<>();


    @PostConstruct
    public void loadOngoingGameStatesFromDatabase() {
        log.info("Loading ongoing game states from database");

        gameRepository.findOngoingGames()
                .flatMap(gameEntity ->
                        moveRepository.findByGameIdOrderByMoveNumber(gameEntity.getGameId())
                            .collectList()
                            .map(moves -> {
                                Game game = convertToGame(gameEntity, moves);
                                log.info("Loading game into cache: {}", game.getGameId());
                                gameMap.put(game.getGameId(), game);
                                return game;
                            }))
                .doOnComplete(() -> log.info("Cache initialized with ongoing games."))
                .doOnError(error -> log.error("Failed to initialize cache", error))
                .blockLast(); // Block until the last element is processed

        log.info("Cache initialized with ongoing games.");
    }

    @Override
    public void put(UUID gameId, Game game) {
        gameMap.put(gameId, game);

        //TODO: we should add a TTL to the cache
        // so that at some point the game is removed from cache
    }

    @Override
    public void remove(UUID gameId) {
        gameMap.remove(gameId);
    }

    @Override
    public Game get(UUID gameId) {
        return gameMap.get(gameId);
    }

    private Game convertToGame(GameEntity entity, List<MoveEntity> moveEntityList) {
        return Game.builder()
                .gameId(entity.getGameId())
                .whitePlayerId(String.valueOf(entity.getPlayerWhite()))
                .blackPlayerId(String.valueOf(entity.getPlayerBlack()))
                .gameStatus(moveEntityList.size() % 2 == 0 ? GameStatus.WHITE_TO_MOVE : GameStatus.BLACK_TO_MOVE)
                .movesPlayed(moveEntityList.stream()
                        .map(moveEntity -> Move.builder()
                                .moveNotation(moveEntity.getMoveNotation())
                                .player(moveEntity.getPlayer())
                                .build())
                        .toList()
                )
                .build();
    }

}
