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
    private Button encodeButton;
    @FXML
    private Button defaultTextButton1;
    @FXML
    private Button defaultTextButton2;
    @FXML
    private TextArea inputText;
    @FXML
    private TextArea outputText;
    @FXML
    private Label infLabel;
    @FXML
    private TextField entropyTextField;
    @FXML
    private TextField averageWordLengthTextField;
    @FXML
    private TableView<HuffmanDataRow> codingTableView;
    @FXML
    private TableColumn<HuffmanDataRow, String> character;
    @FXML
    private TableColumn<HuffmanDataRow, String> frequency;
    @FXML
    private TableColumn<HuffmanDataRow, String> huffCode;

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

            for (Map.Entry<Character, String> entry : huffmanCode.getCharactersWithCode().entrySet()) {
                String inputCharacter = String.valueOf(entry.getKey());
                if (inputCharacter.equals(" ")) {
                    inputCharacter = "space";
                } else if (inputCharacter.equals("\n")) {
                    inputCharacter = "enter";
                } else if (inputCharacter.equals("\t")) {
                    inputCharacter = "tab";
                }
                tableDataList.add(
                        new HuffmanDataRow(
                                inputCharacter,
                                huffmanCode
                                        .getCharacterWithFrequency()
                                        .get(entry.getKey()),
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
