package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardBuilder;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;

import java.util.Objects;

public class Pull extends Move /*TODO*/{
    final Piece pulledPiece;

    public Pull(final Board board, final Piece movedPiece, final Piece pulledPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
        this.pulledPiece = pulledPiece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pull pull)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(pulledPiece, pull.pulledPiece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pulledPiece);
    }

    @Override /*TODO*/
    public Board execute() {
        final BoardBuilder boardBuilder = new BoardBuilder();
        final Player currentPlayer = board.getCurrentPlayer();

        for (final Piece piece : currentPlayer.getActivePieces()){
            if (movedPiece.equals(piece)){
                boardBuilder.setPiece(movedPiece.movePiece(this));
            }else {
                boardBuilder.setPiece(piece);
            }
        }
        Move pulledPieceMove = new SimpleMove(board, pulledPiece, getCurrentCoordiante());
        for (final Piece piece : currentPlayer.getOpponent().getActivePieces()){
            if (pulledPiece.equals(piece)){
                boardBuilder.setPiece(pulledPiece.movePiece(pulledPieceMove));
            }else {
                boardBuilder.setPiece(piece);
            }
        }
        /*TODO add notation*/

        boardBuilder.setMoveMaker(currentPlayer.getOpponent().getAlliance());
        return boardBuilder.build();
    }
}
