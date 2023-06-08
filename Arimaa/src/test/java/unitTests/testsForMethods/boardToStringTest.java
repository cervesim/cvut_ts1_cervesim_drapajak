package unitTests.testsForMethods;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import org.junit.Assert;
import org.junit.Test;

public class boardToStringTest {

    @Test
    public void testBoardConfiguration() {
        Board board = Board.createTestBoard();
        String expectedBoardString =
                        "  r  r  r  r  r  r  r  r\n" +
                        "  h  d  c  e  m  d  c  h\n" +
                        "  -  -     -  -     -  -\n" +
                        "  -  -  -  -  -  -  -  -\n" +
                        "  -  -  -  -  -  -  -  -\n" +
                        "  -  -     -  -     -  -\n" +
                        "  H  D  C  M  E  D  C  H\n" +
                        "  R  R  R  R  R  R  R  R\n";

        Assert.assertEquals(expectedBoardString, board.toString());
    }
}
