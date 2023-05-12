package cz.cvut.fel.sit.pjv.arimaa.model.board.square;

import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

public class TrapSquare extends Square /*TODO */{
    private final int[] PossibleSupportingSquares = {-8, -1, 1, 8};
    private final Piece pieceOnSquare;
    TrapSquare(int squareLocation, Piece pieceOnSquare) {
        super(squareLocation);
        this.pieceOnSquare = pieceOnSquare;
    }
//    TODO
//    public boolean isSupported(){
//        for (possibleSupportingSquare : PossibleSupportingSquares) {
//            if (true){
//                return true;
//            }else {
//                return false;
//            }
//        }
//    }
    @Override
    public boolean isSquareOccupied() {
        return true;
    }

    @Override
    public Piece getPieceOnSquare() {
        return this.pieceOnSquare;
    }
}
