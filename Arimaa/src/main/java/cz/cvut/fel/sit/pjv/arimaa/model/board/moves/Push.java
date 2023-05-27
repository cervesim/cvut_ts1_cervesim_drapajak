package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import com.google.common.base.Objects;
import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardBuilder;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;

/**
 *
 */
public class Push extends Move{
    Piece pushedPiece;
    public Push(Board board, Piece movedPiece, Piece pushedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
        this.pushedPiece = pushedPiece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Push push)) return false;
        if (!super.equals(o)) return false;
        return Objects.equal(pushedPiece, push.pushedPiece);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), pushedPiece);
    }

    @Override
    public Board execute() {
        BoardBuilder boardBuilder = new BoardBuilder();
        Player currentPlayer = board.getCurrentPlayer();

        Move movedPieceMove = new SimpleMove(board, movedPiece, pushedPiece.getPiecePosition());
        for (Piece piece : currentPlayer.getActivePieces()) {
            if (movedPiece.equals(piece)) {
                boardBuilder.setPiece(movedPiece.movePiece(movedPieceMove));
            } else {
                boardBuilder.setPiece(piece);
            }
        }

        Move pushedPieceMove = new SimpleMove(board, pushedPiece, destinationCoordinate);
        for (Piece piece : currentPlayer.getOpponent().getActivePieces()) {
            if (pushedPiece.equals(piece)) {
                boardBuilder.setPiece(pushedPiece.movePiece(pushedPieceMove));
            } else {
                boardBuilder.setPiece(piece);
            }
        }

        setBoardBuilder(boardBuilder, 2);
        return boardBuilder.build();
    }
}
