package DBAccess;

import Models.Contact;
import Models.Customer;
import helper.JDBC;
import helper.timeZoneTranslator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DBContacts {

    public static ObservableList<Contact> getAllContacts(){
        ObservableList<Contact> clist = FXCollections.observableArrayList(); //initilizes empty list of countries

        try{
            String sql = "SELECT * from contacts";                  //SQL query to be sent

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);    //change command from string to Query & send it

            ResultSet rs = ps.executeQuery();                                            //store results from query in ResultSet Object

            while (rs.next()){ //while list of countries is not empty, move to the next country and perform the following
                int contactID = rs.getInt("Contact_ID");             //get the id from the SQL object
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contact C = new Contact(contactID, name, email);                 //make a Country Object from the id and name
                clist.add(C);                                                    //add it to the list

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return clist;
    }

    public static ObservableList<String> getAllContactIDs(){
        ObservableList<String> cidlist = FXCollections.observableArrayList(); //initilizes empty list of countries

        try{
            String sql = "SELECT Contact_ID from contacts";                  //SQL query to be sent

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);    //change command from string to Query & send it

            ResultSet rs = ps.executeQuery();                                            //store results from query in ResultSet Object

            while (rs.next()){ //while list of countries is not empty, move to the next country and perform the following
                int contactID = rs.getInt("Contact_ID");             //get the id from the SQL object
                cidlist.add(Integer.toString(contactID));                                                    //add it to the list
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return cidlist;
    }
}
