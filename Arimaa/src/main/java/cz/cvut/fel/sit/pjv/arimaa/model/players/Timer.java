package cz.cvut.fel.sit.pjv.arimaa.model.players;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
public class Timer extends Thread {
    protected Board board;
    public Label goldenPlayerTimer;
    public Label silverPlayerTimer;
    public int goldenPlayerTime = 0;
    public int silverPlayerTime = 0;
    private Player goldenPlayer;
    private Player silverPlayer;
    private Player currentPlayer;
    public Timer(Board board) {
        this.board = board;
        setGoldenPlayer(board.getGoldenPlayer());
        setSilverPlayer(board.getSilverPlayer());
        setCurrentPlayer(board.getCurrentPlayer());
        this.goldenPlayerTimer = setPlayerTimer("g");
        this.silverPlayerTimer = setPlayerTimer("s");
        start();
    }
    @Override
    public void run(){
        while (!board.gameEnded) {
            if (getCurrentPlayer().equals(getGoldenPlayer())) {
                Platform.runLater(() -> {
                    goldenPlayerTimer.setText(getFormattedTime(++goldenPlayerTime));
                });

            }
            else if (getCurrentPlayer().equals(getSilverPlayer())) {
                Platform.runLater(() -> {
                    silverPlayerTimer.setText(getFormattedTime(++silverPlayerTime));
                });
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){

            }
        }
    }
    public void setPlayers(Board board){
        this.setGoldenPlayer(board.getGoldenPlayer());
        this.setSilverPlayer(board.getSilverPlayer());
        this.setCurrentPlayer(board.getCurrentPlayer());
    }
    private Label setLabel() {
        Label timerLabel = new Label("0:00");
        timerLabel.setPadding(new Insets(10));
        timerLabel.setAlignment(Pos.BOTTOM_CENTER);
        return timerLabel;
    }
    private String getFormattedTime(int playerTime) {
        int minutes = playerTime / 60;
        int seconds = playerTime % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
    public Label setPlayerTimer(String player){
        Label timerLabel = setLabel();
        if (getCurrentPlayer().equals(getGoldenPlayer()) && player.equals("g")) {
            timerLabel.setText(getFormattedTime(goldenPlayerTime));
        }
        else if (getCurrentPlayer().equals(getSilverPlayer()) && player.equals("s")) {
            timerLabel.setText(getFormattedTime(silverPlayerTime));
        }
        return timerLabel;
    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getGoldenPlayer() {
        return goldenPlayer;
    }

    public void setGoldenPlayer(Player goldenPlayer) {
        this.goldenPlayer = goldenPlayer;
    }

    public Player getSilverPlayer() {
        return silverPlayer;
    }

    public void setSilverPlayer(Player silverPlayer) {
        this.silverPlayer = silverPlayer;
    }
}
