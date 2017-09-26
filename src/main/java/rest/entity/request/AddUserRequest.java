package rest.entity.request;

public class AddUserRequest {

    private String name;
    private String password;

    public AddUserRequest(String name, String password){
        this.name=name;
        this.password=password;
    }

    public AddUserRequest() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String surname) {
        this.password = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
