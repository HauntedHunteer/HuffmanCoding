package huffman.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class MainLayoutController {
    @FXML
    private VBox mainLayout;
    @FXML
    private Button coding;
    @FXML
    private Button graph;
    @FXML
    private StackPane subLayout;

    public void setLayout(Node node) {
        subLayout.getChildren().setAll(node);
    }

    @FXML
    public void setCalculationScreen(Event event) {
        LayoutNavigator.loadSubLayout(LayoutNavigator.CALC);
    }

    @FXML
    public void setGraphScreen(Event event) {
        LayoutNavigator.loadSubLayout(LayoutNavigator.GRAPH);
    }
}
