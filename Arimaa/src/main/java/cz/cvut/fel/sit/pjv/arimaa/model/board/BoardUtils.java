package cz.cvut.fel.sit.pjv.arimaa.model.board;

public class BoardUtils {
    public static final boolean[] First_Column = initColumn(0);
    public static final boolean[] Eight_Column = initColumn(7);

    public static final int Num_Squares = 64;
    public static final int Num_Squares_Per_Row = 8;
    private  BoardUtils(){
        throw new RuntimeException("Don't do this");
    }
    public static boolean isValidSquareCoordinate(int squareCoordinate) {
        return squareCoordinate >= 0 && squareCoordinate < Num_Squares;
    }

    private static boolean[] initColumn(int columnNumber) {

        final boolean[] column = new boolean[64];

        do {
            column[columnNumber] = true;
            columnNumber += Num_Squares_Per_Row;
        } while (columnNumber < Num_Squares);
        return column;
    }

}
