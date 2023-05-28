package cz.cvut.fel.sit.pjv.arimaa.model.board.square;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

public final class OccupiedSquare extends Square{
    private final Piece pieceOnSquare;
    OccupiedSquare(final int squareLocation, Piece pieceOnSquare) {
        super(squareLocation);
        this.pieceOnSquare = pieceOnSquare;
    }

    @Override
    public String toString() {
        return getPieceOnSquare().getPieceColor().isSilver() ? getPieceOnSquare().toString().toLowerCase() : getPieceOnSquare().toString();
    }

    @Override
    public boolean isSquareOccupied() {
        return true;
    }

    @Override
    public Piece getPieceOnSquare() {
        return this.pieceOnSquare;
    }

    @Override
    public boolean isSupported(Board board) {
        return false;
    }

    @Override
    public void setPieceOnSquare(Piece piece) {}
}
