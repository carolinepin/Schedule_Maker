package Models;

import java.time.LocalDateTime;

public class User {
    private int userid;
    private String username;
    private String password;
    private LocalDateTime createddate;
    private String createdby;
    private LocalDateTime updateddate;
    private String updatedby;


    public User(int userID, String userName, String pass, LocalDateTime CD, String CB, LocalDateTime UD, String UB){
        this.userid = userID;
        this.username = userName;
        this.password = pass;
        this.createddate = CD;
        this.createdby = CB;
        this.updateddate = UD;
        this.updatedby = UB;

    }

    public int getUserid(){
        return userid;
    }
    public String getUsername(){
        return username;
    }

}
