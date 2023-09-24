package Models;

import java.time.LocalDateTime;

public class FL_Divisions {
    private int divisionID;
    private String division;
    private LocalDateTime CreateDate;
    private String createdBy;
    private LocalDateTime UpdateDate;
    private String updatedBy;
    private int countryID;

    public FL_Divisions(int divID, String div, LocalDateTime CD, String CB, LocalDateTime UD, String UB, int countryID ){
        this.divisionID = divID;
        this.division = div;
        this.CreateDate = CD;
        this.createdBy = CB;
        this.UpdateDate = UD;
        this.updatedBy = UB;
        this.countryID = countryID;

    }

    public int getDivisionID() {return divisionID;}
    public String getDivision() {return division;}
    public LocalDateTime getCreateDate() {return CreateDate;}
    public LocalDateTime getUpdateDate() {return UpdateDate;}
    public String getCreatedBy() {return createdBy;}
    public String getUpdatedBy() {return updatedBy;}
    public int getCountryID() {return countryID;}



}
