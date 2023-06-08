package unitTests;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class WinningMoveTest {

    String boardSetup = "Hg3,Df3,Hg5,Rh5,Eb6,Cf5,Rd5,Rb2,Rc1,Rd2,Rf1,Rh1,Ma4,De7,Re1,Ca1,he8,dg6,ef6,md6,cc6,cc2,dg1,rb5,rf4,hg4,re5,rc4,rb4,rd4,re4,rb3";
    ArrayList<String> piecesToSetList = new ArrayList<>(Arrays.asList(boardSetup.split(",")));
    ArrayList<Piece> piecesToSetArray = new ArrayList<>();

    @Test
    public void winingMoveTest () {
        for (String pieceNotation : piecesToSetList) {
            piecesToSetArray.add(Board.decodePieceToSet(pieceNotation));
        }
        Board board = Board.createBoardUsingArray(piecesToSetArray);
    }
}
