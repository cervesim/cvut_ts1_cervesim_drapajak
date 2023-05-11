package cz.cvut.fel.sit.pjv.arimaa.model.board.square;

import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

public final class EmptySquare extends Square {

    EmptySquare(final int squareLocation) {super(squareLocation);
    }

    @Override
    public boolean isSquareOccupied() {
        return false;
    }

    @Override
    public Piece getPieceOnSquare() {
        return null;
    }
}
