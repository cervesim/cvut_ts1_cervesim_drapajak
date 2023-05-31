package cz.cvut.fel.sit.pjv.arimaa.controller;


import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.SimpleMove;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.PieceType;

public class GameController{
    public static void main(String[] args) {
        /*TODO timer for each player*/
        /*TODO random move generator to play against*/
        /*TODO Notation*/
        /*TODO gameHistory*/
        /*TODO javaDoc*/
        /*TODO loggers*/

        Board board = Board.createTestBoard();
        System.out.println(board.toString());
        Move move = new SimpleMove(board, new Piece(Alliance.GOLDEN, PieceType.DOG, 53), 45);
        System.out.println(move);
        System.out.println(move.execute().toString());
    }
}
