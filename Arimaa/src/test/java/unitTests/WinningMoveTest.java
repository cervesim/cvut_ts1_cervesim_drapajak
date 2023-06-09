package unitTests;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.SquareLocationToString;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class WinningMoveTest {

    String boardSetup = "Hg3,Df3,Hg5,Rh5,Eb6,Cf5,Rd5,Ra7,Rc1,Rd2,Rf1,Rh1,Ma4,De7,Re1,Ca1,he8,dg6,ef6,md6,cc6,cc2,dg1,rb5,rf4,hg4,re5,rc4,rb4,rd4,re4,rb3";
    String boardSetup2 = "Hg3,Df3,Hg5,Rh5,Eb6,Cf5,Rd5,Ra7,Rc1,Rd2,Rf1,Rh1,Ma3,De7,Re1,Ca1,hb5,dg6,ma6,cc6,ca2,dg1,rb3";
    ArrayList<String> piecesToSetList = new ArrayList<>(Arrays.asList(boardSetup.split(",")));
    ArrayList<String> piecesToSetList2 = new ArrayList<>(Arrays.asList(boardSetup2.split(",")));
    ArrayList<Piece> piecesToSetArray = new ArrayList<>();
    ArrayList<Piece> piecesToSetArray2 = new ArrayList<>();
    private static Board board;

    @Test
    public void winingMoveByRabbitTest () {
        for (String pieceNotation : piecesToSetList) {
            piecesToSetArray.add(Board.decodePieceToSet(pieceNotation));
        }
        board = Board.createBoardUsingArray(piecesToSetArray);
        Assertions.assertFalse(board.gameEnded);

        Piece winningRabbit = Board.decodePieceToSet("Ra7");
        Move winningMove = board.getCurrentPlayer().createMove(winningRabbit, null, SquareLocationToString.fromString("a8"));
        board = winningMove.execute();

        Assertions.assertTrue(board.gameEnded);
    }

    @Test
    public void winingMoveByNoLegalMoves () {
        for (String pieceNotation : piecesToSetList2) {
            piecesToSetArray2.add(Board.decodePieceToSet(pieceNotation));
        }
        board = Board.createBoardUsingArray(piecesToSetArray2);
        Assertions.assertFalse(board.gameEnded);

        Piece winningMoveFromGoldGuy = Board.decodePieceToSet("Hg3");
        Move winningMove = board.getCurrentPlayer().createMove(winningMoveFromGoldGuy, null, SquareLocationToString.fromString("g2"));
        board = winningMove.execute();

        Assertions.assertTrue(board.gameEnded);
    }
}
