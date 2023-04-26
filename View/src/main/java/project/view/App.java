package project.view;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App.
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    static Locale location = new Locale("en");

    static void setStart(Locale location) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages", location);
        Parent root = FXMLLoader.load(Objects.requireNonNull(
                App.class.getResource("primary.fxml")), bundle);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    static void setStart() throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages", location);
        Parent root = FXMLLoader.load(Objects.requireNonNull(
                App.class.getResource("primary.fxml")), bundle);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        Locale.setDefault(location);
        setStart();
    }

    static void setRoot(FXMLLoader loader) throws IOException {
        scene.setRoot(loader.load());
    }


    public static void main(String[] args) {
        launch();
    }

}