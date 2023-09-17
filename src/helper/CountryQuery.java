package helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public abstract class CountryQuery {

    public static int insert(String CountryName) throws SQLException {
        String sql = "INSERT INTO COUNTRIES (Country) VALUES(?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, CountryName);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    public static int update(int CountryId, String CountryName, LocalDateTime Date ) throws SQLException {
        String sql = "UPDATE COUNTRIES SET Country = ?, Create_Date = ? WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, CountryName);
        ps.setObject(2, Date);
        ps.setInt(3, CountryId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }
    public static int delete(int CountryID) throws SQLException {
        String sql = "DELETE FROM COUNTRIES WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1,CountryID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    public static void select() throws SQLException {
        String sql = "Select * FROM COUNTRIES";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int CountryID = rs.getInt("Country_ID");
            String CountryName = rs.getString("Country");
            System.out.print("Country ID: " + CountryID + "\t\t\t Country Name: " + CountryName + "\n");
        }
    }
    public static void select(int CountryID) throws SQLException {
        String sql = "Select * FROM COUNTRIES WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1,CountryID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int countryID = rs.getInt("Country_ID");
            String CountryName = rs.getString("Country");
            LocalDateTime ldt = rs.getObject( "Create_Date", LocalDateTime.class ) ;
            System.out.print("Country ID: " + countryID + "\t\t\t Country Name: " + CountryName + "\t\t\t CreateDate: " + ldt + "\n");
        }
    }
}
