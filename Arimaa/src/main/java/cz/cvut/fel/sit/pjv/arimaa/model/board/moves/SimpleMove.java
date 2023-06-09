package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import com.google.common.base.Objects;
import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardBuilder;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.SquareLocationToString;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;

public class SimpleMove extends Move {
    public SimpleMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    @Override
    public String toString() {
        return movedPiece.toString()+SquareLocationToString.fromSquareNumber(movedPiece.getPiecePosition()) + getDirection() + " ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleMove simpleMove)) return false;
        return destinationCoordinate == simpleMove.destinationCoordinate && Objects.equal(board, simpleMove.board) && Objects.equal(movedPiece, simpleMove.movedPiece);
    }

    @Override
    public Board execute() {
        BoardBuilder boardBuilder = new BoardBuilder();
        final Player currentPlayer = this.board.getCurrentPlayer();

        for (final Piece piece : currentPlayer.getActivePieces()){
            if (movedPiece.equals(piece)){
                boardBuilder.setPiece(movedPiece.movePiece(this));
            }else {
                boardBuilder.setPiece(piece);
            }
        }
        for (final Piece piece : currentPlayer.getOpponent().getActivePieces()){
            boardBuilder.setPiece(piece);
        }
        boardBuilder.setNewMovesHistory(board.getMovesHistory(), this.toString());
        setBoardBuilder(boardBuilder, 1);

        return boardBuilder.build();
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
