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

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceColor;

    public Piece(final int piecePosition, final Alliance pieceColor) {
        this.piecePosition = piecePosition;
        this.pieceColor = pieceColor;
//        this.pieceWeight = getWeight(); TODO
    }
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

                /*TODO add if is frozen return null possible move coordinates*/
                /*TODO change getPossibleMoveCoordinates so its checking all squares where rabbit can be frozen*/

                final Square possibleDestinationSquare = board.getSquare(possibleDestinationCoordinate);

                if (!possibleDestinationSquare.isSquareOccupied()){
                    legalMoves.add(new SimpleMove(board, this, possibleDestinationCoordinate));
                    /*TODO add parameters for simple move*/
                } else {

                    final Piece pieceAtDestination = possibleDestinationSquare.getPieceOnSquare();
                    final Alliance pieceColor = pieceAtDestination.getPieceColor();

                    if (this.pieceColor != pieceColor) {
                        legalMoves.add(new Push(board, this, possibleDestinationCoordinate));
                        legalMoves.add(new Pull(board, this, possibleDestinationCoordinate));
                        /* TODO complete push and pull classes*/
                    }
                }
            }
        }
        return legalMoves;
    }

    /**
     * @return
     */
    public abstract int[] getPossibleMoveCoordinates();

//    public abstract int getWeight(); TODO
//    public abstract boolean isFrozen(); TODO

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

    /**
     *
     */
    public enum PieceType{
        ELEPHANT("E"),
        CAMEL("M"),
        HORSE("H"),
        DOG("D"),
        CAT("C"),
        RABBIT("R");

        private String pieceName;
        PieceType(String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }
    }
}
