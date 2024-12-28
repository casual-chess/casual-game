package org.casual_chess.cc_game.service.impl;

import org.casual_chess.cc_game.dto.NewGameRequest;
import org.casual_chess.cc_game.model.Game;
import org.casual_chess.cc_game.model.GameStatus;
import org.casual_chess.cc_game.service.GameCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameManagerService {
    @Autowired
    GameCacheRepository gameCacheRepository;

    // 1. Create a New Game
    public Game createGame(NewGameRequest newGameRequest) {
        //* TODO:
        String TEST_GAME_ID = "testGameId";

        Game game = Game.builder()
                .gameId(TEST_GAME_ID)
                .whitePlayerId(newGameRequest.getWhitePlayerId())
                .blackPlayerId(newGameRequest.getBlackPlayerId())
                .gameStatus(GameStatus.WHITE_TO_MOVE)
                .build();

        //* TODO:
//        //* Save to the database
//        game = gameRepository.save(game);

        //* Save to in-memory cache
        gameCacheRepository.put(game.getGameId(), game);

        return game;
    }

}
