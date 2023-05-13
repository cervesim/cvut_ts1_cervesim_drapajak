package cz.cvut.fel.sit.pjv.arimaa.model.players;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

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
}
