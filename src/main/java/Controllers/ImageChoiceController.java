package Controllers;

import Utils.Controller;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class ImageChoiceController extends Controller {

    @FXML
    private AnchorPane choicePane;

    @FXML
    public void defaultButtonClick(){
        File file = new File("C:\\Program Files\\MoBanGame\\Saved Pictures");

        File [] files = file.listFiles();

        ImageView[] imageViews = new ImageView[files.length];

        for(int i = 0; i < files.length; i++)
        {
            String url = "file:///" + files[i].getPath().replaceAll("\\\\", "/");
            Image image = new Image(url, 200, 200, false, false);
            imageViews[i] = new ImageView(image);
            imageViews[i].setOnMouseClicked(new ChooseImage());
        }
        GridPane gridPane = new GridPane();
        gridPane.setHgap(60);
        gridPane.setVgap(20);
        for(int i=0; i < imageViews.length; i++){
            gridPane.add(imageViews[i], i%2, i/2);
        }
        gridPane.setGridLinesVisible(true);
        choicePane.getChildren().add(gridPane);
    }

    class ChooseImage implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent arg0) {
            ImageView img = (ImageView) arg0.getSource();
            img.getImage();


        }
    }
}
