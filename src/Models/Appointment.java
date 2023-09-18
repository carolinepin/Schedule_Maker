package Models;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createdate;
    private String createdby;
    private LocalDateTime updateddate;
    private String updatedby;
    private int customerID;
    private int userID;
    private int contactID;


    public Appointment(int apID, String title, String description, String Location, String type, LocalDateTime start,
                       LocalDateTime end, LocalDateTime CD, String CB, LocalDateTime UD, String UB, int customerid,
                       int userid, int contactid){
        this.appointmentID = apID;
        this.title = title;
        this.description = description;
        this.location = Location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createdate = CD;
        this.createdby = CB;
        this.updateddate = UD;
        this.updatedby = UB;
        this.customerID = customerid;
        this.userID = userid;
        this.contactID = contactid;

    }

    public int getAppointmentID(){return appointmentID;};

    public String getTitle() {
        return title;
    }
    public String getDescription(){return description;};

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public LocalDateTime getCreatedate() {
        return createdate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public LocalDateTime getUpdateddate() {
        return updateddate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public int getContactID() {
        return contactID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getUserID() {
        return userID;
    }
}
