package cz.cvut.fel.sit.pjv.arimaa.model.square;

import cz.cvut.fel.sit.pjv.arimaa.model.Piece;

public abstract class Square {
    private int squareLocation;

    Square (int squareLocation){
        this.squareLocation = squareLocation;
    }
    public abstract boolean isSquareOccupied();
    public abstract Piece getPieceOnSquare();
}

