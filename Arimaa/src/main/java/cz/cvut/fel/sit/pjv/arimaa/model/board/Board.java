package cz.cvut.fel.sit.pjv.arimaa.model.board;

import com.google.common.collect.ImmutableList;
import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.square.Square;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.PieceType;

import java.util.*;

public class Board {
    private final List<Square> gameBoard;
    private final Collection<Piece> goldenPieces;
    private final Collection<Piece> silverPieces;
    private Board(Builder builder) {
        this.gameBoard = createGameBoard(builder);
        this.goldenPieces = getActivePieces(this.gameBoard, Alliance.GOLDEN);
        this.silverPieces = getActivePieces(this.gameBoard, Alliance.SILVER);

        final Collection<Move> goldenStandardLegalMoves = getAllLegalMoves(this.goldenPieces);
        final Collection<Move> silverStandardLegalMoves = getAllLegalMoves(this.silverPieces);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BoardUtils.Num_Squares; i++){
            final String squareText = this.gameBoard.get(i).toString();
            builder.append(String.format("%3s", squareText));
            if ((i + 1) % BoardUtils.Num_Squares_Per_Row == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private Collection<Move> getAllLegalMoves(Collection<Piece> pieces) {
        final  List<Move> legalMoves = new ArrayList<>();

        for(final Piece piece : pieces) {
            legalMoves.addAll(piece.getLegalMoves(this));
        }
        return ImmutableList.copyOf(legalMoves);
    }

    public Square getSquare(final int squareCoordinate) {
        return gameBoard.get(squareCoordinate);
    }

    private static List<Square> createGameBoard (final Builder builder){
        final Square[] squares = new Square[BoardUtils.Num_Squares];
        for (int i = 0; i < BoardUtils.Num_Squares; i++){
            squares[i] = Square.createSquare(i, builder.boardConfig.get(i));
        }
        return ImmutableList.copyOf(squares);
    }
    public static Board createTestBoard () {
        final Builder builder = new Builder();

        builder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 0));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 1));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 2));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 3));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 4));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 5));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 6));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 7));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.HORSE, 8));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.DOG, 9));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.CAT, 10));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.ELEPHANT, 11));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.CAMEL, 12));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.DOG, 13));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.CAT, 14));
        builder.setPiece(new Piece(Alliance.SILVER, PieceType.HORSE, 15));


        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.HORSE, 48));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.DOG, 49));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.CAT, 50));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.CAMEL, 51));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.ELEPHANT, 52));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.DOG, 53));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.CAT, 54));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.HORSE, 55));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 56));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 57));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 58));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 59));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 60));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 61));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 62));
        builder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 63));

        builder.setMoveMaker(Alliance.GOLDEN);

        return builder.build();
    }
    private static Collection<Piece> getActivePieces(final List<Square> gameBoard, Alliance alliance) {
        final List<Piece> activePieces = new ArrayList<>();

        for (final Square square : gameBoard){
            if(square.isSquareOccupied()) {
                final Piece piece = square.getPieceOnSquare();
                if (piece.getPieceColor() == alliance){
                    activePieces.add(piece);
                }
            }
        }
        return ImmutableList.copyOf(activePieces);
    }

    public static class Builder{
        Map<Integer, Piece> boardConfig;
        Alliance nextMoveMaker;

        public Builder(){
            this.boardConfig = new HashMap<>();
        }

        public Builder setPiece (final Piece piece){
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }
        public Builder setMoveMaker(final Alliance nextMoveMaker){
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }
        public Board build() {
            return new Board(this);
        }
    }
}
