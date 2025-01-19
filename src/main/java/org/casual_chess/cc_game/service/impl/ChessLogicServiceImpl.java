package org.casual_chess.cc_game.service.impl;

import com.github.bhlangonijr.chesslib.Board;
import lombok.extern.slf4j.Slf4j;
import org.casual_chess.cc_game.model.Game;
import org.casual_chess.cc_game.model.GameStatus;
import org.casual_chess.cc_game.model.Move;
import org.casual_chess.cc_game.service.IChessLogicService;
import com.github.bhlangonijr.chesslib.*;
import org.springframework.stereotype.Service;


//* TODO: add rigorous unit tests for this class
@Slf4j
@Service
public class ChessLogicServiceImpl implements IChessLogicService {

    @Override
    public boolean isLegalMove(Game game, Move move) {
        return getBoardAfterPlayingMoveIfMoveIsLegal(game, move) != null;
    }

    @Override
    public Game updateGameState(Game game, Move move) {
        Board board = getBoardAfterPlayingMoveIfMoveIsLegal(game, move);
        if (board == null) {
            return null;
        }

        Game updatedGameState = new Game(game);
        updatedGameState.addMove(move);
        updatedGameState.setGameStatus(getGameStatus(board));

        return updatedGameState;
    }

    private com.github.bhlangonijr.chesslib.move.Move convertToChesslibMove(Move gameMove) {
        // Assuming your Move class stores the move notation in SAN format (e.g., "e2e4")
        //* TODO: take care of promotion
        return new com.github.bhlangonijr.chesslib.move.Move(gameMove.getMoveNotation(), Side.valueOf(gameMove.getPlayer().toUpperCase()));
    }

    private GameStatus getGameStatus(Board board) {
        if (board.isMated()) {
            return board.getSideToMove() == Side.WHITE ? GameStatus.BLACK_WON : GameStatus.WHITE_WON;
        } else if (board.isDraw() || board.isStaleMate()) {
            return GameStatus.DRAW;
        } else {
            return board.getSideToMove() == Side.WHITE ? GameStatus.WHITE_TO_MOVE : GameStatus.BLACK_TO_MOVE;
        }
    }

    private Board getBoard(Game game) {
        Board board = new Board();
        for (Move playedMove : game.getMovesPlayed()) {
            com.github.bhlangonijr.chesslib.move.Move chesslibMove = convertToChesslibMove(playedMove);
            board.doMove(chesslibMove, true);
        }
        return board;
    }

    /*
    * Returns board object after playing the given move on the given game state, if the move is legal
    * Otherwise, returns null
    * */
    private Board getBoardAfterPlayingMoveIfMoveIsLegal(Game game, Move move) {
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