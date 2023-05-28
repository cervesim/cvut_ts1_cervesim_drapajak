package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardBuilder;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;

import java.util.ArrayList;
import java.util.Collection;

public class SetupMove extends Move {
    public SetupMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
    @Override
    public Board execute() {
        BoardBuilder boardBuilder = new BoardBuilder();

        final Collection<Piece> allPieces = new ArrayList<>();
        allPieces.addAll(this.board.getGoldenPlayer().getActivePieces());
        allPieces.addAll(this.board.getSilverPlayer().getActivePieces());

        if (!allPieces.isEmpty()){
            for (final Piece piece : allPieces){
                boardBuilder.setPiece(piece);
            }
        }

        boardBuilder.setPiece(movedPiece.movePiece(this));
        boardBuilder.setMoveMaker(movedPiece.getPieceColor());
        return boardBuilder.build();
    }
}
