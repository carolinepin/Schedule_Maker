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
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DBCustomers {

    public static ObservableList<Customer> getAllCustomers(){
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

    public static int update(int custID, String Name, String Addr, String Postal, String Phone) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ? , Postal_Code = ? , Phone = ? , Last_Update = ?, Last_Updated_By = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        LocalDateTime timestamp = LocalDateTime.now();
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZoneId myZoneId = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(timestamp, myZoneId);
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);


        ps.setString(1, Name);
        ps.setString(2, Addr);
        ps.setString(3, Postal);
        ps.setString(4, Phone);
        ps.setObject(5, utcZDT);
        ps.setString(6, "admin");  //NEED TO CHANGE, MUST ACCEPT USERNAME IN ARGUMENT TO ADD HERE
        ps.setInt(7, custID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

}
