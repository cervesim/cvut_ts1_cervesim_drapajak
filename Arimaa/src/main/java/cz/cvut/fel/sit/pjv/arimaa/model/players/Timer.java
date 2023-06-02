package cz.cvut.fel.sit.pjv.arimaa.model.players;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Timer extends ActionEvent {
    public Label goldenPlayerTimer;
    public Label silverPlayerTimer;
    public int goldenPlayerTime = 0;
    public int silverPlayerTime = 0;
    private Player goldenPlayer;
    private Player silverPlayer;
    private Player currentPlayer;
    public Timer(Board board) {
        setGoldenPlayer(board.getGoldenPlayer());
        setSilverPlayer(board.getSilverPlayer());
        setCurrentPlayer(board.getCurrentPlayer());
        this.goldenPlayerTimer = setPlayerTimer("g");
        this.silverPlayerTimer = setPlayerTimer("s");
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
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), second -> {
            if (getCurrentPlayer().equals(getGoldenPlayer()) && player.equals("g")) {
                goldenPlayerTime++;
                timerLabel.setText(getFormattedTime(goldenPlayerTime));
            }
            else if (getCurrentPlayer().equals(getSilverPlayer()) && player.equals("s")) {
                silverPlayerTime++;
                timerLabel.setText(getFormattedTime(silverPlayerTime));
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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
