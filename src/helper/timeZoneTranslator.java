package helper;

import java.sql.SQLException;
import java.time.*;

import DBAccess.DBAppointments;
import helper.userComputerInfo;

public class timeZoneTranslator {

    public static LocalDateTime toUTC(LocalDateTime LT){
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime userZDT = ZonedDateTime.of(LT, ZoneId.systemDefault());
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(userZDT.toInstant(), utcZoneId);
        return utcZDT.toLocalDateTime();
    }

    public static LocalDateTime fromUTC( LocalDateTime UTC){
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime utcZDT = ZonedDateTime.of(UTC, utcZoneId);
        ZonedDateTime userZDT = ZonedDateTime.ofInstant(utcZDT.toInstant(), ZoneId.systemDefault());
        return userZDT.toLocalDateTime();
    }


    public static int timeCheck(LocalDateTime start, LocalDateTime end) throws SQLException {
        int goodTime = 1;  //this will tell the calling program if the time is good, or if it is not, what kind of error it has
        // good time 1 = time is valid
        // good time 0 = start time and end time are mixed up
        // good time 3 = schedule overlapping with another appointment
        if (start.isAfter(end) || end.isBefore(start)) goodTime = 0;

        if (DBAppointments.timeOverlap(start) || DBAppointments.timeOverlap(end)){
            goodTime =3;
        }


        return goodTime;
    }
}
