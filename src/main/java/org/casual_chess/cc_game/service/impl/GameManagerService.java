package org.casual_chess.cc_game.service.impl;

import org.casual_chess.cc_game.dto.NewGameRequest;
import org.casual_chess.cc_game.entity.GameEntity;
import org.casual_chess.cc_game.entity.GameStatus;
import org.casual_chess.cc_game.model.GameWithMoves;
import org.casual_chess.cc_game.repository.IGameCacheRepository;
import org.casual_chess.cc_game.repository.IGameRepository;
import org.casual_chess.cc_game.repository.IMoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class GameManagerService {
    @Autowired
    IGameCacheRepository gameCacheRepository;

    @Autowired
    IGameRepository gameRepository;

    @Autowired
    IMoveRepository moveRepository;

    @Autowired
    TransactionalOperator transactionalOperator;

//    @Autowired
//    ChessLogicService chessLogicService;

    // 1. Create a New Game
    public GameWithMoves createGame(NewGameRequest newGameRequest) {
        //* TODO: generate new game id
        UUID TEST_GAME_ID = UUID.randomUUID();

        GameWithMoves game = GameWithMoves.builder()
                .gameId(TEST_GAME_ID)
                .playerWhite(newGameRequest.getWhitePlayerId())
                .playerBlack(newGameRequest.getBlackPlayerId())
                .gameStatus(GameStatus.white_to_move)
                .build();

        //* TODO:
//        //* Save to the database
//        game = gameRepository.save(game);

        //* Save to in-memory cache
        gameCacheRepository.put(game.getGameId(), game);

        return game;
    }
//
//    public Mono<GameWithMoves> updateGame(UUID gameId, GameWithMoves newGameState) {
//        //*TODO: update game in games table
//        // update moves in moves table
//        // if both succeeds then update in-memory cache
//
//        gameCacheRepository.put(gameId, newGameState);
//        return game;
//    }
//
//    public Mono<Game> updateGame(UUID gameId, Game newGameState) {
//        String gameStatus = switch (newGameState.getGameStatus()) {
//            case WHITE_TO_MOVE, BLACK_TO_MOVE -> "ongoing";
//            case WHITE_WON -> "white";
//            case BLACK_WON -> "black";
//            case DRAW -> "draw";
//        };
//        GameEntity newGameEntity = GameEntity.builder()
//                .gameId(gameId)
//                .playerWhite(newGameState.getWhitePlayerId())
//                .playerBlack(newGameState.getBlackPlayerId())
//                .gameStatus(gameStatus)
//                .build();
//        // 1. Save updated game state to the database
//        Mono<GameEntity> updatedGameEntityMono = gameRepository.save(newGameEntity);
//
//        // 2. Save the updated moves (if any)
//        Mono<Void> updatedMovesMono = Mono.empty(); // Assuming you update moves somewhere in the new game state
//        if (newGameState.getMovesPlayed() != null && !newGameState.getMovesPlayed().isEmpty()) {
//            updatedMovesMono = Mono.when(
//                    newGameState.getMovesPlayed().stream()
//                            .map(move -> moveRepository.save(new MoveEntity(gameId, move))) // save each move
//                            .toArray(Mono[]::new)
//            );
//        }
//
//        // 3. Ensure both the game update and moves save are successful before updating cache
//        return Mono.zip(updatedGameEntityMono, updatedMovesMono)
//                .flatMap(tuple -> {
//                    // 4. If both database updates succeed, update the cache
//                    Game updatedGame = newGameState; // Convert to Game model as needed
//                    gameCacheRepository.put(gameId, updatedGame);
//                    return Mono.just(updatedGame); // Return the updated game
//                })
//                .doOnError(error -> {
//                    // Handle the case when the database update fails
//                    log.error("Failed to update game in database", error);
//                });
//    }


}
