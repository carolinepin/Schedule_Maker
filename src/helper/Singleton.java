package helper;

public class Singleton {
    private static final Singleton instance = new Singleton();

    private String userName;

    private Singleton(){}

    public static Singleton getInstance(){
        return instance;
    }

    public String getUserName() { return userName; };
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
