package DBAccess;

import Models.FL_Divisions;
import helper.JDBC;
import Models.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public class DBCountriesAndFD {

    public static ObservableList<String> getAllCountries(){
        ObservableList<String> clist = FXCollections.observableArrayList(); //initilizes empty list of countries

        try{
           String sql = "SELECT * from countries";                  //SQL query to be sent

           PreparedStatement ps = helper.JDBC.getConnection().prepareStatement(sql);    //change command from string to Query & send it

           ResultSet rs = ps.executeQuery();                                            //store results from query in ResultSet Object

           while (rs.next()){ //while list of countries is not empty, move to the next country and perform the following
               String countryName = rs.getString("Country");        //get the name from the SQL object
               clist.add(countryName);                                                    //add it to the list

           }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return clist;
    }

    public static ObservableList<String> getAllFL_Divisions(String Country){
        ObservableList<String> fldlist = FXCollections.observableArrayList(); //initilizes empty list of countries

        try{
            String sql = "SELECT * from first_level_divisions WHERE Country_ID = (SELECT Country_ID FROM countries WHERE Country = ?)"; //SQL query to be sent
            //(SELECT Country_ID FROM countries WHERE Country = ?)

            PreparedStatement ps = helper.JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, Country);

            ResultSet rs = ps.executeQuery();                                            //store results from query in ResultSet Object

            while (rs.next()){ //while list of countries is not empty, move to the next country and perform the following
                String div = rs.getString("Division");        //get the name from the SQL object
                fldlist.add(div);                                                    //add it to the list

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return fldlist;


    }
    public static int getDivID(String div) throws SQLException {
        int divID = 1;
        String sql = "SELECT Division_ID FROM first_level_divisions WHERE Division = ?";                  //SQL query to be sent

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, div);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){ //while list of countries is not empty, move to the next country and perform the following
            divID = rs.getInt("Division_ID");
            return divID;

        }
        return divID;
    }



}
