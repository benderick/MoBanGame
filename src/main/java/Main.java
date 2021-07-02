import Utils.ViewLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewLoader.showStage(null, "/fxmls/start.fxml", "", new Stage());

    }
    public static void main(String[] args) {
        launch(args);
    }
}
