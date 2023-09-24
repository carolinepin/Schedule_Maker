package DBAccess;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUsers {
    public static ObservableList<String> getAllUserIDs(){
        ObservableList<String> uidlist = FXCollections.observableArrayList(); //initilizes empty list of countries

        try{
            String sql = "SELECT User_ID from users";                  //SQL query to be sent

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);    //change command from string to Query & send it

            ResultSet rs = ps.executeQuery();                                            //store results from query in ResultSet Object

            while (rs.next()){ //while list of countries is not empty, move to the next country and perform the following
                int userID = rs.getInt("User_ID");             //get the id from the SQL object
                uidlist.add(Integer.toString(userID));                                                    //add it to the list
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return uidlist;
    }
}
