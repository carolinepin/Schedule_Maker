package sample;

import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

public class Controller implements Initializable {
    public TableColumn idCol;
    public TableColumn nameCol;
    public TableView dataTable;



    @FXML
    private Button button_loggin;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("HelloTutorial");


        button_loggin.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    System.out.println(tf_username.getText());
                    DBUtils.logginUser(event, tf_username.getText(), tf_password.getText());   //go back to login page
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


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
