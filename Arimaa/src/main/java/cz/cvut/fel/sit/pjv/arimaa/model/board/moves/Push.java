package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import com.google.common.base.Objects;
import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardBuilder;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;

import static cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.NotationUtils.isTrapSquare;

/**
 *
 */
public class Push extends Move{
    Piece pushedPiece;

    /**
     * @param pushedPiece piece that has been pushed by moved piece
     * @param destinationCoordinate for pushed piece
     */
    public Push(Board board, Piece movedPiece, Piece pushedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
        this.pushedPiece = pushedPiece;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Push push)) return false;
        if (!super.equals(o)) return false;
        return Objects.equal(pushedPiece, push.pushedPiece);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), pushedPiece);
    }

    /**
     * splits one move into two simple moves because of the movePiece method inside a move class
     * @return new Board
     */
    @Override
    public Board execute() {
        BoardBuilder boardBuilder = new BoardBuilder();
        Player currentPlayer = board.getCurrentPlayer();

        Move pushedPieceMove = new SimpleMove(board, pushedPiece, destinationCoordinate);
        boardBuilder.setNewMovesHistory(board.getMovesHistory(), pushedPieceMove.toString());

        if (isTrapSquare(destinationCoordinate)) {
            Board futureBoard = new checkMove(board, pushedPiece, destinationCoordinate).execute();
            Board.pushOrPullNoted = !futureBoard.getSquare(destinationCoordinate).isSupported(futureBoard);
        }

        for (Piece piece : currentPlayer.getOpponent().getActivePieces()) {
            if (pushedPiece.equals(piece)) {
                boardBuilder.setPiece(pushedPiece.movePiece(pushedPieceMove));
            } else {
                boardBuilder.setPiece(piece);
            }
        }

        Move movedPieceMove = new SimpleMove(board, movedPiece, pushedPiece.getPiecePosition());
        boardBuilder.setNewMovesHistory(boardBuilder.getMovesHistory(), movedPieceMove.toString());

        for (Piece piece : currentPlayer.getActivePieces()) {
            if (movedPiece.equals(piece)) {
                boardBuilder.setPiece(movedPiece.movePiece(movedPieceMove));
            } else {
                boardBuilder.setPiece(piece);
            }
        }

        setBoardBuilder(boardBuilder, 2);
        Board newBoard = boardBuilder.build();
        Board.pushOrPullNoted = false;
        return newBoard;
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
