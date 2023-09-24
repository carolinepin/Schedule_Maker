package sample;

import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;
import helper.userComputerInfo;

import DBAccess.DBCountriesAndFD;
import Models.Country;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.*;


public class Controller implements Initializable {




    @FXML
    private Button button_loggin;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;

    @FXML
    private Label userTimeZone;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ZoneId userZone = ZoneId.systemDefault();

        userTimeZone.setText(userZone.toString());
        //ResourceBundle myBundle = ResourceBundle.getBundle("lang");

        button_loggin.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    System.out.println(tf_username.getText());
                    int setData = DBUtils.logginUser(event, tf_username.getText(), tf_password.getText());   //go to login page
                    if (setData == 1) {   //if login was successful
                        userComputerInfo user = userComputerInfo.getInstance(tf_username.getText(), true);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });
    }
/*
OLD TEST CODE to see all countries. It was my first Observable list and holds sentimental value
    public void showMe(ActionEvent actionEvent)
    {
        ObservableList<Country> countrylist = DBCountriesAndFD.getAllCountries();
        for (Country C : countrylist){
            System.out.println("Country ID: "+ C.getID() + " Name: " + C.getName());
        }
    }

 */

}
