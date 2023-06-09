package cz.cvut.fel.sit.pjv.arimaa.model.board.moves;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardBuilder;
import cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.SquareLocationToString;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.view.setupGameView.SetupGameView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SetupMove extends Move {
    public boolean isDone = false;
    ArrayList<Integer> silverPossibleSquarePosition = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
    ArrayList<Integer> goldenPossibleSquarePosition = new ArrayList<>(Arrays.asList(63, 62, 61, 60, 59, 58, 57, 56, 55, 54, 53, 52, 51, 50, 49, 48));
    public SetupMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    @Override
    public String toString() {
        return movedPiece.toString() + SquareLocationToString.fromSquareNumber(destinationCoordinate) + " ";
    }

    @Override
    public Board execute() {
        BoardBuilder boardBuilder = new BoardBuilder();
        final Collection<Piece> allPieces = new ArrayList<>();

        allPieces.addAll(this.board.getGoldenPlayer().getActivePieces());
        allPieces.addAll(this.board.getSilverPlayer().getActivePieces());

        boardBuilder.setNewInitialSetup(board.getInitialSetup(), this.toString());
        if (!allPieces.isEmpty()){
            for (final Piece piece : allPieces){
                boardBuilder.setPiece(piece);
            }
        }

        setPiece(boardBuilder, movedPiece.getPieceColor());
        boardBuilder.setMoveMaker(Alliance.GOLDEN);
        return boardBuilder.build();
    }
    private void setPiece(BoardBuilder boardBuilder, Alliance alliance){
        if (alliance == Alliance.GOLDEN && goldenPossibleSquarePosition.contains(destinationCoordinate)){
            boardBuilder.setPiece(movedPiece.movePiece(this));
            isDone = true;
            SetupGameView.pieceToSet = null;
        }else if (alliance == Alliance.SILVER && silverPossibleSquarePosition.contains(destinationCoordinate)){
            boardBuilder.setPiece(movedPiece.movePiece(this));
            isDone = true;
            SetupGameView.pieceToSet = null;
        }

    }
    public boolean isDone() {
        return isDone;
    }
}
