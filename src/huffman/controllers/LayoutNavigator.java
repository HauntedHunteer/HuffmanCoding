package huffman.controllers;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Objects;

public class LayoutNavigator {
    public static final String MAIN = "../resources/MainLayout.fxml";
    public static final String CALC = "../../resources/CalculationLayout.fxml";
    public static final String GRAPH = "../../resources/GraphLayout.fxml";

    private static MainLayoutController mainLayoutController;

    public static void setMainLayoutController(MainLayoutController mainLayoutController) {
        LayoutNavigator.mainLayoutController = mainLayoutController;
    }

    public static void loadSubLayout(String fxml) {
        try {
            mainLayoutController.setLayout(FXMLLoader.load(Objects.requireNonNull(LayoutNavigator.class.getResource(fxml))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
