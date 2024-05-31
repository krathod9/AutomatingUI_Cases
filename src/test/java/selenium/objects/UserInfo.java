package selenium.objects;

import selenium.utils.JacksonUtils;

import java.io.IOException;

public class UserInfo {
    private String userID;
    private String password;
    private String email;

    public UserInfo(){    }

    public UserInfo(String userID, String password, String email) {
        this.userID = userID;
        this.password = password;
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public UserInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUserID() {
        return userID;
    }

    public UserInfo setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserInfo setPassword(String password) {
        this.password = password;
        return this;
    }


}
