package cz.cvut.fel.sit.pjv.arimaa.model.players;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

import java.util.Collection;

public class GoldenPlayer extends Player{
    public GoldenPlayer(final Board board, final Collection<Move> goldenStandardLegalMoves, final Collection<Move> silverStandardLegalMoves) {
        super(board, goldenStandardLegalMoves, silverStandardLegalMoves);
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
}
