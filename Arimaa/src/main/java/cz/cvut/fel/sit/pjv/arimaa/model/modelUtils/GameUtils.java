package cz.cvut.fel.sit.pjv.arimaa.model.modelUtils;

import cz.cvut.fel.sit.pjv.arimaa.model.pieces.PieceType;

public class GameUtils {
    public static boolean isTrapSquare(int squarePosition){
        return (squarePosition == 18 || squarePosition == 21 || squarePosition == 42 || squarePosition == 45);
    }
    public static PieceType fromCharToPieceType(String character) {
        return switch (character) {
            case "e" -> PieceType.ELEPHANT;
            case "m" -> PieceType.CAMEL;
            case "h" -> PieceType.HORSE;
            case "d" -> PieceType.DOG;
            case "c" -> PieceType.CAT;
            default -> PieceType.RABBIT;
        };
    }
    public static int fromCharToDestinationCoordinate(String character) {
        return switch (character) {
            case "n" -> 8;
            case "s" -> -8;
            case "w" -> 1;
            case "e" -> -1;
            default -> throw new IllegalArgumentException("invalidCharacter");
        };
    }
}
