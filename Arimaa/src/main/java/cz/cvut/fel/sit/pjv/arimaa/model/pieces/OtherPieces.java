package cz.cvut.fel.sit.pjv.arimaa.model.pieces;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;

public class OtherPieces extends Piece /* TODO maybe delete this and have just Piece class*/{
    /**
     * @param piecePosition
     * @param pieceColor
     */
    public OtherPieces(int piecePosition, Alliance pieceColor) {
        super(piecePosition, pieceColor);
    }
    @Override
    public int[] getPossibleMoveCoordinates() {
        return new int[]{-8, -1, 1, 8};
    }

    @Override
    public String toString() {
        return PieceType.ELEPHANT.toString();
    }

}
