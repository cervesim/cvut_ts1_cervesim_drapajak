package cz.cvut.fel.sit.pjv.arimaa.model.board.square;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.SquareLocationToString;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

public class TrapSquare extends Square{
    Piece pieceOnSquare;
    TrapSquare(int squareLocation, Piece pieceOnSquare) {
        super(squareLocation);
        this.pieceOnSquare = pieceOnSquare;
    }

    @Override
    public String toString() {
        if (this.pieceOnSquare != null) {
            return (getPieceOnSquare().getPieceColor().isSilver() ? getPieceOnSquare().toString().toLowerCase() : getPieceOnSquare().toString())
                    + SquareLocationToString.fromSquareNumber(squareLocation) + "x ";
        }else return "";
    }

    @Override
    public boolean isSquareOccupied() {
        return pieceOnSquare != null;
    }

    @Override
    public Piece getPieceOnSquare() {
        return pieceOnSquare;
    }
    @Override
    public boolean isSupported(Board board){
        int[] possibleSupportersCoordinates = new int[]{-8, -1, 1, 8};
        for (final int possibleSupportersCoordinate : possibleSupportersCoordinates) {
            int possibleSupportersSquareNumber = squareLocation + possibleSupportersCoordinate;
            final Square possibleSupportingSquare = board.getSquare(possibleSupportersSquareNumber);
            if (possibleSupportingSquare.isSquareOccupied()) {
                final Alliance pieceAtDestinationColor = possibleSupportingSquare.getPieceOnSquare().getPieceColor();
                if (this.isSquareOccupied() && pieceAtDestinationColor == pieceOnSquare.getPieceColor()) {
                    return true;
                }
            }
        }
        if (!Board.pushOrPullNoted) System.out.print(this);
        return false;
    }

    @Override
    public void setPieceOnSquare(Piece piece) {
        this.pieceOnSquare = piece;
    }
}
