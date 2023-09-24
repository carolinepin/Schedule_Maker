package DBAccess;

import Models.Appointment;
import Models.Customer;
import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import helper.timeZoneTranslator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import helper.userComputerInfo;

public class DBAppointments {

    public static String getNextAppointment() throws SQLException {
        String message = "There are no appointments within 15 minutes!";
        LocalDateTime localTime = LocalDateTime.now();
        LocalDateTime in15Minutes = localTime.minusMinutes(15);
        in15Minutes = helper.timeZoneTranslator.toUTC(in15Minutes);

        String sql = "SELECT * from Appointments WHERE Start > ? ORDER BY Start";                  //SQL query to be sent
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);    //change command from string to Query & send it
        ps.setObject(1,in15Minutes);
        ResultSet rs = ps.executeQuery();


        while (rs.next()){
            LocalDateTime nextAppointment = rs.getObject("Start", LocalDateTime.class);
            int appointmentID = rs.getInt("Appointment_ID");
            nextAppointment = timeZoneTranslator.fromUTC(nextAppointment);

            message = "There is an appointment in 15 minutes! \n" +
                    "AppointmentID: " + appointmentID+ "\n" +
                    "Appointment At: "+ nextAppointment.toLocalTime().toString() + "\n" +
                    "On: " +nextAppointment.toLocalDate().toString();
            return message;
        }

        return message;
    }



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
                start = timeZoneTranslator.fromUTC(start);
                LocalDateTime end = rs.getObject("End", LocalDateTime.class );
                end = timeZoneTranslator.fromUTC(end);
                LocalDateTime CreatedDate = rs.getObject("Create_Date", LocalDateTime.class );
                CreatedDate = timeZoneTranslator.fromUTC(CreatedDate);
                String CreatedBy = rs.getString("Created_By");
                LocalDateTime UpdatedOn = rs.getObject("Last_Update", LocalDateTime.class );
                UpdatedOn = timeZoneTranslator.fromUTC(UpdatedOn);
                String UpdatedBy = rs.getString("Last_Updated_By");
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contID = rs.getInt("Contact_ID");

                Appointment A = new Appointment(apID, title, description, location, type, start, end, CreatedDate, CreatedBy,
                        UpdatedOn, UpdatedBy, custID, userID, contID);                 //make a Country Object from the id and name
                alist.add(A);                                                    //add it to the list

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return alist;
    }

    public static int update(int appID, String title, String desc, String location, String type,
                             LocalDateTime Start, LocalDateTime End, int custID, int userID, int contactID) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ? , Location = ? , Type = ? , Last_Update = ?, Last_Updated_By = ?, " +
                "Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        LocalDateTime timestamp = LocalDateTime.now();
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZoneId myZoneId = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(timestamp, myZoneId);
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);

        ps.setString(1, title);
        ps.setString(2, desc);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setObject(5, utcZDT.toLocalDateTime());
        ps.setString(6, helper.userComputerInfo.getInstance(null,false).getUsername());
        ps.setObject(7, timeZoneTranslator.toUTC(Start));
        ps.setObject(8, timeZoneTranslator.toUTC(End));
        ps.setInt(9,custID);
        ps.setInt(10,userID);
        ps.setInt(11, contactID);
        ps.setInt(12, appID);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    public static boolean timeOverlap(LocalDateTime time) throws SQLException {
        boolean overlap = false;
        String sql = "SELECT * from Appointments";                  //SQL query to be sent
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);    //change command from string to Query & send it
        ResultSet rs = ps.executeQuery();
        while (rs.next()){ //while list of countries is not empty, move to the next country and perform the following
            LocalDateTime start = rs.getObject("Start", LocalDateTime.class );
            start = timeZoneTranslator.fromUTC(start);
            LocalDateTime end = rs.getObject("End", LocalDateTime.class );
            end = timeZoneTranslator.fromUTC(end);
            if (time.isAfter(start) && time.isBefore(end)){
                return overlap = true;
            }

        }

        return overlap;
    }

}
