package cz.cvut.fel.sit.pjv.arimaa.view.utils;

import cz.cvut.fel.sit.pjv.arimaa.model.board.Board;
import cz.cvut.fel.sit.pjv.arimaa.model.pieces.Piece;
import cz.cvut.fel.sit.pjv.arimaa.view.gameView.BoardView;
import cz.cvut.fel.sit.pjv.arimaa.view.setupGameView.SetupGameView;
import cz.cvut.fel.sit.pjv.arimaa.view.gameView.GameView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainSceneView {
    Stage mainWindow;
    public MainSceneView(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    public Scene display() {
        /*TOP menu*/
        Button gameSettingsButton = new Button("Settings");
        gameSettingsButton.setOnAction(e -> SettingsStageView.display());
        VBox topSettingsButton = new VBox(gameSettingsButton);
        topSettingsButton.setPadding(new Insets(2, 5, 2, 2));
        topSettingsButton.setAlignment(Pos.TOP_RIGHT);
        /*TOP menu*/
        Label MenuLabel = new Label("Arimaa Game"); /*Label*/
        /*NormalGameButton*/
        Button startSimpleGameButton = new Button("Start classic game");
        startSimpleGameButton.setOnAction(e -> {
            SetupGameView setupGameView = new SetupGameView(mainWindow);
            mainWindow.setScene(setupGameView.display());
        });
        /*NormalGameButton*/
        /*GameAgainstComputer*/
        Button startGameAgainstComputer = new Button("Start game against computer");
        startGameAgainstComputer.setOnAction(e -> {
            Board board = Board.createBoardWithSilverPiecesSet();
            SetupGameView setupGameWithBotView = new SetupGameView(mainWindow, board,
                    board.getGoldenPlayer().getAllAvailablePieces(),
                    new ArrayList<>());
            mainWindow.setScene(setupGameWithBotView.display());
        });
        /*GameAgainstComputer*/
        /*TestGameButton*/
        Button startTestGameButton = new Button("Start test game");
        startTestGameButton.setOnAction(e -> {
            GameView gameView = new GameView(mainWindow);
            mainWindow.setScene(gameView.display());
        });
        /*TestGameButton*/
        /*ReplayGameHistoryButton*/
        Button replayGameHistoryButton = new Button("Replay history from file");
        replayGameHistoryButton.setOnAction(e -> {
            chooseTextToFile();
        });
        /*ReplayGameHistoryButton*/

        VBox centerMenu = new VBox(10, MenuLabel, startSimpleGameButton, startGameAgainstComputer, startTestGameButton, replayGameHistoryButton);
        centerMenu.setAlignment(Pos.CENTER);
        BorderPane borderPane = new BorderPane(centerMenu, topSettingsButton, null, null, null);
        return new Scene(borderPane, 720, 600);
    }
    private void readFile(File selectedFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(selectedFile);
        ArrayList<Piece> initialSetup = new ArrayList<>();
        ArrayList<String> movesHistory = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            String piece = scanner.nextLine();
            initialSetup.add(Board.decodePieceToSet(piece));
        }
        Board board = Board.createBoardUsingArray(initialSetup);
        while (scanner.hasNextLine()){
            String move = scanner.nextLine();
            movesHistory.add(move);
        }
        board.setMovesHistory(movesHistory);
        GameView gameView = new GameView(mainWindow, board);
        BoardView.howFarInPast = -movesHistory.size();
        gameView.gameEnded = true;
        GameView.inViewMode = true;
        GameView.fileSaved = true;
        mainWindow.setScene(gameView.display());
    }

    private void chooseTextToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\simon\\CVUT\\Summer_2023\\PJV_S_2023\\Arimaa\\Arimaa\\src\\main\\ArimaaGameHistory"));
        fileChooser.setTitle("Select game you want to see");
        File selectedFile = fileChooser.showOpenDialog(mainWindow);
        if (selectedFile != null) {
            try {
                readFile(selectedFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
