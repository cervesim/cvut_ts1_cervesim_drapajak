package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;


import java.util.Objects;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    public Move(final Board board, final Piece movedPiece, int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move move)) return false;
        return destinationCoordinate == move.destinationCoordinate && Objects.equals(board, move.board) && Objects.equals(movedPiece, move.movedPiece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, movedPiece, destinationCoordinate);
    }

    public abstract Board execute();
    public int getDestinationCoordinate() {
        return destinationCoordinate;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }
    public int getCurrentCoordiante() {
        return this.movedPiece.getPiecePosition();
    }
}
