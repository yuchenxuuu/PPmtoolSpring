package io.ppmdev.ppmtool.exceptions;

public class UserNameExistResponse {

    private String username;

    public UserNameExistResponse(String username){
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
