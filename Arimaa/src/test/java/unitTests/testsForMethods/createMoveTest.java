package unitTests.testsForMethods;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Pull;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Push;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SimpleMove;
import cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.SquareLocationToString;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.InstanceOf;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class createMoveTest {
    static Board board;
    static Piece piece1;
    static Piece piece2;
    @BeforeAll
    public static void setup(){
        board = Board.createEmptyBoard();
        piece1 = Board.decodePieceToSet("Ee4");
        piece2 = Board.decodePieceToSet("re5");
    }
    @Test
    public void resultShouldBeInstanceOfSimpleMove(){
        Move move = board.getCurrentPlayer().createMove(piece1, null, SquareLocationToString.fromString("d4"));
        assertTrue(move instanceof SimpleMove);
    }
    @Test
    public void resultShouldBeInstanceOfPush(){
        Move move = board.getCurrentPlayer().createMove(piece1, piece2, SquareLocationToString.fromString("d5"));
        assertTrue(move instanceof Push);
    }
    @Test
    public void resultShouldBeInstanceOfPull(){
        Move move = board.getCurrentPlayer().createMove(piece2, piece1, SquareLocationToString.fromString("d4"));
        assertTrue(move instanceof Pull);
    }
}
