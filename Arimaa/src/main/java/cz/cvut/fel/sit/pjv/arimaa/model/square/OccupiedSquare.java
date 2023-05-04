package cz.cvut.fel.sit.pjv.arimaa.model.square;

import cz.cvut.fel.sit.pjv.arimaa.model.Piece;

public class OccupiedSquare extends Square{
    private Piece pieceOnSquare;
    public OccupiedSquare(int squareLocation, Piece pieceOnSquare) {
        super(squareLocation);
        this.pieceOnSquare = pieceOnSquare;
    }

    @Override
    public boolean isSquareOccupied() {
        return true;
    }

    @Override
    public Piece getPieceOnSquare() {
        return this.pieceOnSquare;
    }
}
