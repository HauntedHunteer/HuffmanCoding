package huffman.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class CalculationController {
    @FXML
    public Button encode;
    @FXML
    public Button defaultText;
    @FXML
    public TextArea plainText;
    @FXML
    public TextArea encoded;
    @FXML
    public Label infLabel;

    @FXML
    public void setDefaultText(Event event) {
        plainText.setText("She sells seashells by the seashore,\n" +
                "The shells she sells are seashells, I'm sure.\n" +
                "So if she sells seashells on the seashore,\n" +
                "Then I'm sure she sells seashore shells.");
        System.out.println("Default text set");
    }
}
