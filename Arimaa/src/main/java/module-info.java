module cz.cvut.fel.sit.pjv.arimaa.arimaa {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
                    requires com.almasb.fxgl.all;
    
    opens cz.cvut.fel.sit.pjv.arimaa to javafx.fxml;
    exports cz.cvut.fel.sit.pjv.arimaa;
    exports cz.cvut.fel.sit.pjv.arimaa.controller;
    opens cz.cvut.fel.sit.pjv.arimaa.controller to javafx.fxml;
    exports cz.cvut.fel.sit.pjv.arimaaInterfaces.controllerInterface;
    opens cz.cvut.fel.sit.pjv.arimaaInterfaces.controllerInterface to javafx.fxml;
}