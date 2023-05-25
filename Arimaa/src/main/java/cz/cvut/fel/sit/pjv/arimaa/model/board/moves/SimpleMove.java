package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import cz.cvut.fel.sit.pjv.arimaa.controller.BoardBuilder;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;

public class SimpleMove extends Move /*TODO*/ {
    public SimpleMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
    @Override
    public Board execute() {
        final BoardBuilder boardBuilder = new BoardBuilder();
        final Player currentPlayer = this.board.getCurrentPlayer();
        final Piece movedPiece = this.movedPiece;

        for (final Piece piece : currentPlayer.getActivePieces()){
            if (movedPiece.equals(piece)){
                boardBuilder.setPiece(movedPiece.movePiece(this));
            }else {
                boardBuilder.setPiece(piece);
            }
        }
        for (final Piece piece : currentPlayer.getOpponent().getActivePieces()){
            boardBuilder.setPiece(piece);
        }
        /*TODO add notation*/

        boardBuilder.setMoveMaker(currentPlayer.getOpponent().getAlliance());
        return boardBuilder.build();
    }
}
