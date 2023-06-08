package unitTests.testsForMethods;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class isSupportedTest {
    String boardSetup = "Hg3,Df3,Hg5,Rh5,Eb6,Cf5,Rd5,Rb2,Rc1,Rd2,Rf1,Rh1,Ma4,De7,Re1,Ca1,he8,dg6,ef6,md6,cc6,cc2,dg1,rb5,rf4,hg4,re5,rc4,rb4,rd4,re4,rb3";
    ArrayList<String> piecesToSetList = new ArrayList<>(Arrays.asList(boardSetup.split(",")));
    ArrayList<Piece> piecesToSetArray = new ArrayList<>();

    @Test
    public void isSupportedTest () {
        for (String pieceNotation : piecesToSetList) {
            piecesToSetArray.add(Board.decodePieceToSet(pieceNotation));
        }
        Board board = Board.createBoardUsingArray(piecesToSetArray);

        Piece piece1 = Board.decodePieceToSet("Hg5");
        Piece piece2 = Board.decodePieceToSet("De7");
        Piece piece3 = Board.decodePieceToSet("Eb6");
        Piece piece4 = Board.decodePieceToSet("cc6");
        Piece piece5 = Board.decodePieceToSet("Rd5");

        assertTrue(piece1.isSupported(board));
        assertFalse(piece2.isSupported(board));
        assertFalse(piece3.isSupported(board));
        assertTrue(piece4.isSupported(board));
        assertFalse(piece5.isSupported(board));
    }
}
