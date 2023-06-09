package unitTests;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SkipTurnsMove;
import cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.SquareLocationToString;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class WinningMoveTest {

    String boardSetup = "Hg3,Df3,Hg5,Rh5,Eb6,Cf5,Rd5,Ra7,Rc1,Rd2,Rf1,Rh1,Ma4,De7,Re1,Ca1,he8,dg6,ef6,md6,cc6,cc2,dg1,rb5,rf4,hg4,re5,rc4,rb4,rd4,re4,rb3";
    String boardSetup2 = "Hg3,Df3,Hg5,Rh5,Eb6,Cf5,Rd5,Ra7,Rc1,Rd2,Rf1,Rh1,Ma3,De7,Re1,Ca1,hb5,dg6,ma6,cc6,ca2,dg1,rb3";
    ArrayList<String> piecesToSetList = new ArrayList<>(Arrays.asList(boardSetup.split(",")));
    ArrayList<String> piecesToSetList2 = new ArrayList<>(Arrays.asList(boardSetup2.split(",")));
    ArrayList<Piece> piecesToSetArray = new ArrayList<>();
    ArrayList<Piece> piecesToSetArray2 = new ArrayList<>();
    private static Board board;

    @Test
    public void winingMoveByGoldenRabbitTest () {
        for (String pieceNotation : piecesToSetList) {
            piecesToSetArray.add(Board.decodePieceToSet(pieceNotation));
        }
        board = Board.createBoardUsingArray(piecesToSetArray);
        Assertions.assertFalse(board.gameEnded);

        Piece winningRabbit = Board.decodePieceToSet("Ra7");
        Move winningMove = board.getCurrentPlayer().createMove(winningRabbit, null, SquareLocationToString.fromString("a8"));
        board = winningMove.execute();

        Assertions.assertTrue(board.gameEnded);
        Assertions.assertEquals(board.getGoldenPlayer(), board.hasWon());
    }

    @Test
    public void winingMoveBySilverRabbitTest () {
        for (String pieceNotation : piecesToSetList2) {
            piecesToSetArray2.add(Board.decodePieceToSet(pieceNotation));
        }
        board = Board.createBoardUsingArray(piecesToSetArray2);
        Assertions.assertFalse(board.gameEnded);

        Piece notVerySmartCamel = Board.decodePieceToSet("Ma3");
        Move notVeryCleverMoveFromCamel = board.getCurrentPlayer().createMove(notVerySmartCamel, null, SquareLocationToString.fromString("a4"));
        board = notVeryCleverMoveFromCamel.execute();
        board = new SkipTurnsMove(board, notVerySmartCamel, 0).execute();
        Assertions.assertFalse(board.gameEnded);
        Assertions.assertNull(board.hasWon());

        Piece winningRabbit = Board.decodePieceToSet("rb3");
        Move firstRabitsMove = board.getCurrentPlayer().createMove(winningRabbit, null, SquareLocationToString.fromString("b2"));
        board = firstRabitsMove.execute();
        Assertions.assertFalse(board.gameEnded);
        Assertions.assertNull(board.hasWon());

        Piece winningRabbit2 = Board.decodePieceToSet("rb2");
        Move winningMove = board.getCurrentPlayer().createMove(winningRabbit2, null, SquareLocationToString.fromString("b1"));
        board = winningMove.execute();
        Assertions.assertTrue(board.gameEnded);
        Assertions.assertEquals(board.getSilverPlayer(), board.hasWon());
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
        Assertions.assertEquals(board.getGoldenPlayer(), board.hasWon());
    }
    @Test
    public void winingMoveWithDestroyingLastRabbit () {
        for (String pieceNotation : piecesToSetList2) {
            piecesToSetArray2.add(Board.decodePieceToSet(pieceNotation));
        }
        board = Board.createBoardUsingArray(piecesToSetArray2);
        Assertions.assertFalse(board.gameEnded);

        Piece winningMoveFromGoldGuy = Board.decodePieceToSet("Ma3");
        Piece finalRabbit = Board.decodePieceToSet("rb3");

        Move winningMove = board.getCurrentPlayer().createMove(winningMoveFromGoldGuy, finalRabbit, SquareLocationToString.fromString("c3"));
        board = winningMove.execute();

        Assertions.assertTrue(board.gameEnded);
        Assertions.assertEquals(board.getGoldenPlayer(), board.hasWon());
    }
}
