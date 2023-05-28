package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardBuilder;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;
import cz.cvut.fel.sit.pjv.arimaa.view.setupGameView.SetupGameView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SetupMove extends Move {
    public boolean isDone = false;
    public SetupMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
    @Override
    public Board execute() {
        BoardBuilder boardBuilder = new BoardBuilder();
        ArrayList<Integer> silverPossibleSquarePosition = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        ArrayList<Integer> goldenPossibleSquarePosition = new ArrayList<>(Arrays.asList(63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48));
        final Collection<Piece> allPieces = new ArrayList<>();
        allPieces.addAll(this.board.getGoldenPlayer().getActivePieces());
        allPieces.addAll(this.board.getSilverPlayer().getActivePieces());

        if (!allPieces.isEmpty()){
            for (final Piece piece : allPieces){
                boardBuilder.setPiece(piece);
            }
        }
        if(movedPiece.getPieceColor() == Alliance.GOLDEN && goldenPossibleSquarePosition.contains(destinationCoordinate)){
            boardBuilder.setPiece(movedPiece.movePiece(this));
            isDone = true;
            SetupGameView.pieceToSet = null;
        } else if (movedPiece.getPieceColor() == Alliance.SILVER && silverPossibleSquarePosition.contains(destinationCoordinate)) {
            boardBuilder.setPiece(movedPiece.movePiece(this));
            isDone = true;
            SetupGameView.pieceToSet = null;
        }
        boardBuilder.setMoveMaker(Alliance.GOLDEN);

        return boardBuilder.build();
    }
    public boolean isDone() {
        return isDone;
    }
}
