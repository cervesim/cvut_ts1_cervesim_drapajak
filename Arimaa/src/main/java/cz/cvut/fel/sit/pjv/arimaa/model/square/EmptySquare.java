package cz.cvut.fel.sit.pjv.arimaa.model.square;

import cz.cvut.fel.sit.pjv.arimaa.model.Piece;

public class EmptySquare extends Square {
    private int squareLocation;

    EmptySquare(int squareLocation) {
        super(squareLocation);
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
