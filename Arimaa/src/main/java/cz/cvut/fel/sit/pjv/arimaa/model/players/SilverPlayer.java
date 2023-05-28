package cz.cvut.fel.sit.pjv.arimaa.model.players;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.PieceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SilverPlayer extends Player {
    public SilverPlayer(final Board board,final Collection<Move> silverStandardLegalMoves,final Collection<Move> goldenStandardLegalMoves) {
        super(board, silverStandardLegalMoves, goldenStandardLegalMoves);
    }
    @Override
    public String toString() {
        return "SilverPlayer";
    }
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getSilverPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.SILVER;
    }

    @Override
    public Player getOpponent() {
        return this.board.getGoldenPlayer();
    }

    @Override
    public boolean rabbitFinishedHisJourney() {
        ArrayList<Integer> lastSquaresPosition = new ArrayList<>(Arrays.asList(63, 62, 61, 60, 59, 58, 57, 56));
        for (Piece playersRabbit : getRabbits()) {
            if (lastSquaresPosition.contains(playersRabbit.getPiecePosition())) {
                return true;
            }
        }
        return false;
    }
    public Collection<Piece> getAllAvailablePieces(){
        ArrayList<Piece> availablePieces = new ArrayList<>();
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.HORSE, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.DOG, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.CAT, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.CAMEL, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.ELEPHANT, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.DOG, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.CAT, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.HORSE, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.RABBIT, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.RABBIT, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.RABBIT, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.RABBIT, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.RABBIT, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.RABBIT, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.RABBIT, 0));
        availablePieces.add(new Piece(Alliance.SILVER, PieceType.RABBIT, 0));
        return availablePieces;
    }
}
