package huffman.controllers;

import huffman.model.HuffmanCode;
import huffman.model.HuffmanDataRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class CalculationController implements Initializable {
    private final ObservableList<HuffmanDataRow> tableDataList = FXCollections.observableArrayList();
    @FXML
    public Button encodeButton; // todo refactor this to private
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
    public TableView<HuffmanDataRow> codingTableView;
    @FXML
    public TableColumn<HuffmanDataRow, String> character;
    @FXML
    public TableColumn<HuffmanDataRow, String> frequency;
    @FXML
    public TableColumn<HuffmanDataRow, String> huffCode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        character.setCellValueFactory(new PropertyValueFactory<>("character"));
        frequency.setCellValueFactory(new PropertyValueFactory<>("frequency"));
        huffCode.setCellValueFactory(new PropertyValueFactory<>("huffCode"));
    }

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

            codingTableView.getItems().clear();
            codingTableView.setItems(tableDataList);

            for (Map.Entry<Character, String> entry : huffmanCode.getLettersWithCode().entrySet()) {
                tableDataList.add(
                        new HuffmanDataRow(
                                String.valueOf(entry.getKey()),
                                String.valueOf(huffmanCode
                                        .getCharacterWithFrequency()
                                        .get(entry.getKey())),
                                entry.getValue()
                        )
                );
            }

            infLabel.setText("");
            outputText.setText(huffmanCode.getEncodedText());
            entropyTextField.setText(String.valueOf(entropy));
            averageWordLengthTextField.setText(String.valueOf(averageWordLength));

        }
    }
}
