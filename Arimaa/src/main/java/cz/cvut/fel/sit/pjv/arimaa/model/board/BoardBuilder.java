package cz.cvut.fel.sit.pjv.arimaa.model.board;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardBuilder {
    private int roundCounter;
    private ArrayList<String> initialSetup;
    private ArrayList<String> movesHistory;
    public Map<Integer, Piece> boardConfig;
    private Alliance nextMoveMaker;
    private int moveCount;
    public BoardBuilder(){
        this.boardConfig = new HashMap<>();
        this.setInitialSetup(new ArrayList<>());
        this.setMovesHistory(new ArrayList<>());
        this.setRoundCounter(2);
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

    public void setNewInitialSetup(ArrayList<String> oldInitialSetup, String newSetPiece){
        if (newSetPiece != null){
            oldInitialSetup.add(newSetPiece);
            setInitialSetup(oldInitialSetup);
        }
    }
    public ArrayList<String> getInitialSetup() {
        return initialSetup;
    }

    public void setInitialSetup(ArrayList<String> initialSetup) {
        this.initialSetup = initialSetup;
    }

    public ArrayList<String> getMovesHistory() {
        return movesHistory;
    }

    public void setMovesHistory(ArrayList<String> movesHistory) {
        this.movesHistory = movesHistory;
    }
    public void setNewMovesHistory(ArrayList<String> oldMoveHistory, String newNotation) {
        oldMoveHistory.add(newNotation);
        setMovesHistory(oldMoveHistory);
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }
}
