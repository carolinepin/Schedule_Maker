package sample;

import helper.JDBC;
import helper.Singleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    Singleton data = Singleton.getInstance();

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String nextAppointment){



        try {
            //Parent root = FXMLLoader.load(DBUtils.class.getResource("sample.fxml"));


            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            Parent root = loader.load();
            //System.out.println(fxmlFile);
            if (username != null && nextAppointment != null && fxmlFile.equals("loggedin.fxml")){               //for custom login page
                //System.out.println("Trying to Log in");
                LoggedInController loggedInController = loader.getController();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle(title);
                stage.setScene(new Scene(root, 600, 400));
                loggedInController.setUserInformation(username, nextAppointment);
                stage.show();

            }else {

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle(title);
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void logginUser(ActionEvent event, String username, String password) throws SQLException {
        String sql = "SELECT Password FROM USERS WHERE User_Name = ? ";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()){
            System.out.println("Invalid Loggin");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid Loggin");
            alert.show();
        }else{
            do
             {
                //System.out.println("Test");
                if ( password.equals(rs.getString("Password"))) {
                    System.out.println("Successful Login");
                    //System.out.println("IS THIS USERNAME NULL" + username);
                    DBUtils.changeScene(event, "loggedin.fxml", "Login Screen", username, "TEST DISPLAY");
                } else {
                    System.out.println("Tried " + username + " " + password);
                    System.out.println("Incorrect Password");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Incorrect Password");
                    alert.show();

                }
            }while(rs.next());
        }

    }


}
