package DBAccess;

import helper.JDBC;
import Models.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public class DBCountries {

    public static ObservableList<Country> getAllCountries(){
        ObservableList<Country> clist = FXCollections.observableArrayList(); //initilizes empty list of countries

        try{
           String sql = "SELECT * from countries";                  //SQL query to be sent

           PreparedStatement ps = helper.JDBC.getConnection().prepareStatement(sql);    //change command from string to Query & send it

           ResultSet rs = ps.executeQuery();                                            //store results from query in ResultSet Object

           while (rs.next()){ //while list of countries is not empty, move to the next country and perform the following
               int countryID = rs.getInt("Country_ID");             //get the id from the SQL object
               String countryName = rs.getString("Country");        //get the name from the SQL object
               LocalDateTime createDate = rs.getObject("Create_Date", LocalDateTime.class );
               String createdBy = rs.getString("Created_By");
               LocalDateTime updateDate = rs.getObject("Last_Update", LocalDateTime.class );
               String updatedBy = rs.getString("Last_Updated_By");
               Country C = new Country(countryID, countryName, createDate, createdBy, updateDate, updatedBy);                 //make a Country Object from the id and name
               clist.add(C);                                                    //add it to the list

           }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return clist;
    }
}
