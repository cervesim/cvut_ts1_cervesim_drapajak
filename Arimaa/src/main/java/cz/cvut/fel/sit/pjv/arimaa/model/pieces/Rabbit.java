package cz.cvut.fel.sit.pjv.arimaa.model.pieces;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardUtils;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.square.Square;

import java.util.ArrayList;
import java.util.List;

public class Rabbit extends Piece /* TODO maybe delete this and have just Piece class*/{
    private final static int[] Possible_Move_Coordinates = {8} /* TODO coordinates */;
    public Rabbit(int piecePosition, Alliance pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public List<Move> getLegalMoves(Board board) {
        int possibleDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidate : Possible_Move_Coordinates){
            possibleDestinationCoordinate = this.piecePosition + currentCandidate;

            if (BoardUtils.isValidSquareCoordinate(possibleDestinationCoordinate)) {

                final Square possibleDestinationSquare = board.getSquare(possibleDestinationCoordinate);

                if (!possibleDestinationSquare.isSquareOccupied()){
                    legalMoves.add(new Move(/* TODO add parameters for simple move*/));
                } else {

                    final Piece pieceAtDestination = possibleDestinationSquare.getPieceOnSquare();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceColor();

                    if (this.pieceColor != pieceAlliance) {
                        legalMoves.add(new Move()/* TODO add parameters for push and pull moves*/);
                    }
                }
            }
        }
        return null;
    }
}
