package cz.cvut.fel.sit.pjv.arimaa.model.board.square;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

public final class EmptySquare extends Square {

    EmptySquare(final int squareLocation) {super(squareLocation);
    }

    @Override
    public String toString() {
        return "-";
    }

    @Override
    public boolean isSquareOccupied() {
        return false;
    }

    @Override
    public Piece getPieceOnSquare() {
        return null;
    }

    @Override
    public boolean isSupported(Board board) {
        return false;
    }

    @Override
    public void setPieceOnSquare(Piece piece) {}
}
