package huffman;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class MainLayoutController {
    @FXML
    public VBox mainLayout;
    @FXML
    public Button coding;
    @FXML
    public Button graph;
    @FXML
    public StackPane subLayout;

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
