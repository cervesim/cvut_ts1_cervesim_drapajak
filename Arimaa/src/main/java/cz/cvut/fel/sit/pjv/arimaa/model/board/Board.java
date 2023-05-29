package cz.cvut.fel.sit.pjv.arimaa.model.board;

import com.google.common.collect.ImmutableList;
import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.square.Square;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.PieceType;
import cz.cvut.fel.sit.pjv.arimaa.model.players.GoldenPlayer;
import cz.cvut.fel.sit.pjv.arimaa.model.players.Player;
import cz.cvut.fel.sit.pjv.arimaa.model.players.SilverPlayer;

import java.util.*;

public class Board {
    public static final int Num_Squares = 64;
    public static final int Num_Squares_Per_Row = 8;
    public static final boolean[] First_Column = initColumn(0);
    public static final boolean[] Eighth_Column = initColumn(7);
    private final List<Square> gameBoard;
    private final Collection<Piece> goldenPieces;
    private final Collection<Piece> silverPieces;
    private final GoldenPlayer goldenPlayer;
    private final SilverPlayer silverPlayer;
    private final Player currentPlayer;
    private final int moveCount;
    public final boolean gameEnded;
    public Board(BoardBuilder boardBuilder) {
        this.gameBoard = createGameBoard(boardBuilder);
        setTrapSquares();
        this.goldenPieces = setActivePieces(this.gameBoard, Alliance.GOLDEN);
        this.silverPieces = setActivePieces(this.gameBoard, Alliance.SILVER);
        this.moveCount = boardBuilder.getMoveCount();

        final Collection<Move> goldenStandardLegalMoves = getLegalMoves(this.goldenPieces);
        final Collection<Move> silverStandardLegalMoves = getLegalMoves(this.silverPieces);

        this.goldenPlayer = new GoldenPlayer(this, goldenStandardLegalMoves, silverStandardLegalMoves);
        this.silverPlayer = new SilverPlayer(this, silverStandardLegalMoves, goldenStandardLegalMoves);

        this.currentPlayer = boardBuilder.getNextMoveMaker().choosePlayer(this.goldenPlayer, this.silverPlayer);
        this.gameEnded = hasWon() != null;
    }
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Num_Squares; i++){
            final String squareText = this.gameBoard.get(i).toString();
            builder.append(String.format("%3s", squareText));
            if ((i + 1) % Num_Squares_Per_Row == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
    public boolean isValidSquareNumber(int squareNumber) {
        return squareNumber >= 0 && squareNumber < Num_Squares;
    }
    private static boolean[] initColumn(int columnNumber) {
        final boolean[] column = new boolean[64];
        do {
            column[columnNumber] = true;
            columnNumber += Num_Squares_Per_Row;
        } while (columnNumber < Num_Squares);
        return column;
    }
    public Player hasWon (){
        Piece possibleGoldenRabbitTheChampion = goldenPlayer.rabbitFinishedHisJourney();
        Piece possibleSilverRabbitTheChampion = silverPlayer.rabbitFinishedHisJourney();
        if (silverPlayer.getLegalMoves().isEmpty() ||
                silverPlayer.getRabbits().isEmpty() ||
                (possibleGoldenRabbitTheChampion != null) &&
                        (silverPlayer != currentPlayer || silverPlayer.getMoveCount() < 2 &&
                                possibleGoldenRabbitTheChampion.isFrozen(this))) {
            return goldenPlayer;
        } else if (goldenPlayer.getLegalMoves().isEmpty() ||
                goldenPlayer.getRabbits().isEmpty() ||
                possibleSilverRabbitTheChampion != null &&
                        (goldenPlayer != currentPlayer || goldenPlayer.getMoveCount() < 2 &&
                                possibleSilverRabbitTheChampion.isFrozen(this))) {
            return silverPlayer;
        } else return null;
    }
    public void setTrapSquares (){
        int[] trapSquarePositions = new int[]{18, 21, 42, 45};
        for (int trapSquarePosition : trapSquarePositions) {
            Square trapSquare = gameBoard.get(trapSquarePosition);
            if (trapSquare.isSquareOccupied()){
                if (!trapSquare.isSupported(this)){
                    trapSquare.setPieceOnSquare(null);
                }
            }
        }
    }
    private Collection<Move> getLegalMoves(Collection<Piece> pieces) {
        final  List<Move> legalMoves = new ArrayList<>();
        for(final Piece piece : pieces) {
            Collection<Move> pieceLegalMoves = piece.getLegalMoves(this);
            if(!pieceLegalMoves.isEmpty()) {
                legalMoves.addAll(pieceLegalMoves);
            }
        }
        return List.copyOf(legalMoves);
    }
    private static List<Square> createGameBoard (final BoardBuilder boardBuilder){
        final Square[] squares = new Square[Num_Squares];
        for (int i = 0; i < Num_Squares; i++){
            squares[i] = Square.createSquare(i, boardBuilder.boardConfig.get(i));
        }
        return List.of(squares);
    }
    public static Board createTestBoard () {final BoardBuilder boardBuilder = new BoardBuilder();

        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 0));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 1));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 2));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 3));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 4));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 5));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 6));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.RABBIT, 7));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.HORSE, 8));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.DOG, 9));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.CAT, 10));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.ELEPHANT, 11));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.CAMEL, 12));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.DOG, 13));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.CAT, 14));
        boardBuilder.setPiece(new Piece(Alliance.SILVER, PieceType.HORSE, 15));


        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.HORSE, 48));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.DOG, 49));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.CAT, 50));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.CAMEL, 51));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.ELEPHANT, 52));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.DOG, 53));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.CAT, 54));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.HORSE, 55));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 56));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 57));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 58));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 59));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 60));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 61));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 62));
        boardBuilder.setPiece(new Piece(Alliance.GOLDEN, PieceType.RABBIT, 63));

        boardBuilder.setMoveMaker(Alliance.GOLDEN);
        boardBuilder.setMoveCount(0);

        return boardBuilder.build();
    }
    public static Board createEmptyBoard(){
        final BoardBuilder boardBuilder = new BoardBuilder();
        boardBuilder.setMoveMaker(Alliance.GOLDEN);
        boardBuilder.setMoveCount(0);

        return boardBuilder.build();
    }
    private static Collection<Piece> setActivePieces(final List<Square> gameBoard, Alliance alliance) {
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
    public Player getCurrentPlayer(){return this.currentPlayer;}
    public int getMoveCount(){return this.moveCount;}
    public GoldenPlayer getGoldenPlayer() {
        return this.goldenPlayer;
    }
    public SilverPlayer getSilverPlayer() {
        return this.silverPlayer;
    }
    public Collection<Piece> getGoldenPieces() {
        return this.goldenPieces;
    }
    public Collection<Piece> getSilverPieces() {
        return this.silverPieces;
    }
    public Square getSquare(final int squareCoordinate) {
        return gameBoard.get(squareCoordinate);
    }

}
