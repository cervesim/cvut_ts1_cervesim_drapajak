package unitTests;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.SquareLocationToString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TrapSquareTest {
    @Test
    public void steppedOnTrapSquareTest(){
        Board board = Board.createTestBoard();
        assertEquals(board.getCurrentPlayer().getActivePieces().size(), 16);
        assertEquals(board.getCurrentPlayer().getOpponent().getActivePieces().size(), 16);
        Move move = board.getCurrentPlayer().createMove(Board.decodePieceToSet("Df2"), null, SquareLocationToString.fromString("f3"));

        board = move.execute();
        assertEquals(board.getCurrentPlayer().getActivePieces().size(), 15);
        assertEquals(board.getCurrentPlayer().getOpponent().getActivePieces().size(), 16);

        assertNull(board.getSquare(SquareLocationToString.fromString("f3")).getPieceOnSquare());

    }


}
