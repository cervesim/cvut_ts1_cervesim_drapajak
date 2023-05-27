package cz.cvut.fel.sit.pjv.arimaa.model.players;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Pull;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Push;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SimpleMove;
import cz.cvut.fel.sit.pjv.arimaa.model.board.square.Square;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.PieceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {
    protected final Board board;
    protected final List<Piece> playersRabbits;
    protected final Collection<Move> legalMoves;

    public Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentsMoves) {
        this.board = board;
        this.playersRabbits = getRabbits();
        this.legalMoves = legalMoves;
    }
    public Move createMove (Piece firstClickedPiece, Piece secondClickedPiece, int destinationSquare){
        if (secondClickedPiece != null){
            if (firstClickedPiece.getPieceColor() == board.getCurrentPlayer().getAlliance()){
                return new Push(board, firstClickedPiece, secondClickedPiece, destinationSquare);
            }else {
                return new Pull(board, secondClickedPiece, firstClickedPiece, destinationSquare);
            }
        }
        return new SimpleMove(board, firstClickedPiece, destinationSquare);
    }
    public boolean isMoveLegal(final Move move) {
        return this.legalMoves.contains(move);
    }
    public boolean hasLost(){
        return false; /*TODO implement this or something like this*/
    }
    private List<Piece> getRabbits() {
        List<Piece> playersRabbits = new ArrayList<>();
        for(final Piece piece : getActivePieces()){
            if(piece.getPieceType() == PieceType.RABBIT){
                playersRabbits.add(piece);
            }
        }
        return playersRabbits; /*TODO return gameEnded() method or something like that*/
    }
    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }
    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();

}
