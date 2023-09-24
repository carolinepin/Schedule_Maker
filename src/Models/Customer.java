package Models;

import java.time.LocalDateTime;

public class Customer {
    private int Customer_ID;
    private String Name;
    private String Address;
    private String zip;
    private String phone;
    private LocalDateTime CreatedTime;
    private String CreatedBy;
    private LocalDateTime UpdatedAt;
    private String UpdatedBy;
    private int divisionID;

    public Customer(int id, String name, String add, String zip, String phone, LocalDateTime ct, String cb,
                    LocalDateTime ut, String ub, int divisionID){
        this.Customer_ID = id;
        this.Name = name;
        this.Address = add;
        this.zip =zip;
        this.phone = phone;
        this.CreatedTime = ct;
        this.CreatedBy = cb;
        this.UpdatedAt = ut;
        this.UpdatedBy = ub;
        this.divisionID = divisionID;

    }

    public int getCustomerId(){return Customer_ID;};
    public String getName(){return Name;};
    public String getAddress(){return Address;};
    public String getZip(){return zip;};
    public String getPhone(){return phone;};
    public LocalDateTime getCT(){return CreatedTime;};
    public String getCreatedBy(){return CreatedBy;};
    public LocalDateTime getUT(){return UpdatedAt;};
    public String getUpdatedBy(){return UpdatedBy;};
    public int getDivisionID() {return divisionID;}




}
