package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import com.google.common.base.Objects;
import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardBuilder;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.SquareLocationToString;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;

public class Pull extends Move{
    final Piece pulledPiece;

    public Pull(final Board board, final Piece movedPiece, final Piece pulledPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
        this.pulledPiece = pulledPiece;
    }

    @Override
    public String toString() {
        return movedPiece.toString() + SquareLocationToString.fromSquareNumber(destinationCoordinate) + getDirection() + " ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pull pull)) return false;
        if (!super.equals(o)) return false;
        return Objects.equal(pulledPiece, pull.pulledPiece);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), pulledPiece);
    }

    @Override
    public Board execute() {
        final BoardBuilder boardBuilder = new BoardBuilder();
        final Player currentPlayer = board.getCurrentPlayer();
        boardBuilder.setGoldenInitialSetup(board.goldenInitialSetup, null);
        boardBuilder.setSilverInitialSetup(board.silverInitialSetup, null);

        System.out.print(this); /*TODO destroy*/
        for (final Piece piece : currentPlayer.getActivePieces()){
            if (movedPiece.equals(piece)){
                boardBuilder.setPiece(movedPiece.movePiece(this));
            }else {
                boardBuilder.setPiece(piece);
            }
        }
        Move pulledPieceMove = new SimpleMove(board, pulledPiece, getCurrentCoordinate());
        System.out.print(pulledPieceMove); /*TODO destroy*/
        for (final Piece piece : currentPlayer.getOpponent().getActivePieces()){
            if (pulledPiece.equals(piece)){
                boardBuilder.setPiece(pulledPiece.movePiece(pulledPieceMove));
            }else {
                boardBuilder.setPiece(piece);
            }
        }
        setBoardBuilder(boardBuilder, 2);
        return boardBuilder.build();
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
