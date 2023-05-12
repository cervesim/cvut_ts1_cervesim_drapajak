package cz.cvut.fel.sit.pjv.arimaa.model.pieces;

public enum PieceType {
    ELEPHANT("E"),
    CAMEL("M"),
    HORSE("H"),
    DOG("D"),
    CAT("C"),
    RABBIT("R");

    private String pieceName;

    PieceType(String pieceName) {
        this.pieceName = pieceName;
    }

    @Override
    public String toString() {
        return this.pieceName;
    }
}
