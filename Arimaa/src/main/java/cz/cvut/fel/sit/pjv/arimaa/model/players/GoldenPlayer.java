package cz.cvut.fel.sit.pjv.arimaa.model.players;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.PieceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GoldenPlayer extends Player{
    public GoldenPlayer(final Board board, final Collection<Move> goldenStandardLegalMoves, final Collection<Move> silverStandardLegalMoves) {
        super(board, goldenStandardLegalMoves, silverStandardLegalMoves);
    }

    @Override
    public String toString() {
        return "GoldenPlayer";
    }
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getGoldenPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.GOLDEN;
    }

    @Override
    public Player getOpponent() {
        return this.board.getSilverPlayer();
    }

    @Override
    public boolean rabbitFinishedHisJourney() {
        ArrayList<Integer> lastSquaresPosition = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
        for (Piece playersRabbit : getRabbits()) {
            if (lastSquaresPosition.contains(playersRabbit.getPiecePosition())) {
                return true;
            }
        }
        return false;
    }
    public Collection<Piece> getAllAvailablePieces(){
        ArrayList<Piece> availablePieces = new ArrayList<>();
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.HORSE, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.DOG, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.CAT, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.CAMEL, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.ELEPHANT, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.DOG, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.CAT, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.HORSE, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 0));
        availablePieces.add(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 0));
        return availablePieces;
    }
}
