package cz.cvut.fel.sit.pjv.arimaa.model.pieces;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Move;

import java.util.List;

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceColor;

    public Piece(final int piecePosition, final Alliance pieceColor) {
        this.piecePosition = piecePosition;
        this.pieceColor = pieceColor;
    }
    public abstract List<Move> getLegalMoves (final Board board);

    public Alliance getPieceColor() {
        return pieceColor;
    }
//    public abstract int getWeight(); TODO
//    public abstract boolean isFrozen(); TODO
}
