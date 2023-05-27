package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import com.google.common.base.Objects;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardBuilder;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    public Move(final Board board, final Piece movedPiece, int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public abstract Board execute();
    public int getDestinationCoordinate() {
        return destinationCoordinate;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }
    public int getCurrentCoordinate() {
        return this.movedPiece.getPiecePosition();
    }
    protected void setBoardBuilder(BoardBuilder boardBuilder, int moveWeight){
        Player currentPlayer = board.getCurrentPlayer();
        boardBuilder.setMoveCount(board.getMoveCount() + moveWeight);

        if (boardBuilder.getMoveCount() == 4){
            boardBuilder.setMoveMaker(currentPlayer.getOpponent().getAlliance());
            boardBuilder.setMoveCount(0);
            return;
        }
        boardBuilder.setMoveMaker(currentPlayer.getAlliance());
    }

}
