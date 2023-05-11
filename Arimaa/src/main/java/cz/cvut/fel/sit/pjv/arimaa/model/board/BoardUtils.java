package cz.cvut.fel.sit.pjv.arimaa.model.board;

public class BoardUtils {
    private  BoardUtils(){
        throw new RuntimeException("Don't do this");
    }
    public static boolean isValidSquareCoordinate(int squareCoordinate) {
        return squareCoordinate >= 0 && squareCoordinate < 64 /* TODO is it really < 64 */;
    }
}
