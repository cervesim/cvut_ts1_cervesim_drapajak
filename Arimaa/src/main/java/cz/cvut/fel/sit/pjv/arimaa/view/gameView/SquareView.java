package cz.cvut.fel.sit.pjv.arimaa.view.gameView;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SkipTurnsMove;
import cz.cvut.fel.sit.pjv.arimaa.model.board.square.Square;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.PieceType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class SquareView{
    int squarePosition;
    Board board;
    public SquareView(int squarePosition, Board board) {
        this.squarePosition = squarePosition;
        this.board = board;
    }

    public StackPane setSquare() {
        Rectangle cell = new Rectangle(50, 50);
        Square currentSquare = board.getSquare(squarePosition);
        cell.setFill((squarePosition == 18 || squarePosition == 21 || squarePosition == 42 || squarePosition == 45)
                ? Color.BLACK : Color.WHITE);
        cell.setUserData(squarePosition);

        if (currentSquare.isSquareOccupied()) {
            Image image = getPieceImage(currentSquare.getPieceOnSquare().getPieceType(), currentSquare.getPieceOnSquare().getPieceColor());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);

            StackPane stackPane = new StackPane(cell, imageView);
            setSquareOnMouseClick(stackPane, cell);
            return stackPane;
        }

        StackPane stackPane = new StackPane(cell);
        setSquareOnMouseClick(stackPane, cell);
        return stackPane;
    }

    private void setSquareOnMouseClick(StackPane stackPane, Rectangle cell) {
        stackPane.setOnMouseClicked(e -> {
            int clickCount = ++BoardView.clickCount;
            if (e.getButton() == MouseButton.PRIMARY) {
                if (clickCount == 1) {
                    BoardView.firstClickedSquare = board.getSquare(squarePosition);
                    if (!BoardView.firstClickedSquare.isSquareOccupied()) {
                        BoardView.clickCount = 0;
                        cell.setFill((squarePosition == 18 || squarePosition == 21 || squarePosition == 42 || squarePosition == 45)
                                ? Color.BLACK : Color.WHITE);
                    } else {
                        cell.setFill(Color.RED);
                    }
                } else if (clickCount == 2) {
                    BoardView.secondClickedSquare = board.getSquare(squarePosition);
                    if (!BoardView.secondClickedSquare.isSquareOccupied()) {
                        Move move = board.getCurrentPlayer().createMove(BoardView.firstClickedSquare.getPieceOnSquare(),
                                null, BoardView.secondClickedSquare.getSquareLocation());
                        if (board.getCurrentPlayer().isMoveLegal(move)) {
                            GameView gameView = new GameView(GameView.mainWindow, move.execute());
                            GameView.mainWindow.setScene(gameView.display());
                            BoardView.clickCount = 0;
                            cell.setFill((squarePosition == 18 || squarePosition == 21 || squarePosition == 42 || squarePosition == 45)
                                    ? Color.BLACK : Color.WHITE);
                        } else {
                            cell.setFill((squarePosition == 18 || squarePosition == 21 || squarePosition == 42 || squarePosition == 45)
                                    ? Color.BLACK : Color.WHITE);
                        }
                    } else {
                        cell.setFill(Color.RED);
                    }
                } else if (clickCount == 3) {
                    BoardView.thirdClickedSquare = board.getSquare(squarePosition);
                    if (!BoardView.thirdClickedSquare.isSquareOccupied()) {
                        Move move = board.getCurrentPlayer().createMove(BoardView.firstClickedSquare.getPieceOnSquare(),
                                BoardView.secondClickedSquare.getPieceOnSquare(),
                                BoardView.thirdClickedSquare.getSquareLocation());
                        if (board.getCurrentPlayer().isMoveLegal(move)) {
                            GameView gameView = new GameView(GameView.mainWindow, move.execute());
                            GameView.mainWindow.setScene(gameView.display());
                            BoardView.clickCount = 0;
                            cell.setFill((squarePosition == 18 || squarePosition == 21 || squarePosition == 42 || squarePosition == 45)
                                    ? Color.BLACK : Color.WHITE);
                        } else {
                            cell.setFill((squarePosition == 18 || squarePosition == 21 || squarePosition == 42 || squarePosition == 45)
                                    ? Color.BLACK : Color.WHITE);
                        }
                    } else {
                        cell.setFill(Color.RED);
                    }
                    BoardView.clickCount = 0;
                }
            } else if (e.getButton() == MouseButton.SECONDARY) {
                if (e.getClickCount() == 1){
                    BoardView.clickCount = 0;
                    cell.setFill((squarePosition == 18 || squarePosition == 21 || squarePosition == 42 || squarePosition == 45)
                            ? Color.BLACK : Color.WHITE);
                } else if (e.getClickCount() == 3 && board.getMoveCount() >= 1) {
                    System.out.println("round skipped");
                    Move skipTurnsMove = new SkipTurnsMove(board, null, 0);
                    GameView gameView = new GameView(GameView.mainWindow, skipTurnsMove.execute());
                    GameView.mainWindow.setScene(gameView.display());
                    BoardView.clickCount = 0;
                }

            }
        });
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
