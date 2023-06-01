package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import com.google.common.base.Objects;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardBuilder;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;

public class ViewMove extends Move{
    Piece presentPiece;
    public ViewMove(Board board, Piece movedPiece, int destinationCoordinate, Piece presentPiece) {
        super(board, movedPiece, destinationCoordinate);
        this.presentPiece = presentPiece;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleMove simpleMove)) return false;
        return destinationCoordinate == simpleMove.destinationCoordinate && Objects.equal(board, simpleMove.board) && Objects.equal(movedPiece, simpleMove.movedPiece);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(board, movedPiece, destinationCoordinate);
    }
    @Override
    public Board execute() {
        BoardBuilder boardBuilder = new BoardBuilder();
        final Player currentPlayer = this.board.getCurrentPlayer();

        for (final Piece piece : currentPlayer.getActivePieces()){
            if (!presentPiece.equals(piece)){
                boardBuilder.setPiece(piece);
            }
        }
        for (final Piece piece : currentPlayer.getOpponent().getActivePieces()){
            if (!presentPiece.equals(piece)){
                boardBuilder.setPiece(piece);
            }
        }

        boardBuilder.setPiece(movedPiece.movePiece(this));

        boardBuilder.setMoveCount(board.getMoveCount());
        boardBuilder.setInitialSetup(board.getInitialSetup());
        boardBuilder.setRoundCounter(board.getRoundCounter());
        boardBuilder.setMovesHistory(board.getMovesHistory());
        boardBuilder.setMoveMaker(board.getCurrentPlayer().getAlliance());

        return boardBuilder.build();
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
