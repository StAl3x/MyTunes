package mytunes.gui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

import java.io.IOException;

public class ErrorAlert extends Alert {


    public ErrorAlert(AlertType alertType)
    {
        super(alertType);
        init(null);
    }

    public ErrorAlert(AlertType alertType, String contentText, ButtonType... buttons)
    {
        super(alertType, contentText, buttons);
        init(contentText);
    }

    private void init(String contentText)
    {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ErrorAlertView.fxml"));
            DialogPane dp = loader.load();
            this.setTitle("Error Dialog");
            dp.setContentText(contentText);
            dp.setHeaderText("You are not connected");
            super.setDialogPane(dp);
        }
        catch(IOException ioex)
        {

        }
    }

}
