package Controllers;

import Utils.Controller;
import Utils.ViewLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class StartController extends Controller {
    @FXML
    private Button outButton;

    @FXML
    private Button inButton;

    @FXML
    private Button exitButton;


    @FXML
    void outButtonClick(ActionEvent event) throws IOException {
        stage.close();
        ViewLoader.showStage(model, "/fxmls/outMain.fxml", "单机魔板", new Stage());
    }

    @FXML
    void inButtonClick(ActionEvent event) throws IOException {
        Stage second = new Stage();
        second.initStyle(StageStyle.TRANSPARENT);
        ViewLoader.showStage(model, "/fxmls/logreg.fxml", "登陆注册", second);
    }

    @FXML
    void exitButtonClick(ActionEvent event)  {
        System.exit(0);
    }

}
