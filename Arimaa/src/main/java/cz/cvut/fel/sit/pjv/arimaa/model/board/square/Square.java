package cz.cvut.fel.sit.pjv.arimaa.model.board.square;

import cz.cvut.fel.sit.pjv.arimaa.model.board.BoardUtils;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Square {
    protected final int squareLocation;
    private static final Map<Integer, EmptySquare> Empty_Squares = createAllPossibleEmptySquares();
    Square(final int squareLocation){
        this.squareLocation = squareLocation;
    }

    private static Map<Integer, EmptySquare> createAllPossibleEmptySquares() {
        final  Map<Integer, EmptySquare> emptySquareMap = new HashMap<>();

        for(int i = 0; i < BoardUtils.Num_Squares; i++) {
            emptySquareMap.put(i, new EmptySquare(i));
        }

        return Collections.unmodifiableMap(emptySquareMap);
    }
    public static Square createSquare(final int squareLocation, final Piece pieceOnSquare){
        return pieceOnSquare != null ? new OccupiedSquare(squareLocation, pieceOnSquare) : Empty_Squares.get(squareLocation);
    }
    public abstract boolean isSquareOccupied();
    public abstract Piece getPieceOnSquare();
}

