package helper;

import java.time.LocalDateTime;
import java.time.ZoneId;

/*
userComputerInfo Class:
This class represents the user who logged in
This class has the stored values gained from the user that the application will use to know which user is updating or creating data,
know what the local time is to display info in the local time, and to know what language the user computer is in.
 */
public class userComputerInfo {
    private static userComputerInfo instance;
    private String username;


    private userComputerInfo(String username){
        this.username = username;

    }

    public static userComputerInfo getInstance(String username, Boolean New){

        if (New == false){
            return instance;
        }
        else {
            instance = new userComputerInfo(username);

            return instance;
        }
    }

    public String getUsername(){
        return instance.username;
    }
}
