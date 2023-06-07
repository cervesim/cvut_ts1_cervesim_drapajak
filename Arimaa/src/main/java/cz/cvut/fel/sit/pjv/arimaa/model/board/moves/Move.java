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

    /**
     * Abstract class that is implemented by each move type like push, pull or simple move
     * @param board is used to get current player and board configuration so the pieces that weren't moved could be set as before.
     * @param movedPiece gets the piece that was moved.
     * @param destinationCoordinate the square where the piece is heading to.
     */
    public Move(final Board board, final Piece movedPiece, int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    /**
     * Is used to determine whether the move that has been created using mouse click controller is equal to any move in legal moves array.
     * @param o object
     * @return true or false depending on whether the moves equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move move)) return false;
        return destinationCoordinate == move.destinationCoordinate && Objects.equal(board, move.board) && Objects.equal(movedPiece, move.movedPiece);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(board, movedPiece, destinationCoordinate);
    }

    /**
     * executes the move
     * @return new Board
     */
    public abstract Board execute();
    public int getCurrentCoordinate() {
        return this.movedPiece.getPiecePosition();
    }
    protected String getDirection(){
        return switch (movedPiece.getPiecePosition() - destinationCoordinate) {
            case -1 -> "e";
            case 8 -> "n";
            case -8 -> "s";
            default -> "w";
        };
    }
    protected void setBoardBuilder(BoardBuilder boardBuilder, int moveWeight){
        Player currentPlayer = board.getCurrentPlayer();
        boardBuilder.setMoveCount(board.getMoveCount() + moveWeight);
        boardBuilder.setInitialSetup(board.getInitialSetup());
        boardBuilder.setRoundCounter(board.getRoundCounter());
        boardBuilder.setMovesHistory(board.getMovesHistory());

        if (boardBuilder.getMoveCount() >= 4){
            boardBuilder.setMoveMaker(currentPlayer.getOpponent().getAlliance());
            boardBuilder.setMoveCount(0);

            if (currentPlayer == board.getSilverPlayer()) {
                boardBuilder.setRoundCounter(board.getRoundCounter() + 1);
            }
            return;
        }

        boardBuilder.setMoveMaker(currentPlayer.getAlliance());
    }
    public int getDestinationCoordinate() {
        return destinationCoordinate;
    }
    public Piece getMovedPiece() {
        return this.movedPiece;
    }
    public abstract boolean isDone();

}
