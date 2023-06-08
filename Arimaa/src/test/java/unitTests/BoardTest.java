package unitTests;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SimpleMove;
import cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.SquareLocationToString;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.PieceType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void initialTestBoard() {
        final Board board = Board.createTestBoard();
        assertEquals(board.getCurrentPlayer().getLegalMoves().size(), 8);
        assertEquals(board.getCurrentPlayer().getOpponent().getLegalMoves().size(), 8);
        assertEquals(board.getCurrentPlayer(), board.getGoldenPlayer());
        assertEquals(board.getCurrentPlayer().getOpponent(), board.getSilverPlayer());

        assertEquals("g", board.getGoldenPlayer().toString());
        assertEquals("s", board.getSilverPlayer().toString());

        ArrayList<Piece> allPieces = board.getGoldenPieces();
        allPieces.addAll(board.getSilverPieces());
        ArrayList<Move> allMoves = board.getGoldenPlayer().getLegalMoves();
        allMoves.addAll(board.getSilverPlayer().getLegalMoves());

        assertEquals(allMoves.size(), 16);
        assertEquals(allPieces.size(), 32);
        assertFalse(board.gameEnded);
    }
    @Test
    public void testAfterMove(){
        Board board = Board.createTestBoard();
        Piece piece = new Piece(Alliance.GOLDEN, PieceType.ELEPHANT, SquareLocationToString.fromString("e2"));
        Move simpleMove = new SimpleMove(board, piece, SquareLocationToString.fromString("e3"));
        Board newBoard = simpleMove.execute();

        assertEquals(newBoard.getCurrentPlayer().getLegalMoves().size(), 14);
        assertEquals(newBoard.getCurrentPlayer().getOpponent().getLegalMoves().size(), 8);
    }

}
