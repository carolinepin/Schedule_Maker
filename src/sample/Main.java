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
import java.time.*;

//<a href="https://www.vecteezy.com/free-vector/cloud">Cloud Vectors by Vecteezy</a> cloud image for login
//color pallet
//42758E blue
//7A9AAB light blue
//F1EDEC off white
//E9B28C peach
//B55434 terracotta orange

//611B14 red/blood orange
//1D2218 dark forest green
//98BA9D chill mint green

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("2 Tutorial Videos Later");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        int rowsAffected = 0;
        //rowsAffected = CountryQuery.insert("Wakanda");

        //rowsAffected = CountryQuery.delete(4);
        CountryQuery.select(5);
        LocalDate myLD = LocalDate.of(2023,9,16);
        LocalTime myLT = LocalTime.of(12,34,56);

        LocalDateTime LDT = LocalDateTime.of(myLD,myLT);
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now.toLocalDate() + " " + now.toLocalTime() + "the computer current time");
        rowsAffected = CountryQuery.update(5, "North Pole",LDT);
        System.out.println(rowsAffected);

        //System.out.println(LDT.toLocalDate() + " " + LDT.toLocalTime() + ":" + LDT.toLocalTime().getSecond() );

        //if (rowsAffected > 0) {
            //System.out.println("Insert Successful!");

        //}
        //else {
            //System.out.println("Insert Failed!");
        //}




        launch(args);




        JDBC.closeConnection();
    }
}
