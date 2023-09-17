package DBAccess;

import Models.Country;
import Models.Customer;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DBCustomers {

    public static ObservableList<Customer> getAllCountries(){
        ObservableList<Customer> clist = FXCollections.observableArrayList(); //initilizes empty list of countries

        try{
           String sql = "SELECT * from customers";                  //SQL query to be sent

           PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);    //change command from string to Query & send it

           ResultSet rs = ps.executeQuery();                                            //store results from query in ResultSet Object

           while (rs.next()){ //while list of countries is not empty, move to the next country and perform the following
               int countryID = rs.getInt("Customer_ID");             //get the id from the SQL object
               String customerName = rs.getString("Customer_Name");
               String address = rs.getString("Address");
               String PostalCode = rs.getString("Postal_Code");
               String phone = rs.getString("Phone");
               LocalDateTime CreatedDate = rs.getObject("Create_Date", LocalDateTime.class );
               String CreatedBy = rs.getString("Created_By");
               LocalDateTime UpdatedOn = rs.getObject("Last_Update", LocalDateTime.class );
               String UpdatedBy = rs.getString("Last_Updated_By");

               Customer C = new Customer(countryID, customerName, address, PostalCode, phone,CreatedDate, CreatedBy, UpdatedOn, UpdatedBy);                 //make a Country Object from the id and name
               clist.add(C);                                                    //add it to the list

           }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return clist;
    }
}
