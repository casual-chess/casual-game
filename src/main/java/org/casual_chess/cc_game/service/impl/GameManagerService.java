package org.casual_chess.cc_game.service.impl;

import org.casual_chess.cc_game.dto.NewGameRequest;
import org.casual_chess.cc_game.model.Game;
import org.casual_chess.cc_game.model.GameStatus;
import org.casual_chess.cc_game.model.Move;
import org.casual_chess.cc_game.repository.IGameCacheRepository;
import org.casual_chess.cc_game.service.ChessLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameManagerService {
    @Autowired
    IGameCacheRepository gameCacheRepository;

    @Autowired
    ChessLogicService chessLogicService;

    // 1. Create a New Game
    public Game createGame(NewGameRequest newGameRequest) {
        //* TODO: generate new game id
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

    public Game updateGame(String gameId, Move move) {
        Game game = gameCacheRepository.get(gameId);
        //*TODO: throw proper exception
        if (game == null)
            throw new RuntimeException("Game not found");

        if (!chessLogicService.isLegalMove(game, move))
            throw new RuntimeException("Illegal move");

        game = chessLogicService.updateGameState(game, move);

        //* TODO: update in database
        // game = gameRepository.save(game);
        gameCacheRepository.put(gameId, game);
        return game;
    }

}
