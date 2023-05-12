package cz.cvut.fel.sit.pjv.arimaa.controller;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;

public class GameController{

    public static void main(String[] args){
        Board board = Board.createTestBoard();

        System.out.println(board);
    }
}
