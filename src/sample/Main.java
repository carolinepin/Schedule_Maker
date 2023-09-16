package sample;

import helper.CountryQuery;
import helper.JDBC;
import sample.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("2 Tutorial Videos Later");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        int rowsAffected = 0;
        //rowsAffected = CountryQuery.insert("Wakanda");
        //rowsAffected = CountryQuery.update(5, "North Pole");
        //rowsAffected = CountryQuery.delete(4);

        //if (rowsAffected > 0) {
            //System.out.println("Insert Successful!");

        //}
        //else {
            //System.out.println("Insert Failed!");
        //}

        CountryQuery.select(3);


        launch(args);




        JDBC.closeConnection();
    }
}
