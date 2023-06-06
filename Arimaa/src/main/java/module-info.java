module cz.cvut.fel.sit.pjv.arimaa.arimaa {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
                    requires com.almasb.fxgl.all;
    requires com.google.common;

    opens cz.cvut.fel.sit.pjv.arimaa to javafx.fxml;
    exports cz.cvut.fel.sit.pjv.arimaa;
    exports cz.cvut.fel.sit.pjv.arimaa.controller;
    opens cz.cvut.fel.sit.pjv.arimaa.controller to javafx.fxml;
    exports cz.cvut.fel.sit.pjv.arimaa.model.board;
    opens cz.cvut.fel.sit.pjv.arimaa.model.board to javafx.fxml;
    exports cz.cvut.fel.sit.pjv.arimaa.model;
    opens cz.cvut.fel.sit.pjv.arimaa.model to javafx.fxml;
    exports cz.cvut.fel.sit.pjv.arimaa.model.modelUtils;
    opens cz.cvut.fel.sit.pjv.arimaa.model.modelUtils to javafx.fxml;
    exports cz.cvut.fel.sit.pjv.arimaa.model.players;
    exports cz.cvut.fel.sit.pjv.arimaa.model.pieces;
    exports cz.cvut.fel.sit.pjv.arimaa.model.board.square;
    exports cz.cvut.fel.sit.pjv.arimaa.model.board.moves;
}