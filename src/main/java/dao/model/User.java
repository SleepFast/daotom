package dao.model;

public class User {
    private String login;
    private String pwd;
    private String firstName;
    private String lastName;
    private int id;

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
    	return id;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return String.format("%s %s %s %s %d", firstName, lastName, login, pwd, id);
    }
}