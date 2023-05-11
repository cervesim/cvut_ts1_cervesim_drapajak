package cz.cvut.fel.sit.pjv.arimaa.model.pieces;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
public class Rabbit extends Piece /* TODO maybe delete this and have just Piece class*/{
    private static int[] Possible_Move_Coordinates; /* TODO coordinates */
    public Rabbit(int piecePosition, Alliance pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public int[] getPossibleMoveCoordinates() {
        if (this.pieceColor == Alliance.GOLDEN){
            return new int[]{-8, -1, 1};
        } else return new int[]{-1, 1, 8};
    }


}
