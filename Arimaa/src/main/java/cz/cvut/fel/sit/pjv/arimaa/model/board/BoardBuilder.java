package cz.cvut.fel.sit.pjv.arimaa.model.board;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardBuilder {
    public ArrayList<String> goldenInitialSetup;
    public ArrayList<String> goldenMoves;
    public ArrayList<String>  silverInitialSetup;
    public ArrayList<String> silverMoves;
    public Map<Integer, Piece> boardConfig;
    private Alliance nextMoveMaker;
    private int moveCount;
    public BoardBuilder(){
        this.boardConfig = new HashMap<>();
        this.goldenInitialSetup = new ArrayList<>();
        this.silverInitialSetup = new ArrayList<>();
    }

    public void setPiece (final Piece piece){
        this.boardConfig.put(piece.getPiecePosition(), piece);
    }
    public void setMoveMaker(final Alliance nextMoveMaker){
        this.nextMoveMaker = nextMoveMaker;
    }
    public Board build() {
        return new Board(this);
    }
    public Alliance getNextMoveMaker() {
        return nextMoveMaker;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }
    public ArrayList<String> getGoldenInitialSetup() {
        return goldenInitialSetup;
    }

    public void setGoldenInitialSetup(ArrayList<String> oldArray, String newSetPiece) {
        if (oldArray != null) this.goldenInitialSetup.addAll(oldArray);
        if (newSetPiece != null || oldArray.size() != 16) this.goldenInitialSetup.add(newSetPiece);
    }

    public ArrayList<String> getGoldenMoves() {
        return goldenMoves;
    }

    public void setGoldenMoves(String goldenMoves) {
        this.goldenMoves.add(goldenMoves);
    }

    public ArrayList<String> getSilverInitialSetup() {
        return silverInitialSetup;
    }

    public void setSilverInitialSetup(ArrayList<String> oldArray, String newSetPiece) {
        if (oldArray != null) this.silverInitialSetup.addAll(oldArray);
        if (newSetPiece != null || oldArray.size() != 16) this.silverInitialSetup.add(newSetPiece);

    }

    public ArrayList<String> getSilverMoves() {
        return silverMoves;
    }

    public void setSilverMoves(String silverMoves) {
        this.silverMoves.add(silverMoves);
    }
}
