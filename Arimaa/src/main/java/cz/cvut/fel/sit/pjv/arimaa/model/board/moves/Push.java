package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import cz.cvut.fel.sit.pjv.arimaa.controller.BoardBuilder;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;

/**
 *
 */
public class Push extends Move /*TODO*/{
    Piece pushedPiece;
    public Push(Board board, Piece movedPiece, Piece pushedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
        this.pushedPiece = pushedPiece;
    }
    @Override
    public Board execute() {
        final BoardBuilder boardBuilder = new BoardBuilder();
        final Player currentPlayer = board.getCurrentPlayer();

        Move movedPieceMove = new SimpleMove(board, movedPiece, pushedPiece.getPiecePosition());
        for (final Piece piece : currentPlayer.getActivePieces()){
            if (movedPiece.equals(piece)){
                boardBuilder.setPiece(movedPiece.movePiece(movedPieceMove));
            }else {
                boardBuilder.setPiece(piece);
            }
        }
        Move pushedPieceMove = new SimpleMove(board, pushedPiece, destinationCoordinate);
        for (final Piece piece : currentPlayer.getOpponent().getActivePieces()){
            if (pushedPiece.equals(piece)){
                boardBuilder.setPiece(pushedPiece.movePiece(pushedPieceMove));
            }else {
                boardBuilder.setPiece(piece);
            }
        }

        boardBuilder.setMoveMaker(currentPlayer.getOpponent().getAlliance());
        return boardBuilder.build();
    }
}
