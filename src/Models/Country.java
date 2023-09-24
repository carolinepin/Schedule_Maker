package Models;

import java.time.LocalDateTime;

public class Country {
    private int id;
    private String name;
    private LocalDateTime CreateDate;
    private String createdBy;
    private LocalDateTime UpdateDate;
    private String updatedBy;

    public Country(int id, String name, LocalDateTime CD, String CB, LocalDateTime UD, String UB){
        this.id = id;
        this.name = name;
        this.CreateDate = CD;
        this.createdBy = CB;
        this.UpdateDate = UD;
        this.updatedBy = UB;
    }

    public int getID() {return id;}
    public String getName() {return name;}
    public LocalDateTime getCreateDate() {return CreateDate;}
    public String getCreatedBy() {return createdBy;}
    public LocalDateTime getUpdateDate() {return UpdateDate;}
    public String getUpdatedBy() {return updatedBy;}
}
