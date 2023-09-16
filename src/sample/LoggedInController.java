package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    @FXML
    private Button proceed_button;

    @FXML
    Label label_welcome;

    @FXML
    Label next_appointment;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        proceed_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml", "Login!", null, null);   //go back to login page


            }
        });

    }

    public void setUserInformation(String username, String appointment){
        label_welcome.setText("Welcome " + username + "!");
        next_appointment.setText("Your next appointment is at " +appointment);
    }

}
