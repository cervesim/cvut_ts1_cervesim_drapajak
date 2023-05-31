package cz.cvut.fel.sit.pjv.arimaa.model.modelUtils;

public class GameUtils {
    public static boolean isTrapSquare(int squarePosition){
        return (squarePosition == 18 || squarePosition == 21 || squarePosition == 42 || squarePosition == 45);
    }
}
