package sample;

import java.net.URL;
import java.util.ResourceBundle;

import DBAccess.DBCountries;
import Models.Country;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Controller implements Initializable {
    public TableColumn idCol;
    public TableColumn nameCol;
    public TableView dataTable;

    @FXML
    private Button button_loggin;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("HelloTutorial");
        button_loggin.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "loggedin.fxml", "Logged In!", null, null);   //go back to login page


            }
        });
    }

    public void showMe(ActionEvent actionEvent)
    {
        ObservableList<Country> countrylist = DBCountries.getAllCountries();
        for (Country C : countrylist){
            System.out.println("Country ID: "+ C.getID() + " Name: " + C.getName());
        }
    }

}
