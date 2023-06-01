package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardBuilder;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;

public class checkMove extends Move{
    public checkMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    @Override
    public Board execute() {
        BoardBuilder boardBuilder = new BoardBuilder();

        final Collection<Piece> allPieces = new ArrayList<>();
        allPieces.addAll(this.board.getGoldenPlayer().getActivePieces());
        allPieces.addAll(this.board.getSilverPlayer().getActivePieces());

        for (final Piece piece : allPieces) {
            if (!piece.equals(movedPiece)) boardBuilder.setPiece(piece);
            else boardBuilder.setPiece(movedPiece.movePiece(this));
        }

        boardBuilder.setMoveMaker(board.getCurrentPlayer().getAlliance());
        return boardBuilder.build();
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
