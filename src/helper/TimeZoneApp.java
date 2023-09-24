package helper;

import java.time.*;


public class TimeZoneApp {

    public static void main(String[] args){

        ZoneId.getAvailableZoneIds().stream().sorted().forEach(System.out::println); //NEAT WAY TO LOOP
        //System.out.println(ZoneId.systemDefault());
        LocalDate myLD = LocalDate.of(2023,9,16);
        LocalTime myLT = LocalTime.of(23,55,0);

        LocalDateTime LDT = LocalDateTime.of(myLD,myLT);

        ZoneId myZoneId = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(LDT, myZoneId);
        //System.out.println(myZDT);
        System.out.println(myZDT.toLocalDate());
        System.out.println(myZDT.toLocalTime());
        System.out.println("Usertime = " + myZDT.toLocalDate().toString() + " " + myZDT.toLocalTime().toString()); //now ready for SQL because string

        //CONVERT LOCAL TIME TO UTC
        ZoneId utcZoneId = ZoneId.of("UTC");
        System.out.println("TEST = " + myZDT.toInstant()); //TO INSTANT CONVERTS THE TIMEZONE TO UTC/RESTS TIME OFFSET TO ZERO
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId); //THIS CONVERTS TO CUSTOME TIME ZONE, JUST REPLACE utcZoneId with another zone
        System.out.println("User Time to UTC = "+ utcZDT);

        //CONVERT UTC TO LOCAL TIME
        myZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), myZoneId);
        System.out.println(myZDT);






    }
}
