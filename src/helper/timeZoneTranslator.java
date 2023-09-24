package helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import helper.userComputerInfo;

public class timeZoneTranslator {
    static userComputerInfo myUser = userComputerInfo.getInstance("null", false);

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
}
