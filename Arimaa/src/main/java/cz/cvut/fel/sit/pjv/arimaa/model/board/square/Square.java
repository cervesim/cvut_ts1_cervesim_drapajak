package cz.cvut.fel.sit.pjv.arimaa.model.board.square;

import com.google.common.collect.ImmutableMap;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import java.util.HashMap;
import java.util.Map;

import static cz.cvut.fel.sit.pjv.arimaa.model.modelUtils.NotationUtils.isTrapSquare;

public abstract class Square {
    protected final int squareLocation;
    private static final Map<Integer, EmptySquare> Empty_Squares = createAllPossibleEmptySquares();
    Square(final int squareLocation){
        this.squareLocation = squareLocation;
    }
    private static Map<Integer, EmptySquare> createAllPossibleEmptySquares() {
        final  Map<Integer, EmptySquare> emptySquareMap = new HashMap<>();

        for(int i = 0; i < Board.Num_Squares; i++) {
            emptySquareMap.put(i, new EmptySquare(i));
        }

        return ImmutableMap.copyOf(emptySquareMap);
    }

    public static Square createSquare(final int squareLocation, final Piece pieceOnSquare){
        if (isTrapSquare(squareLocation)){
            return new TrapSquare(squareLocation, pieceOnSquare);
        }
        if (pieceOnSquare != null){
            return new OccupiedSquare(squareLocation, pieceOnSquare);
        } else return Empty_Squares.get(squareLocation);
    }
    public abstract boolean isSquareOccupied();
    public abstract Piece getPieceOnSquare();
    public int getSquareLocation() {
        return squareLocation;
    }
    public abstract boolean isSupported(Board board);
    public abstract void setPieceOnSquare(Piece piece);
}

