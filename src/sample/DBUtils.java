package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String nextAppointment){


        try {
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            Parent root = loader.load();
            LoggedInController loggedInController = loader.getController();
            if (username != null && nextAppointment != null){
                loggedInController.setUserInformation(username, nextAppointment);
            } else {
                System.out.println("This will never happen");
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //IDK IF I CAN USE STAGE
            stage.setTitle(title);
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
