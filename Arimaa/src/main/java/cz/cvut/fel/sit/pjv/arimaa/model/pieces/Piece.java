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
                return PieceType.ELEPHANT.toString();
            case CAMEL:
                return PieceType.CAMEL.toString();
            case HORSE:
                return PieceType.HORSE.toString();
            case DOG:
                return PieceType.DOG.toString();
            case CAT:
                return PieceType.CAT.toString();
            default:
                return PieceType.RABBIT.toString();
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
     * nejdříve metoda zjistí všechny možný směry jakými se může pohybovat (getPossibleMoveCoordinates),
     * poté pro každý směr určí podle své polohy na jake políčko by se moha figurka pohnout. (possibleDestinationCoordinate)
     * pokud políčko není mimo hrací pole (isValidSquareCoordinate) a pokud není jednou z vyjímek (is...ColumnExclusion) pak pokračuje v určovaní jaký pohyb může provést.
     * zjistí co je na políčku v možné destinaci (possibleDestinationSquare)
     * podle barvy firgurky se přidají typy poybů do listu of legal moves.
     * @param board
     * @return list všech možných tahů pro figurku
     * pokud je kolem figurky figurka s rozdílnou alliance a větší weight a nemá kolem
     * sebe figurku se stejnou alliance pak se vrátí prázdný pole legálních pohybů.
     */
    public Collection<Move> getLegalMoves (final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        int possibleDestinationSquareNumber;
        boolean isFrozen = isFrozen(board);
        if (isFrozen){
            return null;
        }
        int[] possibleMoveCoordinates = getPossibleCoordinates(true);

        /*TODO check if problem*/
        for(final int possibleMoveCoordinate : possibleMoveCoordinates){
            possibleDestinationSquareNumber = this.piecePosition + possibleMoveCoordinate;
            if (board.isValidSquareNumber(possibleDestinationSquareNumber) &&
                    !isFirstAndEighthColumnException(this.piecePosition, possibleMoveCoordinate)){

                final Square possibleDestinationSquare = board.getSquare(possibleDestinationSquareNumber);
                if (!possibleDestinationSquare.isSquareOccupied()){
                    legalMoves.add(new SimpleMove(board, this, possibleDestinationSquareNumber));
                } else {
                    final Piece pieceAtDestination = possibleDestinationSquare.getPieceOnSquare();
                    if (this.pieceColor != pieceAtDestination.getPieceColor() && this.pieceWeight > pieceAtDestination.getPieceWeight()) {
                        legalMoves.add(new Push(board, this, pieceAtDestination, possibleDestinationSquareNumber));
                        legalMoves.add(new Pull(board, this, pieceAtDestination, possibleDestinationSquareNumber));
                    }
                }
            }
        }
        return legalMoves;
    }
    protected boolean isFrozen(Board board) {
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
     * @return pole směrů, kterýmí se může figurka pohybovat.
     * U králíka záleží na barvě a nesmí se pohybovat dozadu, jinak jsou u všech figurek směry stejné
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
    public Object getPieceType() {return this.pieceType;}
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
