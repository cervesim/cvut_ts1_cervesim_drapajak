package cz.cvut.fel.sit.pjv.arimaa.view.gameView;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.PieceType;
import javafx.scene.image.Image;

import java.util.Objects;

public class PieceView {
    public static Image getPieceImage(PieceType pieceType, Alliance alliance) {
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
        return new Image(Objects.requireNonNull(PieceView.class.getResource(imagePath)).toExternalForm());
    }
}
