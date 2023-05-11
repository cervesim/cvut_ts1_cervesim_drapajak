package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    public Move(Board board, Piece movedPiece, int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }
}
