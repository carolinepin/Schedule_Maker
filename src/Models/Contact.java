package Models;

public class Contact {
    private int ContactID;
    private String Name;
    private String Email;


    public Contact(int id, String name, String email){
        this.ContactID = id;
        this.Name = name;
        this.Email = email;
    }

    public int getID() {return ContactID;}
    public String getName() {return Name;}
    public String getEmail() {return Email;}





}
