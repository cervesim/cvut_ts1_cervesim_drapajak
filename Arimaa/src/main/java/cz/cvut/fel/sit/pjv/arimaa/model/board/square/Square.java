package cz.cvut.fel.sit.pjv.arimaa.model.board.square;

import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Square {
    protected final int squareLocation;

    private static final Map<Integer, EmptySquare> Empty_Squares = createAllPossibleEmptySquares();

    private static Map<Integer, EmptySquare> createAllPossibleEmptySquares() {
        final  Map<Integer, EmptySquare> emptySquareMap = new HashMap<>();

        for(int i = 0; i < 64; i++) {
            emptySquareMap.put(i, new EmptySquare(i));
        }

        return Collections.unmodifiableMap(emptySquareMap);
    }
    public static Square createSquare(final int squareLocation, final Piece piece){
        return piece != null ? new OccupiedSquare(squareLocation, piece) : Empty_Squares.get(squareLocation);
    }
    Square(int squareLocation){
        this.squareLocation = squareLocation;
    }
    public abstract boolean isSquareOccupied();
    public abstract Piece getPieceOnSquare();
}

