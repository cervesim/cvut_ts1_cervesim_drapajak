package cz.cvut.fel.sit.pjv.arimaa.model.pieces;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Pull;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Push;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SimpleMove;
import cz.cvut.fel.sit.pjv.arimaa.model.board.square.Square;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Piece {
    private final Alliance pieceColor;
    private final PieceType pieceType;
    private final int piecePosition;
    private final int pieceWeight;

    public Piece(final Alliance pieceColor, final PieceType pieceType, final int piecePosition) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceWeight = getWeight();
    }
    @Override
    public String toString() {
        switch (this.pieceType){
            case ELEPHANT:
                return pieceColor.isSilver() ? PieceType.ELEPHANT.toString().toLowerCase() : PieceType.ELEPHANT.toString();
            case CAMEL:
                return pieceColor.isSilver() ? PieceType.CAMEL.toString().toLowerCase() : PieceType.CAMEL.toString();
            case HORSE:
                return pieceColor.isSilver() ? PieceType.HORSE.toString().toLowerCase() : PieceType.HORSE.toString();
            case DOG:
                return pieceColor.isSilver() ? PieceType.DOG.toString().toLowerCase() : PieceType.DOG.toString();
            case CAT:
                return pieceColor.isSilver() ? PieceType.CAT.toString().toLowerCase() : PieceType.CAT.toString();
            default:
                return pieceColor.isSilver() ? PieceType.RABBIT.toString().toLowerCase() : PieceType.RABBIT.toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece piece)) return false;
        return piecePosition == piece.piecePosition && pieceWeight == piece.pieceWeight &&
                pieceColor == piece.pieceColor && pieceType == piece.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, pieceType, piecePosition, pieceWeight);
    }

    public Piece movePiece(Move move){
        switch (move.getMovedPiece().pieceType){
            case ELEPHANT:
                return new Piece(move.getMovedPiece().pieceColor, PieceType.ELEPHANT, move.getDestinationCoordinate());
            case CAMEL:
                return new Piece(move.getMovedPiece().pieceColor, PieceType.CAMEL, move.getDestinationCoordinate());
            case HORSE:
                return new Piece(move.getMovedPiece().pieceColor, PieceType.HORSE, move.getDestinationCoordinate());
            case DOG:
                return new Piece(move.getMovedPiece().pieceColor, PieceType.DOG, move.getDestinationCoordinate());
            case CAT:
                return new Piece(move.getMovedPiece().pieceColor, PieceType.CAT, move.getDestinationCoordinate());
            default:
                return new Piece(move.getMovedPiece().pieceColor, PieceType.RABBIT, move.getDestinationCoordinate());
        }
    }
    /**
     * first the method finds all possible directions it can move in (getPossibleMoveCoordinates), then for each direction it determines according to its position
     * which square the piece could move to. (possibleDestinationCoordinate)
     * if the square is not out of the playing field (isValidSquareCoordinate) and if it is not one of the exceptions (is...ColumnExclusion)
     * then it continues to determine what movement it can make. it finds out what is in the square in the possible destination (possibleDestinationSquare)
     * according to the color of the piece it adds the types of moves to the list of legal moves.
     * @param board
     * @return A list of all possible moves for a piece if there is a piece with different alliance and higher weight around the piece, and
     * it does not have a piece with the same alliance around it then an empty array of legal moves is returned.
     */
    public Collection<Move> getLegalMoves (final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        int possibleDestinationSquareNumber;
        boolean isFrozen = isFrozen(board);
        if (isFrozen){
            return legalMoves;
        }
        int[] possibleMoveCoordinates = getPossibleCoordinates(true);

        for(final int possibleMoveCoordinate : possibleMoveCoordinates){
            possibleDestinationSquareNumber = this.piecePosition + possibleMoveCoordinate;
            if (board.isValidSquareNumber(possibleDestinationSquareNumber) &&
                    !isFirstAndEighthColumnException(this.piecePosition, possibleMoveCoordinate)){

                final Square possibleDestinationSquare = board.getSquare(possibleDestinationSquareNumber);
                if (!possibleDestinationSquare.isSquareOccupied()){
                    legalMoves.add(new SimpleMove(board, this, possibleDestinationSquareNumber));
                } else if (
                        !((board.getMoveCount()) + 2 > 4)){
                    final Piece pieceAtDestination = possibleDestinationSquare.getPieceOnSquare();
                    legalMoves.addAll(calculateLegalPushAndPull(board, pieceAtDestination));
                }
            }
        }
        return legalMoves;
    }

    private Collection<Move> calculateLegalPushAndPull(Board board, Piece pieceAtDestination) {
        final List<Move> legalMoves = new ArrayList<>();
        if (this.pieceColor != pieceAtDestination.getPieceColor() && this.pieceWeight > pieceAtDestination.getPieceWeight()) {
            int[][] coordinates = { pieceAtDestination.getPossibleCoordinates(false), this.getPossibleCoordinates(false) };
            Piece[] pieces = {pieceAtDestination, this };

            for (int i = 0; i < coordinates.length; i++) {
                for (int coordinate : coordinates[i]) {
                    int possibleDestinationSquareNumber = pieces[i].getPiecePosition() + coordinate;
                    if (board.isValidSquareNumber(possibleDestinationSquareNumber) &&
                            !isFirstAndEighthColumnException(pieces[i].getPiecePosition(), coordinate)) {
                        final Square possibleDestinationSquare = board.getSquare(possibleDestinationSquareNumber);
                        if (!possibleDestinationSquare.isSquareOccupied()) {
                            if (pieces[i] == pieceAtDestination) {
                                legalMoves.add(new Push(board, this, pieceAtDestination, possibleDestinationSquareNumber));
                            } else {
                                legalMoves.add(new Pull(board, this, pieceAtDestination, possibleDestinationSquareNumber));
                            }
                        }
                    }
                }
            }
        }
        return legalMoves;
    }

    public boolean isFrozen(Board board) {
        int[] possibleFreezersCoordinates = getPossibleCoordinates(false);
        if (!isSupported(board)){
            for (final int possibleFreezersCoordinate : possibleFreezersCoordinates) {
                int possibleFreezersSquareNumber = this.piecePosition + possibleFreezersCoordinate;
                if (board.isValidSquareNumber(possibleFreezersSquareNumber) &&
                        !isFirstAndEighthColumnException(this.piecePosition, possibleFreezersCoordinate)) {
                    final Square possibleDestinationSquare = board.getSquare(possibleFreezersSquareNumber);
                    if (possibleDestinationSquare.isSquareOccupied()){
                        final Piece pieceAtDestination = possibleDestinationSquare.getPieceOnSquare();
                        if(pieceAtDestination.getPieceWeight() > this.pieceWeight &&
                                pieceAtDestination.getPieceColor() != this.pieceColor){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean isSupported(Board board) {
        int[] possibleSupportersCoordinates = getPossibleCoordinates(false);
        for (final int possibleSupportersCoordinate : possibleSupportersCoordinates) {
            int possibleSupportersSquareNumber = this.piecePosition + possibleSupportersCoordinate;
            if (board.isValidSquareNumber(possibleSupportersSquareNumber) &&
                    !isFirstAndEighthColumnException(this.piecePosition, possibleSupportersCoordinate)) {
                final Square possibleDestinationSquare = board.getSquare(possibleSupportersSquareNumber);
                if (possibleDestinationSquare.isSquareOccupied()) {
                    final Piece pieceAtDestination = possibleDestinationSquare.getPieceOnSquare();
                    if (pieceAtDestination.getPieceColor() == this.pieceColor) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @return the array of directions the piece can move.
     * The rabbit depends on the colour and cannot move backwards, otherwise the directions are the same for all figures
     */
    public int[] getPossibleCoordinates(Boolean isForMove){
         if(isForMove){
            if (this.pieceType == PieceType.RABBIT) {
                if (this.pieceColor == Alliance.GOLDEN){
                    return new int[]{-8, -1, 1};
                } else return new int[]{-1, 1, 8};
            }
         }
         return new int[]{-8, -1, 1, 8};
    }

    public int getWeight(){
        switch (this.pieceType){
            case ELEPHANT:
                return 6;
            case CAMEL:
                return 5;
            case HORSE:
                return 4;
            case DOG:
                return 3;
            case CAT:
                return 2;
            default: /*RABBIT*/
                return 1;
        }
    }
    public PieceType getPieceType() {return this.pieceType;}
    public int getPiecePosition() {return this.piecePosition;}
    public Alliance getPieceColor() {return this.pieceColor;}
    public int getPieceWeight() {
        return pieceWeight;
    }

    /**
     * @param currentPosition
     * @param candidateOffset
     * @return
     */
    protected static boolean isFirstAndEighthColumnException(final int currentPosition, final int candidateOffset){
        return Board.First_Column[currentPosition] && (candidateOffset == -1) || Board.Eighth_Column[currentPosition] && (candidateOffset == 1);
    }

}
