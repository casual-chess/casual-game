package org.casual_chess.cc_game.service.impl;

import com.github.bhlangonijr.chesslib.Board;
import lombok.extern.slf4j.Slf4j;
import org.casual_chess.cc_game.entity.GameStatus;
import org.casual_chess.cc_game.entity.MoveEntity;
import org.casual_chess.cc_game.model.GameWithMoves;
import org.casual_chess.cc_game.service.IChessLogicService;
import com.github.bhlangonijr.chesslib.*;
import org.springframework.stereotype.Service;


//* TODO: add rigorous unit tests for this class
@Slf4j
@Service
public class ChessLogicServiceImpl implements IChessLogicService {

    @Override
    public boolean isLegalMove(GameWithMoves game, MoveEntity move) {
        return getBoardAfterPlayingMoveIfMoveIsLegal(game, move) != null;
    }

    @Override
    public GameWithMoves updateGameState(GameWithMoves game, MoveEntity move) {
        Board board = getBoardAfterPlayingMoveIfMoveIsLegal(game, move);
        if (board == null) {
            return null;
        }

        GameWithMoves updatedGameState = new GameWithMoves(game);
        updatedGameState.addMove(move);
        updatedGameState.setGameStatus(getGameStatus(board));

        return updatedGameState;
    }

    private com.github.bhlangonijr.chesslib.move.Move convertToChesslibMove(MoveEntity gameMove) {
        // Assuming your Move class stores the move notation in SAN format (e.g., "e2e4")
        //* TODO: take care of promotion
        return new com.github.bhlangonijr.chesslib.move.Move(gameMove.getMoveNotation(), Side.valueOf(gameMove.getPlayer().toString().toUpperCase()));
    }

    private GameStatus getGameStatus(Board board) {
        if (board.isMated()) {
            return board.getSideToMove() == Side.WHITE ? GameStatus.black : GameStatus.white;
        } else if (board.isDraw() || board.isStaleMate()) {
            return GameStatus.draw;
        } else {
            return board.getSideToMove() == Side.WHITE ? GameStatus.white_to_move : GameStatus.black_to_move;
        }
    }

    private Board getBoard(GameWithMoves game) {
        Board board = new Board();
        for (MoveEntity playedMove : game.getMovesPlayed()) {
            com.github.bhlangonijr.chesslib.move.Move chesslibMove = convertToChesslibMove(playedMove);
            board.doMove(chesslibMove, true);
        }
        return board;
    }

    /*
    * Returns board object after playing the given move on the given game state, if the move is legal
    * Otherwise, returns null
    * */
    private Board getBoardAfterPlayingMoveIfMoveIsLegal(GameWithMoves game, MoveEntity move) {
        Board board = getBoard(game);
        com.github.bhlangonijr.chesslib.move.Move chesslibMove = convertToChesslibMove(move);
        boolean isLegalMove = board.legalMoves().contains(chesslibMove);
        try {
            if (!board.doMove(chesslibMove, true)) {
                return null;
            }
        } catch (Exception e) {
            log.error("Error playing move. game: {}, move: {}, errorMessage: {}", game, move, e.getMessage());
            return null;
        }

        return isLegalMove ? board : null;
    }
}