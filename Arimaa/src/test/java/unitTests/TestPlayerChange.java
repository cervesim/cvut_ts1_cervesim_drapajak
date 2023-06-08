package unitTests;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SkipTurnsMove;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPlayerChange{
    @Test
    public void testPlayerChange(){
        Board board = Board.createTestBoard();
        assertEquals(board.getCurrentPlayer(), board.getGoldenPlayer());
        for (int i=0; i<4; i++) {
            Move move = board.getCurrentPlayer().getLegalMoves().get(i);
            board = move.execute();
        }
        assertEquals(board.getCurrentPlayer(), board.getSilverPlayer());
        for (int i=0; i<2; i++) {
            Move move = board.getCurrentPlayer().getLegalMoves().get(i);
            board = move.execute();
        }
        board = new SkipTurnsMove(board, null, 0).execute();
        assertEquals(board.getCurrentPlayer(), board.getGoldenPlayer());
        if (board.getMoveCount() > 1){
            board = new SkipTurnsMove(board, null, 0).execute();
        }
        assertEquals(board.getCurrentPlayer(), board.getGoldenPlayer());
    }
}
