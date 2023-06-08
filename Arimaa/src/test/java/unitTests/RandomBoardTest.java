package unitTests;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.view.gameView.BoardView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RandomBoardTest {
    static String boardSetup = "Hg5,Ch2,Mg2,Ef2,De2,Ce4,Rg4,Rf4,Rd2,Hf3,Rc2,Rb4,Rc4,Ra5,Da4,Rb3,he5,eh5,cc5,mb5,dd5,da6,hb6,cd4,rd3,re3,rb2,ra3,ra2,rd6,re6,rf5";
    static ArrayList<String> piecesToSetList = new ArrayList<>(Arrays.asList(boardSetup.split(",")));
    static ArrayList<Piece> piecesToSetArray = new ArrayList<>();
    static Board board;

    @BeforeAll
    public static void setBoard () {
        for(String pieceNotation : piecesToSetList){
            piecesToSetArray.add(Board.decodePieceToSet(pieceNotation));
        }
        board = Board.createBoardUsingArray(piecesToSetArray);
    }
    @Test
    public void initialBoardTest() {
        assertEquals(board.getCurrentPlayer().getLegalMoves().size(), 15);
        assertEquals(board.getCurrentPlayer().getOpponent().getLegalMoves().size(), 18);
        assertEquals(board.getCurrentPlayer(), board.getGoldenPlayer());
        assertEquals(board.getCurrentPlayer().getOpponent(), board.getSilverPlayer());

        assertEquals("g", board.getGoldenPlayer().toString());
        assertEquals("s", board.getSilverPlayer().toString());

        ArrayList<Piece> allPieces = board.getGoldenPieces();
        allPieces.addAll(board.getSilverPieces());
        ArrayList<Move> allMoves = board.getGoldenPlayer().getLegalMoves();
        allMoves.addAll(board.getSilverPlayer().getLegalMoves());

        assertEquals(33, allMoves.size());
        assertEquals(32, allPieces.size());
        assertFalse(board.gameEnded);
    }

    private static boolean checkMoveAndExecute(Piece piece, Piece secondPiece, int destinationSquare){
        Move move = board.getCurrentPlayer().createMove(BoardView.firstClickedSquare.getPieceOnSquare(),
                null, BoardView.secondClickedSquare.getSquareLocation());
        if (board.getCurrentPlayer().isMoveLegal(move)) {
            board = move.execute();
            return true;
        } else return false;
    }

}
