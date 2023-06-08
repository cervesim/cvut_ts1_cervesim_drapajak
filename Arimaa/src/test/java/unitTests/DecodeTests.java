package unitTests;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.ViewMove;
import cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.SquareLocationToString;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.PieceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DecodeTests {
    private static Board board;
    private static Move move1;
    private static Move move2;
    private static Move move3;
    private static Move move4;
    private static Piece piece1;
    private static Piece piece2;
    private static Piece piece3;
    private static Piece piece4;
    @BeforeEach
    void beforeEach() {
        board = mock(Board.class);

        Piece movedPiece1 = new Piece(Alliance.GOLDEN, PieceType.ELEPHANT, SquareLocationToString.fromString("e3"));
        Piece previousPiece1 = new Piece(Alliance.GOLDEN, PieceType.ELEPHANT, SquareLocationToString.fromString("e2"));
        move1 = new ViewMove(board, movedPiece1, SquareLocationToString.fromString("e3"), previousPiece1);

        Piece movedPiece2 = new Piece(Alliance.GOLDEN, PieceType.ELEPHANT, SquareLocationToString.fromString("e5"));
        Piece previousPiece2 = new Piece(Alliance.GOLDEN, PieceType.ELEPHANT, SquareLocationToString.fromString("e4"));
        move2 = new ViewMove(board, movedPiece2, SquareLocationToString.fromString("e5"), previousPiece2);

        Piece movedPiece3 = new Piece(Alliance.GOLDEN, PieceType.ELEPHANT, SquareLocationToString.fromString("g4"));
        Piece previousPiece3 = new Piece(Alliance.GOLDEN, PieceType.ELEPHANT, SquareLocationToString.fromString("g3"));
        move3 = new ViewMove(board, movedPiece3, SquareLocationToString.fromString("g4"), previousPiece3);

        Piece movedPiece4 = new Piece(Alliance.GOLDEN, PieceType.ELEPHANT, SquareLocationToString.fromString("c4"));
        Piece previousPiece4 = new Piece(Alliance.GOLDEN, PieceType.ELEPHANT, SquareLocationToString.fromString("c3"));
        move4 = new ViewMove(board, movedPiece4, SquareLocationToString.fromString("c4"), previousPiece4);

        piece1 = previousPiece1;
        piece2 = new Piece(Alliance.SILVER, PieceType.RABBIT, SquareLocationToString.fromString("f1"));
        piece3 = new Piece(Alliance.GOLDEN, PieceType.CAMEL, SquareLocationToString.fromString("c4"));
        piece4 = new Piece(Alliance.SILVER, PieceType.CAT, SquareLocationToString.fromString("g5"));
    }
    @Test
    public void decodeMoveTest() {
        when(board.decodeMove("Ee2n", false)).thenReturn(move1);
        when(board.decodeMove("Ee4n", false)).thenReturn(move2);
        when(board.decodeMove("Eg3n", false)).thenReturn(move3);
        when(board.decodeMove("Ec3n", false)).thenReturn(move4);

        Move viewMove1 = board.decodeMove("Ee2n", false);
        assertEquals(viewMove1, move1);

        Move viewMove2 = board.decodeMove("Ee4n", false);
        assertEquals(viewMove2, move2);

        Move viewMove3 = board.decodeMove("Eg3n", false);
        assertEquals(viewMove3, move3);

        Move viewMove4 = board.decodeMove("Ec3n", false);
        assertEquals(viewMove4, move4);

        verify(board, times(1)).decodeMove("Ee2n", false);
        verify(board, times(1)).decodeMove("Ee4n", false);
        verify(board, times(1)).decodeMove("Eg3n", false);
        verify(board, times(1)).decodeMove("Ec3n", false);
        verifyNoMoreInteractions(board);
    }
    @Test
    public void decodePieceToSetTest (){
        Piece decodedPiece1 = Board.decodePieceToSet("Ee2");
        Piece decodedPiece2 = Board.decodePieceToSet("rf1");
        Piece decodedPiece3 = Board.decodePieceToSet("Mc4");
        Piece decodedPiece4 = Board.decodePieceToSet("cg5");
        assertEquals(piece1, decodedPiece1);
        assertEquals(piece2, decodedPiece2);
        assertEquals(piece3, decodedPiece3);
        assertEquals(piece4, decodedPiece4);
    }
}
