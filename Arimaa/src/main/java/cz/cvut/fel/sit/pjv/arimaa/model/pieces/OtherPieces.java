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
import java.util.List;

public class OtherPieces extends Piece{
    public OtherPieces(int piecePosition, Alliance pieceColor) {
        super(piecePosition, pieceColor);
    }
    @Override
    public int[] getPossibleMoveCoordinates() {
        return new int[]{-8, -1, 1, 8};
    }
}
