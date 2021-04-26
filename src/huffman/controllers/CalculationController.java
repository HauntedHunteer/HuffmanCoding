package huffman.controllers;

import huffman.model.HuffmanCode;
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
        plainText.setText("""
                She sells seashells by the seashore,
                The shells she sells are seashells, I'm sure.
                So if she sells seashells on the seashore,
                Then I'm sure she sells seashore shells.""");
        System.out.println("Default text set");
    }

    @FXML
    public void encodeText(Event event) {
        if (plainText.getText().trim().equals("")) {
            infLabel.setText("Puste pole !!! Proszę wporadzić tekst");
        }
        else {
            HuffmanCode huffmanCode = new HuffmanCode(plainText.getText());
            encoded.setText(huffmanCode.getEncodedText());
            System.out.println(huffmanCode.calculateEntropy());
            System.out.println(huffmanCode.calculateAverageWordLength());
        }
    }
}
