module cz.cvut.fel.sit.pjv.arimaa.arimaa {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
                    requires com.almasb.fxgl.all;
    
    opens cz.cvut.fel.sit.pjv.arimaa.arimaa to javafx.fxml;
    exports cz.cvut.fel.sit.pjv.arimaa.arimaa;
}