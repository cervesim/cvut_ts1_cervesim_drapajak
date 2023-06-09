package unitTests.testsForMethods;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Pull;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Push;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SimpleMove;
import cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.SquareLocationToString;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class createMoveTestMock {
        private Board board;
        private Piece piece1;
        private Piece piece2;
        private Player currentPlayer;

        @BeforeEach
        public void setup(){
            board = mock(Board.class);
            piece1 = mock(Piece.class);
            piece2 = mock(Piece.class);
            currentPlayer = mock(Player.class);
            when(board.getCurrentPlayer()).thenReturn(currentPlayer);
        }

        @Test
        public void resultShouldBeInstanceOfSimpleMove(){
            when(currentPlayer.createMove(eq(piece1), isNull(), anyInt())).thenReturn(mock(SimpleMove.class));
            Move move = board.getCurrentPlayer().createMove(piece1, null, SquareLocationToString.fromString("d4"));
            assertTrue(move instanceof SimpleMove);
        }

        @Test
        public void resultShouldBeInstanceOfPush(){
            when(currentPlayer.createMove(eq(piece1), eq(piece2), anyInt())).thenReturn(mock(Push.class));

            Move move = board.getCurrentPlayer().createMove(piece1, piece2, SquareLocationToString.fromString("d5"));
            assertTrue(move instanceof Push);
        }

        @Test
        public void resultShouldBeInstanceOfPull(){
            when(currentPlayer.createMove(eq(piece2), eq(piece1), anyInt())).thenReturn(mock(Pull.class));

            Move move = board.getCurrentPlayer().createMove(piece2, piece1, SquareLocationToString.fromString("d4"));
            assertTrue(move instanceof Pull);
        }

}
