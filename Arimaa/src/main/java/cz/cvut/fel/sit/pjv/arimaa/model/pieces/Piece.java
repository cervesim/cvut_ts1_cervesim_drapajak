package cz.cvut.fel.sit.pjv.arimaa.model.pieces;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardUtils;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Pull;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Push;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SimpleMove;
import cz.cvut.fel.sit.pjv.arimaa.model.board.square.Square;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Piece {
    protected final Alliance pieceColor;
    protected final PieceType pieceType;
    protected final int piecePosition;
    protected final int pieceWeight;

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
    /**
     * nejdříve metoda zjistí všechny možný směry jakými se může pohybovat (getPossibleMoveCoordinates),
     * poté pro každý směr určí podle své polohy na jake políčko by se moha figurka pohnout. (possibleDestinationCoordinate)
     * pokud políčko není mimo hrací pole (isValidSquareCoordinate) a pokud není jednou z vyjímek (is...ColumnExclusion) pak pokračuje v určovaní jaký pohyb může provést.
     * zjistí co je na políčku v možné destinaci (possibleDestinationSquare)
     * podle barvy firgurky se přidají typy poybů do listu of legal moves.
     * @param board
     * @return list všech možných tahů pro figurku
     *TODO pokud je kolem figurky figurka s rozdílnou alliance a větší weight a nemá kolem
     *TODO sebe figurku se stejnou alliance pak se vrátí prazdný pole leaálních pohybů.
     */
    public Collection<Move> getLegalMoves (final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        int[] Possible_Move_Coordinates = getPossibleMoveCoordinates();

        int possibleDestinationCoordinate;

        for(final int currentCandidateOffset : Possible_Move_Coordinates){
            possibleDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if (BoardUtils.isValidSquareCoordinate(possibleDestinationCoordinate)){
                if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isEightColumnExclusion(this.piecePosition, currentCandidateOffset)){
                    continue;
                }

                final Square possibleDestinationSquare = board.getSquare(possibleDestinationCoordinate);
                if (!possibleDestinationSquare.isSquareOccupied()){
                    legalMoves.add(new SimpleMove(board, this, possibleDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = possibleDestinationSquare.getPieceOnSquare();
                    final Alliance pieceColor = pieceAtDestination.getPieceColor();
                    if (this.pieceColor != pieceColor) {
                        legalMoves.add(new Push(board, this, possibleDestinationCoordinate));
                        legalMoves.add(new Pull(board, this, possibleDestinationCoordinate));
                    }
                }
            }
        }
        return legalMoves;
    }

    /**
     * @return pole směrů, kterýmí se může figurka pohybovat.
     * U králíka záleží na barvě a nesmí se pohybovat dozadu, jinak jsou u všech figurek směry stejné
     */
    public int[] getPossibleMoveCoordinates(){
        if (this.pieceType == PieceType.RABBIT) {
            if (this.pieceColor == Alliance.GOLDEN){
                return new int[]{-8, -1, 1};
            } else return new int[]{-1, 1, 8};
        } else return new int[]{-8, -1, 1, 8};
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

    public int getPiecePosition() {
        return this.piecePosition;
    }

    public Alliance getPieceColor() {return this.pieceColor;}

    /**
     * @param currentPosition
     * @param candidateOffset
     * @return
     */
    protected static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.First_Column[currentPosition] && (candidateOffset == -1);
    }

    /**
     * @param currentPosition
     * @param candidateOffset
     * @return
     */
    protected static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.Eight_Column[currentPosition] && (candidateOffset == 1);
    }

}
