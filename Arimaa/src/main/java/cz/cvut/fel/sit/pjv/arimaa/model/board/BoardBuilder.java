package cz.cvut.fel.sit.pjv.arimaa.model.board;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

public class BoardBuilder {
    public Map<Integer, Piece> boardConfig;
    private Alliance nextMoveMaker;
    private int moveCount;
    public BoardBuilder(){
        this.boardConfig = new HashMap<>();
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
}
