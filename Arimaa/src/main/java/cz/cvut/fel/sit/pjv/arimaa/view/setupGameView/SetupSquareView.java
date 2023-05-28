package cz.cvut.fel.sit.pjv.arimaa.view.setupGameView;

import cz.cvut.fel.sit.pjv.arimaa.controller.MouseClickController;
import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SetupMove;
import cz.cvut.fel.sit.pjv.arimaa.model.board.square.Square;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.Objects;

public class SetupSquareView{
    int squarePosition;
    Stage mainWindow;
    Board board;
    Collection<Piece> goldenPieces;
    Collection<Piece> silverPieces;
    public SetupSquareView(int squarePosition, Stage mainWindow, Board board, Collection<Piece> goldenPieces, Collection<Piece> silverPieces) {
        this.squarePosition = squarePosition;
        this.mainWindow = mainWindow;
        this.board = board;
        this.goldenPieces = goldenPieces;
        this.silverPieces = silverPieces;
    }

    public StackPane setSquare() {
        Rectangle cell = new Rectangle(50, 50);
        Square currentSquare = board.getSquare(squarePosition);
        cell.setFill(MouseClickController.isTrapSquare(squarePosition)
                ? Color.BLACK : Color.WHITE);

        if (currentSquare.isSquareOccupied()) {
            Image image = getPieceImage(currentSquare.getPieceOnSquare().getPieceType(), currentSquare.getPieceOnSquare().getPieceColor());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);

            StackPane stackPane = new StackPane(cell, imageView);
            return stackPane;
        }

        StackPane stackPane = new StackPane(cell);
        stackPane.setOnMouseClicked(e->{
            if (e.getButton() == MouseButton.PRIMARY){
                Piece pieceToSet = SetupGameView.pieceToSet;
                if(pieceToSet != null){
                    if(!currentSquare.isSquareOccupied()){

                        if(pieceToSet.getPieceColor() == Alliance.GOLDEN){
                            goldenPieces.remove(pieceToSet);
                        }else silverPieces.remove(pieceToSet);

                        Move setupMove = new SetupMove(board, pieceToSet, squarePosition);
                        SetupGameView.pieceToSet = null;
                        SetupGameView setupGameView = new SetupGameView(mainWindow, setupMove.execute(), goldenPieces, silverPieces);
                        mainWindow.setScene(setupGameView.display());
                    }
                }
            }
        });
        return stackPane;
    }

    private Image getPieceImage(PieceType pieceType, Alliance alliance) {
        String imagePath;
        switch (pieceType) {
            case ELEPHANT -> {
                if (alliance == Alliance.GOLDEN) {
                    imagePath = "/pieceImages/goldenElephant.png";
                } else {
                    imagePath = "/pieceImages/silverElephant.png";
                }
            }
            case CAMEL -> {
                if (alliance == Alliance.GOLDEN) {
                    imagePath = "/pieceImages/goldenCamel.png";
                } else {
                    imagePath = "/pieceImages/silverCamel.png";
                }
            }
            case HORSE -> {
                if (alliance == Alliance.GOLDEN) {
                    imagePath = "/pieceImages/goldenHorse.png";
                } else {
                    imagePath = "/pieceImages/silverHorse.gif";
                }
            }
            case DOG -> {
                if (alliance == Alliance.GOLDEN) {
                    imagePath = "/pieceImages/goldenDog.png";
                } else {
                    imagePath = "/pieceImages/silverDog.png";
                }
            }
            case CAT -> {
                if (alliance == Alliance.GOLDEN) {
                    imagePath = "/pieceImages/goldenCat.png";
                } else {
                    imagePath = "/pieceImages/silverCat.png";
                }
            }
            case RABBIT -> {
                if (alliance == Alliance.GOLDEN) {
                    imagePath = "/pieceImages/goldenRabbit.png";
                } else {
                    imagePath = "/pieceImages/silverRabbit.png";
                }
            }
            default -> {
                return null;
            }
        }
        return new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
    }
}