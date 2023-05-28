package cz.cvut.fel.sit.pjv.arimaa.model.players;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SilverPlayer extends Player {
    public SilverPlayer(final Board board,final Collection<Move> silverStandardLegalMoves,final Collection<Move> goldenStandardLegalMoves) {
        super(board, silverStandardLegalMoves, goldenStandardLegalMoves);
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
}
