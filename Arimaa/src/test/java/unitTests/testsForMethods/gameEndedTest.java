package unitTests.testsForMethods;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class gameEndedTest {

    @Test
    public void testGameEndedOnTestBoard() {
        Board board = Board.createTestBoard();
        assertFalse(board.gameEnded);
        assertNull(board.hasWon());
    }

    @ParameterizedTest
    @CsvSource({
            "'Hf2,De2,Cd2,Mc2,hc7,dd7,me7,rf7,Rf8', true, g",
            "'Hd2,De2,Cc2,Rf2,Eh1,hb7,dc7,ed7,rf7', false, null",
            "'Hd3,De4,Cc5,Rf1,Eh6,hb1,dc2,ed5,rf1', true, s",
            "'Hf2,De2,Cd2,Mc2,hc7,dd7,me7,rf7', true, s"
    })
    public void testGameEndedOnRandomBoard(String boardSetup, boolean gameEnded, String hasWon) {
        ArrayList<String> piecesToSetList = new ArrayList<>(Arrays.asList(boardSetup.split(",")));
        ArrayList<Piece> piecesToSetArray = new ArrayList<>();

        for (String pieceNotation : piecesToSetList) {
            piecesToSetArray.add(Board.decodePieceToSet(pieceNotation));
        }

        Board board = Board.createBoardUsingArray(piecesToSetArray);
        assertEquals(gameEnded, board.gameEnded);
        if (!hasWon.equals("null")){
            assertEquals(hasWon, board.hasWon().toString());
        } else assertNull(board.hasWon());

    }

}
