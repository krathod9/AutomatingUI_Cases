package selenium.objects;

import selenium.utils.JacksonUtils;

import java.io.IOException;

public class UserInfo {
    private String userID;
    private String password;

    public UserInfo(){    }

    public UserInfo(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
