package cz.cvut.fel.sit.pjv.arimaa.model.board.square;

import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

public final class OccupiedSquare extends Square{
    private final Piece pieceOnSquare;
    OccupiedSquare(final int squareLocation, Piece pieceOnSquare) {
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
