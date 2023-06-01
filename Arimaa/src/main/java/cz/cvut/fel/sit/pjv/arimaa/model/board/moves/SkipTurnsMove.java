package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardBuilder;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;

public class SkipTurnsMove extends Move{
    public SkipTurnsMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    @Override
    public String toString() {
        return movedPiece.toString() + " ";
    }

    @Override
    public Board execute() {
        BoardBuilder boardBuilder = new BoardBuilder();
        final Player currentPlayer = this.board.getCurrentPlayer();


        for (final Piece piece : currentPlayer.getActivePieces()){
            boardBuilder.setPiece(piece);
        }
        for (final Piece piece : currentPlayer.getOpponent().getActivePieces()){
            boardBuilder.setPiece(piece);
        }

        setBoardBuilder(boardBuilder, 4);
        return boardBuilder.build();
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
