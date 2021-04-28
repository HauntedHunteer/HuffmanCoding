package huffman.controllers;

import huffman.model.HuffmanCode;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CalculationController {
    @FXML
    public Button encodeButton;
    @FXML
    public Button defaultTextButton1;
    @FXML
    public Button defaultTextButton2;
    @FXML
    public TextArea inputText;
    @FXML
    public TextArea outputText;
    @FXML
    public Label infLabel;
    @FXML
    public TextField entropyTextField;
    @FXML
    public TextField averageWordLengthTextField;

    @FXML
    public void setDefaultText1(Event event) {
        inputText.setText("""
                She sells seashells by the seashore,
                The shells she sells are seashells, I'm sure.
                So if she sells seashells on the seashore,
                Then I'm sure she sells seashore shells.""");
        outputText.setText("");
        System.out.println("Default text 1 set");
    }
    @FXML
    public void setDefaultText2(Event event) {
        inputText.setText("Mateusz Niemczuk teleinformatyka");
        outputText.setText("");
        System.out.println("Default text 2 set");
    }

    @FXML
    public void encodeText(Event event) {
        if (inputText.getText().trim().equals("")) {
            infLabel.setText("Proszę wporadzić tekst");
        }
        else {
            HuffmanCode huffmanCode = new HuffmanCode(inputText.getText());
            double entropy = huffmanCode.calculateEntropy();
            double averageWordLength = huffmanCode.calculateAverageWordLength();

            infLabel.setText("");
            outputText.setText(huffmanCode.getEncodedText());
            entropyTextField.setText(String.valueOf(entropy));
            averageWordLengthTextField.setText(String.valueOf(averageWordLength));

        }
    }
}
