package cz.cvut.fel.sit.pjv.arimaa.controller;

import cz.cvut.fel.sit.pjv.arimaa.model.Alliance;
import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Move;
import cz.cvut.fel.sit.pjv.arimaa.model.board.moves.Push;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.PieceType;


public class GameController{

    public static void main(String[] args){
        Board board = Board.createTestBoard();
        Piece piece1 = new Piece(Alliance.GOLDEN, PieceType.ELEPHANT, 52);
        Piece piece2 = new Piece(Alliance.SILVER, PieceType.ELEPHANT, 11);


        Move move = new Push(board, piece1, piece2, 44);
        System.out.println(board);
        System.out.println(move.execute());

    }
}
