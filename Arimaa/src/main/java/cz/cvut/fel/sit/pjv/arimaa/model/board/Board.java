package cz.cvut.fel.sit.pjv.arimaa.model.board;

import com.google.common.collect.ImmutableList;
import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.square.Square;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.OtherPieces;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Rabbit;

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

        builder.setPiece(new OtherPieces(0, Alliance.SILVER));
        builder.setPiece(new OtherPieces(1, Alliance.SILVER));
        builder.setPiece(new OtherPieces(2, Alliance.SILVER));
        builder.setPiece(new OtherPieces(3, Alliance.SILVER));
        builder.setPiece(new OtherPieces(4, Alliance.SILVER));
        builder.setPiece(new OtherPieces(5, Alliance.SILVER));
        builder.setPiece(new OtherPieces(6, Alliance.SILVER));
        builder.setPiece(new OtherPieces(7, Alliance.SILVER));
        builder.setPiece(new Rabbit(8, Alliance.SILVER));
        builder.setPiece(new Rabbit(9, Alliance.SILVER));
        builder.setPiece(new Rabbit(10, Alliance.SILVER));
        builder.setPiece(new Rabbit(11, Alliance.SILVER));
        builder.setPiece(new Rabbit(12, Alliance.SILVER));
        builder.setPiece(new Rabbit(13, Alliance.SILVER));
        builder.setPiece(new Rabbit(14, Alliance.SILVER));
        builder.setPiece(new Rabbit(15, Alliance.SILVER));

        builder.setPiece(new Rabbit(48, Alliance.GOLDEN));
        builder.setPiece(new Rabbit(49, Alliance.GOLDEN));
        builder.setPiece(new Rabbit(50, Alliance.GOLDEN));
        builder.setPiece(new Rabbit(51, Alliance.GOLDEN));
        builder.setPiece(new Rabbit(52, Alliance.GOLDEN));
        builder.setPiece(new Rabbit(53, Alliance.GOLDEN));
        builder.setPiece(new Rabbit(54, Alliance.GOLDEN));
        builder.setPiece(new Rabbit(55, Alliance.GOLDEN));
        builder.setPiece(new OtherPieces(56, Alliance.GOLDEN));
        builder.setPiece(new OtherPieces(57, Alliance.GOLDEN));
        builder.setPiece(new OtherPieces(58, Alliance.GOLDEN));
        builder.setPiece(new OtherPieces(59, Alliance.GOLDEN));
        builder.setPiece(new OtherPieces(60, Alliance.GOLDEN));
        builder.setPiece(new OtherPieces(61, Alliance.GOLDEN));
        builder.setPiece(new OtherPieces(62, Alliance.GOLDEN));
        builder.setPiece(new OtherPieces(63, Alliance.GOLDEN));

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
