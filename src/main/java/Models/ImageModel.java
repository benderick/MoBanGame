package Models;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class ImageModel
{
    private SimpleStringProperty text = new SimpleStringProperty();

    public ImageModel()
    {
        this.text = new SimpleStringProperty();
    }

    public StringProperty textProperty() {
        return text;
    }

    public final String getText() {
        return textProperty().get();
    }


    public final void setText(String text) {
        textProperty().set(text);
    }
}