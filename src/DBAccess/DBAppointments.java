package DBAccess;

import Models.Appointment;
import Models.Customer;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DBAppointments {

    public static ObservableList<Appointment> getAllAppointments(){
        ObservableList<Appointment> alist = FXCollections.observableArrayList(); //initilizes empty list of countries

        try{
            String sql = "SELECT * from Appointments";                  //SQL query to be sent

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);    //change command from string to Query & send it

            ResultSet rs = ps.executeQuery();                                            //store results from query in ResultSet Object

            while (rs.next()){ //while list of countries is not empty, move to the next country and perform the following
                int apID = rs.getInt("Appointment_ID");             //get the id from the SQL object
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getObject("Start", LocalDateTime.class );
                LocalDateTime end = rs.getObject("End", LocalDateTime.class );
                LocalDateTime CreatedDate = rs.getObject("Create_Date", LocalDateTime.class );
                String CreatedBy = rs.getString("Created_By");
                LocalDateTime UpdatedOn = rs.getObject("Last_Update", LocalDateTime.class );
                String UpdatedBy = rs.getString("Last_Updated_By");
                int custID = rs.getInt("Appointment_ID");
                int userID = rs.getInt("Appointment_ID");
                int contID = rs.getInt("Appointment_ID");

                Appointment A = new Appointment(apID, title, description, location, type, start, end, CreatedDate, CreatedBy,
                        UpdatedOn, UpdatedBy, custID, userID, contID);                 //make a Country Object from the id and name
                alist.add(A);                                                    //add it to the list

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return alist;
    }
}
