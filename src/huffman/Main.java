package huffman;

import huffman.controllers.LayoutNavigator;
import huffman.controllers.MainLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../resources/MainLayout.fxml")));
        primaryStage.setTitle("Kodowanie metodą Huffmana");
        /*primaryStage.setScene(new Scene(root, 600, 400));*/
        primaryStage.setScene(createScene(loadMainPane()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = loader.load(getClass().getResourceAsStream(LayoutNavigator.MAIN));

        MainLayoutController mainLayoutController = loader.getController();

        LayoutNavigator.setMainLayoutController(mainLayoutController);
        LayoutNavigator.loadSubLayout(LayoutNavigator.CALC);
        return mainPane;
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        scene.getStylesheets().setAll(Objects.requireNonNull(getClass().getResource("../resources/styles.css")).toExternalForm());
        return scene;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
